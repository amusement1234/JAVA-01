
import main.JdbcOperation;
import model.UsersModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;


public class JdbcOperationTest {

    private JdbcOperation jdbcOperation;

    @Before
    public void setUp() throws Exception {
        jdbcOperation = new JdbcOperation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser() throws SQLException {
        boolean result = jdbcOperation.addUser("张三", "123");
        Assert.assertEquals(true, result);
    }

    @Test
    public void updateUser() throws SQLException {
        boolean result = jdbcOperation.updateUser(2, "张三", "1234");
        Assert.assertEquals(true, result);
    }

    @Test
    public void deleteUser() throws SQLException {
        boolean result = jdbcOperation.deleteUser(6);
        Assert.assertEquals(true, result);

    }

    @Test
    public void getUser() throws SQLException {
        UsersModel usersModel = jdbcOperation.getUser(1);
        Assert.assertEquals(1, usersModel.getId());
    }


}