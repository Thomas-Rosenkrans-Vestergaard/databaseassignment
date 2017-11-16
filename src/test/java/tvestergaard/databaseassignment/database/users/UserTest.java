package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest
{

    @Test
    public void getId() throws Exception
    {
        int expected = 7;
        User user = new User(expected, null, null, false);
        assertSame(expected, user.getId());
    }

    @Test
    public void getUsername() throws Exception
    {
        String expected = "Username";
        User user = new User(0, expected, null, false);
        assertSame(expected, user.getUsername());
    }

    @Test
    public void getPassword() throws Exception
    {
        String expected = "UserPassword";
        User user = new User(0, null, expected, false);
        assertSame(expected, user.getPassword());
    }

    @Test
    public void isAdmin() throws Exception
    {
        boolean expected = true;
        User user = new User(0, null, null, expected);
        assertSame(expected, user.isAdmin());
    }

    @Test
    public void setUsername() throws Exception
    {
        User user = new User(0, null, null, false);
        assertNull(user.getUsername());
        String expected = "Username";
        user.setUsername(expected);
        assertSame(expected, user.getUsername());
    }

    @Test
    public void setPassword() throws Exception
    {
        User user = new User(0, null, null, false);
        assertNull(user.getPassword());
        String expected = "userpassword";
        user.setPassword(expected);
        assertSame(expected, user.getPassword());
    }

    @Test
    public void setAdmin() throws Exception
    {
        User user = new User(0, null, null, false);
        boolean expected = true;
        user.setAdmin(expected);
        assertSame(expected, user.isAdmin());
    }

    @Test
    public void testEquals() throws Exception
    {
        User userA = new User(1, "A", "Password", false);
        User userB = new User(1, "B", "Password", false);

        assertEquals(userA, userA);
        assertNotEquals(userA, userB);
    }

    @Test
    public void testHashCode() throws Exception
    {
        User userA = new User(1, "A", "Password", false);
        User userB = new User(1, "A", "Password", false);
        User userC = new User(1, "B", "Password", false);

        assertEquals(userA.hashCode(), userB.hashCode());
        assertNotEquals(userA.hashCode(), userC.hashCode());
    }
}