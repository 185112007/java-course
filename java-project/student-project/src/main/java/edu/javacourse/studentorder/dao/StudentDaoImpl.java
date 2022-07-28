package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Address;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.StudentOrderStatus;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

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

            // Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            // Husband and Wife
            setParamsForAdult(stmt, 3, so.getHusband());
            setParamsForAdult(stmt, 18, so.getWife());

            // Marriage
            stmt.setString(33, so.getMarriageCertificateId());
            stmt.setLong(34,so.getMarriageOffice().getOfficeId());
            stmt.setDate(35,java.sql.Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()){
                result = keys.getLong(1);
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }

        return result;
    }

    private static void setParamsForAdult(PreparedStatement stmt, int start,  Adult adult) throws SQLException {
        stmt.setString(start, adult.getSurName());
        stmt.setString(start +1, adult.getGivenName());
        stmt.setString(start +2, adult.getPatronymic());
        stmt.setDate(start +3, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start +4, adult.getPassportSerialNumber());
        stmt.setString(start +5, adult.getPassportNumber());
        stmt.setDate(start +6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start +7, adult.getIssueDepartment().getOfficeId());
        Address husbandAddress = adult.getAddress();
        stmt.setString(start +8, husbandAddress.getPostCode());
        stmt.setLong(start +9, husbandAddress.getStreet().getStreetCode());
        stmt.setString(start +10, husbandAddress.getBuilding());
        stmt.setString(start +11, husbandAddress.getExtension());
        stmt.setString(start +12, husbandAddress.getApartment());
        stmt.setLong(start +13, adult.getUniversity().getUniversityId());
        stmt.setString(start +14, adult.getStudentId());
    }
}
