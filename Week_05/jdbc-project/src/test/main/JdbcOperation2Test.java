import main.JdbcOperation2;
import model.UsersModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class JdbcOperation2Test {

    private JdbcOperation2 jdbcOperation2;

    @Before
    public void setUp() throws Exception {
        jdbcOperation2 = new JdbcOperation2();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser() throws SQLException {
        boolean result = jdbcOperation2.addUser("张三", "123");
        Assert.assertEquals(true, result);
    }

    @Test
    public void updateUser() throws SQLException {
        boolean result = jdbcOperation2.updateUser(2,"张三", "12345");
        Assert.assertEquals(true, result);
    }

    @Test
    public void deleteUser() throws SQLException {
        boolean result = jdbcOperation2.deleteUser(8);
        Assert.assertEquals(true, result);

    }

    @Test
    public void getUser() throws SQLException {
        UsersModel usersModel= jdbcOperation2.getUser(1);
        Assert.assertEquals(1,usersModel.getId());
    }
}