package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TeamReferenceTest
{

    @Test
    public void of() throws Exception
    {
        int expected = 999;
        TeamReference teamReference = TeamReference.of(expected);
        assertEquals(expected, teamReference.getId());
    }

    @Test
    public void getId() throws Exception
    {
        int expected = 999;
        TeamReference teamReference = new TeamReference(expected);
        assertEquals(expected, teamReference.getId());
    }
}