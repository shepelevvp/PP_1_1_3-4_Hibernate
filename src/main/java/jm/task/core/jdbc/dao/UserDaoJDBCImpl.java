package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS `newdbkata`.`USERS` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(200) NULL,\n" +
                "  `lastname` VARCHAR(200) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = MyISAM;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS `newdbkata`.`USERS`;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String removeUserById = "DELETE FROM USERS WHERE ID=?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM USERS";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM USERS";

        try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}
