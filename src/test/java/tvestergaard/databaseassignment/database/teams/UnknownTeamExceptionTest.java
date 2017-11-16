package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UnknownTeamExceptionTest
{

    @Test
    public void getUnknownTeam() throws Exception
    {
        Team expected = new Team(0, null);
        UnknownTeamException exception = new UnknownTeamException(expected);
        assertSame(expected, exception.getUnknownTeam());
    }
}