package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;
import tvestergaard.databaseassignment.database.users.User;

import static org.junit.Assert.*;

public class TeamBuilderTest
{

    @Test
    public void getName() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        String expected = "Name";
        builder.setName(expected);
        assertSame(expected, builder.getName());
    }

    @Test
    public void setName() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        String expected = "Name";
        assertNull(builder.getName());
        builder.setName(expected);
        assertSame(expected, builder.getName());
    }

    @Test
    public void addMember() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        assertEquals(0, builder.getMembers().size());
        User user = new User(0, "", "", false);
        builder.addMember(user);
        assertEquals(1, builder.getMembers().size());
        assertSame(user, builder.getMembers().get(0));
    }

    @Test
    public void getMembers() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        User user = new User(0, "", "", false);
        builder.addMember(user);
        assertEquals(1, builder.getMembers().size());
        assertSame(user, builder.getMembers().get(0));
    }
}