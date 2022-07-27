package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao{

    //"SELECT street_code, street_name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%" + pattern +"%')";
    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street " +
            "WHERE street_name ILIKE ?";
    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office " +
            "WHERE p_office_area_id = ?";
    private static final String GET_REGISTER = "SELECT * FROM jc_register_office " +
            "WHERE r_office_area_id = ?";

    private Connection getConnection() throws SQLException {
//        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_USER),
                Config.getProperty(Config.DB_PASSWORD)
        );
        return connection;
    }

    @Override
    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_STREET)){

            stmt.setString(1, "%" + pattern + "%");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Street str = new Street(resultSet.getInt("street_code"), resultSet.getString("street_name"));
                result.add(str);
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_PASSPORT)){

            stmt.setString(1, areaId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                PassportOffice str = new PassportOffice(
                        resultSet.getLong("p_office_id"),
                        resultSet.getString("p_office_area_id"),
                        resultSet.getString("p_office_name"));
                result.add(str);
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_REGISTER)){

            stmt.setString(1, areaId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                RegisterOffice str = new RegisterOffice(
                        resultSet.getLong("r_office_id"),
                        resultSet.getString("r_office_area_id"),
                        resultSet.getString("r_office_name"));
                result.add(str);
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }
}
