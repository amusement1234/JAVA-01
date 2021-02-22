import main.HikariOperation;
import model.UsersModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class HikariOperationTest {

    private HikariOperation hikariOperation;

    @Before
    public void setUp() throws Exception {
        hikariOperation = new HikariOperation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser() throws SQLException {
        boolean result = hikariOperation.addUser("张三", "123");
        Assert.assertEquals(true, result);
    }

    @Test
    public void updateUser() throws SQLException {
        boolean result = hikariOperation.updateUser(2,"张三", "123456");
        Assert.assertEquals(true, result);
    }

    @Test
    public void deleteUser() throws SQLException {
        boolean result = hikariOperation.deleteUser(10);
        Assert.assertEquals(true, result);

    }

    @Test
    public void getUser() throws SQLException {
        UsersModel usersModel= hikariOperation.getUser(1);
        Assert.assertEquals(1,usersModel.getId());
    }
}