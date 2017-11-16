package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class TeamNameTakenExceptionTest
{

    @Test
    public void getTakenName() throws Exception
    {
        String expected = "takenName";
        TeamNameTakenException exception = new TeamNameTakenException(expected);
        assertSame(expected, exception.getTakenName());
    }
}