import java.sql.*;
import java.text.DateFormat;

class JdbcDemo {

    final static int MAX = 1000000;

    public static void main(String[] args) throws SQLException {


        long start = System.currentTimeMillis();


        JdbcDemo jdbcDemo = new JdbcDemo();
        jdbcDemo.insert();


        System.out.println("耗时： " + (System.currentTimeMillis() - start) + "ms");

    }


    Connection connection = JDBCUtils.getConnection();

    public void insert() throws SQLException {

        String sql = "insert into user(user_name,user_pwd,is_enable,is_delete,create_time) values(?,?,?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        for (int i = 1; i <= MAX; i++) {
            pstmt.setString(1, "张三" + i);
            pstmt.setString(2, "123456");
            pstmt.setBoolean(3, true);
            pstmt.setBoolean(4, true);
            pstmt.setObject(5, getThisDate());
            pstmt.addBatch();
        }
        int[] r = pstmt.executeBatch();

    }

    public String getThisDate() {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);  //只有年月日  与MySQL中的DATE相对应
        Time time = new Time(currentTimeMillis);  //只有时分秒  与MySQL中的TIME相对应
        String datetime = date.toString() + " " + time.toString();
        return datetime;
    }
}
