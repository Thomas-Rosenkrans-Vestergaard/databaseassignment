package tvestergaard.databaseassignment.database.users;

import org.junit.Before;
import org.junit.Test;
import tvestergaard.databaseassignment.database.MysqlMemoryDatabase;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MysqlUserDAOTest
{

    private MysqlUserDAO dao;

    @Before
    public void setUp() throws Exception
    {
        if (dao == null)
            dao = new MysqlUserDAO(MysqlMemoryDatabase.getDataSource());

        MysqlMemoryDatabase.reset();
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
        User user = dao.getUser(UserReference.of(1));
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("Anders And", user.getUsername());
        assertEquals("1234", user.getPassword());
        assertEquals(true, user.isAdmin());
    }


    @Test(expected = UnknownUserReferenceException.class)
    public void getUserByIDThrowsUnknownUserIdException() throws Exception
    {
        dao.getUser(UserReference.of(100));
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

    @Test(expected = UnknownUserReferenceException.class)
    public void deleteUser() throws Exception
    {
        User before = dao.getUser(UserReference.of(1));
        dao.deleteUser(before);
        dao.getUser(UserReference.of(1));
    }

    @Test(expected = UnknownUserReferenceException.class)
    public void deleteUserThrowsUnknownUserReferenceException() throws Exception
    {
        User before = dao.getUser(UserReference.of(1));
        dao.deleteUser(before);
        dao.getUser(UserReference.of(1));
    }
}