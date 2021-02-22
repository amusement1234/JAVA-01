package utils;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class HikariUtils {

    private HikariUtils() {
    }

    private static Connection con;

    static {
        try {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybase?useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            con = dataSource.getConnection();

        } catch (Exception ex) {
            throw new RuntimeException(ex + "数据库连接失败");
        }
    }

    /*
     * 定义静态方法,返回数据库的连接对象
     */
    public static Connection getConnection() {
        return con;
    }


    private static void closeConn(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
    }

    private static void closeStatement(Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
            }
        }
    }

    private static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public static void close(Connection con, Statement stat) {
        closeStatement(stat);
        closeConn(con);
    }

    public static void close(Connection con, Statement stat, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(stat);
        closeConn(con);
    }

}
