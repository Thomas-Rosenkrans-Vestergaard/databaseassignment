package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;
import tvestergaard.databaseassignment.database.users.UnknownUsernameException;

import static org.junit.Assert.assertSame;

public class UnknownTeamNameExceptionTest
{

    @Test
    public void getUnknownTeamName() throws Exception
    {
        String expected = "TeamName";
        UnknownUsernameException exception = new UnknownUsernameException(expected);
        assertSame(expected, exception.getUnknownUsername());
    }
}