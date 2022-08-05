package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentOrderDao{

    private static final String INSERT_ORDER =
            "INSERT INTO jc_student_order(" +
                    " student_order_status, student_order_date, " +
                    "h_sur_name, h_given_name, h_patronymic, h_date_of_birth, " +
                        "h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, " +
                        "h_post_index, h_street_code, h_building, h_extension, h_apartment, h_university_id, h_student_number, " +
                    "w_sur_name, w_given_name, w_patronymic, w_date_of_birth, " +
                        "w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id, " +
                        "w_post_index, w_street_code, w_building, w_extension, w_apartment, w_university_id, w_student_number, " +
                    "certificate_id, register_office_id, marriage_date)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_CHILD =
            "INSERT INTO jc_student_child(" +
                    " student_order_id, c_sur_name, c_given_name, c_patronymic, c_date_of_birth, " +
                    " c_certificate_number, c_certificate_date, c_register_office_id, " +
                    " c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ORDERS =
            " SELECT so.*, ro.r_office_area_id, ro.r_office_name," +
                    " po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name," +
                    " po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name" +
                    " FROM jc_student_order so" +
                    " INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id" +
                    " INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id" +
                    " INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id" +
                    " WHERE student_order_status = ?" +
                    " ORDER BY student_order_date LIMIT ?;";

    private static final String SELECT_CHILD =
            "SELECT soc.*, ro.r_office_area_id, ro.r_office_name " +
                    "FROM jc_student_child soc " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = soc.c_register_office_id " +
                    "WHERE soc.student_order_id IN ";

    private static final String SELECT_ORDERS_FULL =
            " SELECT so.*, ro.r_office_area_id, ro.r_office_name," +
                    " po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name," +
                    " po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name," +
                    " soc.*, ro_c.r_office_area_id, ro_c.r_office_name " +
                    " FROM jc_student_order so" +
                    " INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id" +
                    " INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id" +
                    " INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id" +
                    " INNER JOIN jc_student_child soc ON soc.student_order_id = so.student_order_id" +
                    " INNER JOIN jc_register_office ro_c  ON ro_c.r_office_id = soc.c_register_office_id" +
                    " WHERE student_order_status = ?" +
                    " ORDER BY so.student_order_id LIMIT ?;";

    // TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException ex){
            throw new SQLException(ex);
        }
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_USER),
                Config.getProperty(Config.DB_PASSWORD)
        );
        return connection;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1L;

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})){

            connection.setAutoCommit(false);
            try {
                // Header
                stmt.setInt(1, StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

                // Husband and Wife
                setParamsForAdult(stmt, 3, so.getHusband());
                setParamsForAdult(stmt, 18, so.getWife());

                // Marriage
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();

                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    result = keys.getLong(1);
                }

                saveChildren(connection, so, result);
                connection.commit();
            }catch (SQLException ex){
                connection.rollback();
                throw ex;
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }

        return result;
    }

    private void saveChildren(Connection connection, StudentOrder so, Long soId) throws SQLException{
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CHILD)){
            for (Child child:so.getChildren()){
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
//                stmt.executeUpdate();
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private static void setParamsForAdult(PreparedStatement stmt, int start,  Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);
        stmt.setString(start +4, adult.getPassportSerialNumber());
        stmt.setString(start +5, adult.getPassportNumber());
        stmt.setDate(start +6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start +7, adult.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, start + 8, adult);
        stmt.setLong(start +13, adult.getUniversity().getUniversityId());
        stmt.setString(start +14, adult.getStudentId());
    }

    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException{
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child);
    }

    private static void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start +1, person.getGivenName());
        stmt.setString(start +2, person.getPatronymic());
        stmt.setDate(start +3, Date.valueOf(person.getDateOfBirth()));
    }

    private static void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address husbandAddress = person.getAddress();
        stmt.setString(start, husbandAddress.getPostCode());
        stmt.setLong(start +1, husbandAddress.getStreet().getStreetCode());
        stmt.setString(start +2, husbandAddress.getBuilding());
        stmt.setString(start +3, husbandAddress.getExtension());
        stmt.setString(start +4, husbandAddress.getApartment());
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        return getStudentOrderOneSelect();
//        return getStudentOrderTwoSelect();
    }

    private List<StudentOrder> getStudentOrderOneSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ORDERS_FULL)) {

            Map<Long,StudentOrder> maps = new HashMap<>();

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            int counter = 0;
            while (rs.next()){
                Long soId = rs.getLong("student_order_id");
                if (!maps.containsKey(soId)) {
                    StudentOrder so = getFullStudentOrder(rs);

                    result.add(so);
                    maps.put(soId, so);
                }
                StudentOrder so = maps.get(soId);
                so.addChild(fillChild(rs));
                counter++;
            }
            if (counter >= limit){
                result.remove(result.size() - 1);
            }

            rs.close();
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    private List<StudentOrder> getStudentOrderTwoSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ORDERS)) {

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setInt(2, Integer.parseInt(Config.getProperty(Config.DB_LIMIT)));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                StudentOrder so = getFullStudentOrder(rs);

                result.add(so);
            }
            findChildren(connection, result);

            rs.close();
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();

        fillStudentOrder(rs, so);
        fillMarriage(rs, so);

        so.setHusband(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        so.setMarriageOffice(new RegisterOffice(roId, areaId, name));
    }

    private Adult fillAdult(ResultSet rs, String pref) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(rs.getString(pref + "sur_name"));
        adult.setGivenName(rs.getString(pref + "given_name"));
        adult.setPatronymic(rs.getString(pref + "patronymic"));
        adult.setDateOfBirth(rs.getDate(pref + "date_of_birth").toLocalDate());
        adult.setPassportSerialNumber(rs.getString(pref + "passport_seria"));
        adult.setPassportNumber(rs.getString(pref + "passport_number"));
        adult.setIssueDate(rs.getDate(pref + "passport_date").toLocalDate());

        Long poId = rs.getLong(pref + "passport_office_id");
        String poArea = rs.getString(pref + "p_office_area_id");
        String poName = rs.getString(pref + "p_office_name");
        PassportOffice po = new PassportOffice(poId, poArea, poName);
        adult.setIssueDepartment(po);

        Address address = new Address();
        Street street = new Street(rs.getLong(pref + "street_code"), "");

        address.setStreet(street);
        address.setPostCode(rs.getString(pref + "post_index"));
        address.setBuilding(rs.getString(pref + "building"));
        address.setExtension(rs.getString(pref + "extension"));
        address.setApartment(rs.getString(pref + "apartment"));

        adult.setAddress(address);

        University uni = new University(rs.getLong(pref + "university_id"), "");
        adult.setUniversity(uni);
        adult.setStudentId(rs.getString(pref + "student_number"));

        return adult;
    }

    private void findChildren(Connection conn, List<StudentOrder> result) throws SQLException {
        String cl = "(" + result.stream().map(so -> String.valueOf(so.getStudentOrderId()))
                .collect(Collectors.joining(",")) + ")";
        Map<Long, StudentOrder> maps = result.stream().collect(Collectors.toMap(so -> so.getStudentOrderId(), so -> so));

        try(PreparedStatement stmt = conn.prepareStatement(SELECT_CHILD + cl)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Child child = fillChild(rs);
                StudentOrder so = maps.get(rs.getLong("student_order_id"));
                so.addChild(child);
            }
        }
    }

    private Child fillChild(ResultSet rs) throws SQLException {
        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        Child child = new Child(surName, givenName, patronymic, dateOfBirth);

        child.setCertificateNumber(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());

        Long roId = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, roArea, roName);
        child.setIssueDepartment(ro);

        Address address = new Address();
        Street street = new Street(rs.getLong("c_street_code"), "");

        address.setStreet(street);
        address.setPostCode(rs.getString("c_post_index"));
        address.setBuilding(rs.getString("c_building"));
        address.setExtension(rs.getString("c_extension"));
        address.setApartment(rs.getString("c_apartment"));
        child.setAddress(address);

        return child;
    }
}
