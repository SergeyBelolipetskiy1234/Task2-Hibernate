package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
      /*  String insert = "create table users (\n" +
                "id_user int (10) AUTO_INCREMENT,\n" +
                "name varchar(20) NOT NULL,\n" +
                "email varchar(50) NOT NULL,\n" +
                "password varchar(15) NOT NULL,\n" +
                "PRIMARY KEY (id_user)\n" +
                ");";  */
        String insert = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age SMALLINT NOT NULL, PRIMARY KEY (id))";
        try (PreparedStatement prepared = connection.prepareStatement(insert)) {
            prepared.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name , lastName , age ) VALUES (?, ?, ?)")) {
           // String sql = "INSERT INTO users (name , lastName , age ) VALUES (?, ?, ?)";
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM users WHERE id";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String query = "Truncate table users";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
