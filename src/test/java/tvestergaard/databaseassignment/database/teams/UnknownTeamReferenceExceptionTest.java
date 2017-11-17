package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnknownTeamReferenceExceptionTest
{

    @Test
    public void getTeamReference() throws Exception
    {
        TeamReference expected = new TeamReference(56);
        UnknownTeamReferenceException exception = new UnknownTeamReferenceException(expected);
        assertEquals(expected, exception.getTeamReference());
    }

    @Test
    public void getUnknownId() throws Exception
    {
        int expected = 56;
        TeamReference teamReference = new TeamReference(expected);
        UnknownTeamReferenceException exception = new UnknownTeamReferenceException(teamReference);
        assertEquals(expected, exception.getUnknownId());
    }
}