package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UnknownUserIdExceptionTest
{
    @Test
    public void getUnknownId() throws Exception
    {
        int expected = 34;
        UnknownUserIdException exception = new UnknownUserIdException(expected);
        assertSame(expected, exception.getUnknownId());
    }
}