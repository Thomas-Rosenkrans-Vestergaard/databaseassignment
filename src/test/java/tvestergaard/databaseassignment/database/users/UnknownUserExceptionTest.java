package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class UnknownUserExceptionTest
{
    @Test
    public void getUnknownUser() throws Exception
    {
        User expected = new User(1, null, null, false);
        UnknownUserException exception = new UnknownUserException(expected);
        assertSame(expected, exception.getUnknownUser());
    }

    @Test
    public void getUserReference() throws Exception
    {
        User expected = new User(1, null, null, false);
        UnknownUserException exception = new UnknownUserException(expected);
        assertSame(expected, exception.getUserReference());
    }

    @Test
    public void getUnknownId() throws Exception
    {
        int expected = 324;
        User user = new User(expected, null, null, false);
        UnknownUserException exception = new UnknownUserException(user);
        assertEquals(expected, exception.getUnknownId());
    }
}