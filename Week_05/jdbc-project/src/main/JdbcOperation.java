package main;

import model.UsersModel;
import utils.HikariUtils;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcOperation {

    public boolean addUser(String username, String password) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("insert into users(username,password) values('" + username + "','" + password + "')");
        JDBCUtils.close(connection, statement);
        return result > 0;
    }

    public boolean updateUser(int id, String username, String password) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("update users set username='" + username + "',password='" + password + "' where id=" + id);
        JDBCUtils.close(connection, statement);
        return result > 0;
    }

    public boolean deleteUser(int id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("delete from users where id=" + id);
        JDBCUtils.close(connection, statement);
        return result > 0;
    }

    public UsersModel getUser(int id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where id='" + id + "'");
        JDBCUtils.close(connection, statement, resultSet);

        UsersModel usersModel = new UsersModel();
        while (resultSet.next()) {
            usersModel.setId(resultSet.getInt("id"));
            usersModel.setUsername(resultSet.getString("username"));
            usersModel.setPassword(resultSet.getString("password"));
        }
        HikariUtils.close(connection, statement, resultSet);
        return usersModel;
    }

}
