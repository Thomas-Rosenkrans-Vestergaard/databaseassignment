package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class UnknownUserReferenceExceptionTest
{

    @Test
    public void getUserReference() throws Exception
    {
        UserReference expected = UserReference.of(199);
        UnknownUserReferenceException exception = new UnknownUserReferenceException(expected);
        assertSame(expected, exception.getUserReference());
    }

    @Test
    public void getUnknownId() throws Exception
    {
        int expected = 234234;
        UserReference userReference = UserReference.of(expected);
        UnknownUserReferenceException exception = new UnknownUserReferenceException(userReference);
        assertEquals(expected, exception.getUnknownId());
    }
}