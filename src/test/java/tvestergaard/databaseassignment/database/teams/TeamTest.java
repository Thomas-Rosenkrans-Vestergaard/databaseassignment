package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;
import tvestergaard.databaseassignment.database.users.*;

import static org.junit.Assert.*;

public class TeamTest
{

    @Test
    public void getName() throws Exception
    {
        String expected = "Name";
        Team team = new Team(0, expected);
        assertSame(expected, team.getName());
    }

    @Test
    public void setName() throws Exception
    {
        Team team = new Team(0, null);
        assertNull(team.getName());
        String expected = "Name";
        team.setName(expected);
        assertSame(expected, team.getName());
    }

    @Test
    public void addMember() throws Exception
    {
        Team team = new Team(0, null);
        assertEquals(0, team.getMembers().size());
        team.addMember(new User(0, null, null, false));
        team.addMember(new User(0, null, null, false));
        team.addMember(new User(0, null, null, false));
        assertEquals(3, team.getMembers().size());
    }

    @Test
    public void getMembers() throws Exception
    {
        Team team = new Team(0, null);
        assertEquals(0, team.getMembers().size());

        User userA = new User(0, null, null, false);
        User userB = new User(0, null, null, false);
        User userC = new User(0, null, null, false);

        team.addMember(userA);
        team.addMember(userB);
        team.addMember(userC);

        assertEquals(3, team.getMembers().size());
        assertSame(userA, team.getMembers().get(0));
        assertSame(userB, team.getMembers().get(1));
        assertSame(userC, team.getMembers().get(2));
    }

    @Test
    public void getMemberByID() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(5, null, null, false);
        team.addMember(user);
        assertSame(user, team.getMember(5));
    }

    @Test(expected = UnknownUserIdException.class)
    public void getMemberByIDThrowsUnknownTeamIdException() throws Exception
    {
        Team team = new Team(0, null);
        team.getMember(5);
    }

    @Test
    public void getMemberByUserReference() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(5, null, null, false);
        team.addMember(user);
        assertSame(user, team.getMember(UserReference.of(5)));
    }

    @Test(expected = UnknownUserReferenceException.class)
    public void getMemberByUserReferenceThrowsUnknownUserReferenceException() throws Exception
    {
        Team team = new Team(0, null);
        team.getMember(UserReference.of(5));
    }

    @Test
    public void getMemberByTeamName() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(0, "A", null, false);
        team.addMember(user);
        assertSame(user, team.getMember("A"));
    }

    @Test(expected = UnknownUsernameException.class)
    public void getMemberByTeamNameThrowsUnknownUsernameException() throws Exception
    {
        Team team = new Team(0, null);
        team.getMember("A");
    }

    @Test
    public void removeMemberByID() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(0, "A", null, false);
        team.addMember(user);
        assertTrue(team.hasMember(0));
        team.removeMember(0);
        assertFalse(team.hasMember(0));
    }

    @Test(expected = UnknownUserIdException.class)
    public void removeMemberByIDThrowsUnknownUserIdException() throws Exception
    {
        Team team = new Team(0, null);
        team.removeMember(0);
    }

    @Test
    public void removeMemberByUserReference() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(0, "A", null, false);
        team.addMember(user);
        assertTrue(team.hasMember(0));
        team.removeMember(UserReference.of(0));
        assertFalse(team.hasMember(0));
    }

    @Test(expected = UnknownUserReferenceException.class)
    public void removeMemberByUserReferenceThrowsUnknownUserReferenceException() throws Exception
    {
        Team team = new Team(0, null);
        team.removeMember(UserReference.of(0));
    }

    @Test
    public void removeMemberByUser() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(0, "A", null, false);
        team.addMember(user);
        assertTrue(team.hasMember(0));
        team.removeMember(user);
        assertFalse(team.hasMember(0));
    }

    @Test(expected = UnknownUserException.class)
    public void removeMemberByUserThrowsUnknownUserException() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(0, "A", null, false);
        team.removeMember(user);
    }

    @Test
    public void hasMemberByID() throws Exception
    {
        Team team = new Team(0, null);
        assertFalse(team.hasMember(1));
        team.addMember(new User(1, null, null, false));
        assertTrue(team.hasMember(1));
    }

    @Test
    public void hasMemberByUserReference() throws Exception
    {
        Team team = new Team(0, null);
        assertFalse(team.hasMember(UserReference.of(1)));
        team.addMember(new User(1, null, null, false));
        assertTrue(team.hasMember(UserReference.of(1)));
    }

    @Test
    public void hasMemberByUsername() throws Exception
    {
        Team team = new Team(0, null);
        assertFalse(team.hasMember("A"));
        team.addMember(new User(1, "A", null, false));
        assertTrue(team.hasMember("A"));
    }

    @Test
    public void hasMemberByUserInstance() throws Exception
    {
        Team team = new Team(0, null);
        User user = new User(1, "A", null, false);
        assertFalse(team.hasMember(user));
        team.addMember(user);
        assertTrue(team.hasMember(user));
    }
}