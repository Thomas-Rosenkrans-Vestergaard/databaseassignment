package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserReferenceTest
{
    @Test
    public void of() throws Exception
    {
        int userId = 234235;
        UserReference expected = new UserReference(userId);
        UserReference actual = UserReference.of(userId);
        assertEquals(expected, actual);
    }

    @Test
    public void getId() throws Exception
    {
        int expected = 23423;
        UserReference userReference = new UserReference(expected);
        assertEquals(expected, userReference.getId());
    }
}