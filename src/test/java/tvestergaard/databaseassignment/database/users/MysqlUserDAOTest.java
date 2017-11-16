package tvestergaard.databaseassignment.database.users;

import org.junit.Before;
import org.junit.Test;
import tvestergaard.databaseassignment.database.DefaultMysqlDataSource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MysqlUserDAOTest
{

    private MysqlUserDAO dao;

    @Before
    public void setUp()
    {
        this.dao = new MysqlUserDAO(new DefaultMysqlDataSource());
    }

    @Test
    public void getUsers() throws Exception
    {
        List<User> users = dao.getUsers();
        assertNotNull(users);
        assertEquals(8, users.size());
    }

    @Test
    public void getUserByID() throws Exception
    {
        User user = dao.getUser(1);
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Anders And", user.getUsername());
        assertEquals("1234", user.getPassword());
        assertEquals(true, user.isAdmin());
    }


    @Test(expected = UnknownUserIdException.class)
    public void getUserByIDThrowsUnknownUserIdException() throws Exception
    {
        dao.getUser(99);
    }

    @Test
    public void getUserByUsername() throws Exception
    {
        User user = dao.getUser("Anders And");
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Anders And", user.getUsername());
        assertEquals("1234", user.getPassword());
        assertEquals(true, user.isAdmin());

    }

    @Test(expected = UnknownUsernameException.class)
    public void getUserByUsernameThrowsUnknownUsernameException() throws Exception
    {
        dao.getUser("3243");
    }
}