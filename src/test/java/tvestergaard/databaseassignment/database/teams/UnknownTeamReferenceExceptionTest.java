package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnknownTeamReferenceExceptionTest
{

    @Test
    public void getTeamReference() throws Exception
    {
        int expected = 324;
        TeamReference teamReference = new TeamReference(expected);
        assertEquals(expected, teamReference.getId());
    }
}