package utils;

import java.sql.*;

public class JDBCUtils {
    private JDBCUtils() {
    }

    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mybase?useSSL=false";
            String username = "root";
            String password = "123456";
            con = DriverManager.getConnection(url, username, password);
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
