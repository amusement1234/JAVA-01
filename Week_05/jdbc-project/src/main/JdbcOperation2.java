package main;

import model.UsersModel;
import utils.HikariUtils;
import utils.JDBCUtils;

import java.sql.*;

public class JdbcOperation2 {

    public boolean addUser(String username, String password) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into users(username,password) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, password);
        int result = preparedStatement.executeUpdate();
        JDBCUtils.close(connection, preparedStatement);
        return result > 0;
    }

    public boolean updateUser(int id, String username, String password) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update users set username=?,password=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, password);
        preparedStatement.setObject(3, id);
        int result = preparedStatement.executeUpdate();
        JDBCUtils.close(connection, preparedStatement);
        return result > 0;
    }

    public boolean deleteUser(int id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "delete from users where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, id);
        int result = preparedStatement.executeUpdate();
        JDBCUtils.close(connection, preparedStatement);
        return result > 0;
    }

    public UsersModel getUser(int id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from users where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        UsersModel usersModel = new UsersModel();
        while (resultSet.next()) {
            usersModel.setId(resultSet.getInt("id"));
            usersModel.setUsername(resultSet.getString("username"));
            usersModel.setPassword(resultSet.getString("password"));
        }
        HikariUtils.close(connection, preparedStatement, resultSet);
        return usersModel;
    }

}
