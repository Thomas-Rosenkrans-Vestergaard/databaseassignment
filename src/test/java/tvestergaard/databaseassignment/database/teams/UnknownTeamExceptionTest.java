package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void getTeamReference() throws Exception
    {
        Team expected = new Team(0, null);
        UnknownTeamException exception = new UnknownTeamException(expected);
        assertSame(expected, exception.getTeamReference());
    }

    @Test
    public void getUnknownId() throws Exception
    {
        int expected = 5;
        Team team = new Team(expected, null);
        UnknownTeamException exception = new UnknownTeamException(team);
        assertEquals(expected, exception.getUnknownId());
    }
}