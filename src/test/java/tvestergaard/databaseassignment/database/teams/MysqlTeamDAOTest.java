package tvestergaard.databaseassignment.database.teams;

import org.junit.Before;
import org.junit.Test;
import tvestergaard.databaseassignment.database.MysqlMemoryDatabase;
import tvestergaard.databaseassignment.database.users.User;
import tvestergaard.databaseassignment.database.users.UserReference;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class MysqlTeamDAOTest
{

    private MysqlTeamDAO dao;

    @Before
    public void setUp() throws Exception
    {
        if (dao == null)
            dao = new MysqlTeamDAO(MysqlMemoryDatabase.getDataSource());

        MysqlMemoryDatabase.reset();
    }

    @Test
    public void testGetAllTeams() throws Exception
    {
        List<Team> teams = dao.getTeams();
        assertNotNull(teams);
        assertFalse(teams.isEmpty());
        Team team = teams.get(0);
        assertEquals(team.getName(), "A");
        assertEquals(team.getMembers().size(), 3);
    }

    @Test
    public void getTeamByID() throws Exception
    {
        Team team = dao.getTeam(1);
        assertNotNull(team);
        assertEquals(team.getName(), "A");
        assertEquals(team.getMembers().size(), 3);
    }

    @Test(expected = UnknownTeamIdException.class)
    public void getTeamThrowsUnknownTeamIdException() throws Exception
    {
        dao.getTeam(99);
    }

    @Test
    public void getTeamByTeamReference() throws Exception
    {
        Team team = dao.getTeam(TeamReference.of(1));
        assertNotNull(team);
        assertEquals(team.getName(), "A");
        assertEquals(team.getMembers().size(), 3);
    }

    @Test(expected = UnknownTeamReferenceException.class)
    public void getTeamByTeamReferenceThrowsUnknownTeamReferenceException() throws Exception
    {
        dao.getTeam(TeamReference.of(100));
    }

    @Test
    public void getTeamByTeamName() throws Exception
    {
        Team team = dao.getTeam("A");
        assertNotNull(team);
        assertEquals(team.getId(), 1);
        assertEquals(team.getMembers().size(), 3);
    }

    @Test(expected = UnknownTeamNameException.class)
    public void getTeamByNameThrowsUnknownTeamNameException() throws Exception
    {
        dao.getTeam("Not a team name!");
    }

    @Test
    public void getTeamMembersByID() throws Exception
    {
        List<User> teamMembers = dao.getTeamMembers(1);
        assertNotNull(teamMembers);
        assertEquals(3, teamMembers.size());
    }

    @Test(expected = UnknownTeamIdException.class)
    public void getTeamMembersByIDThrowsUnknownTeamIdException() throws Exception
    {
        dao.getTeamMembers(99);
    }

    @Test
    public void getTeamMembersByTeamReference() throws Exception
    {
        List<User> teamMembers = dao.getTeamMembers(TeamReference.of(1));
        assertNotNull(teamMembers);
        assertEquals(3, teamMembers.size());
    }

    @Test(expected = UnknownTeamReferenceException.class)
    public void getTeamMembersByTeamReferenceThrownUnknownTeamReferenceException() throws Exception
    {
        dao.getTeamMembers(TeamReference.of(99));
    }

    @Test
    public void insertTeam() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        String expectedName = "AA";
        builder.setName(expectedName);
        builder.addMember(UserReference.of(1));
        builder.addMember(UserReference.of(2));
        Team result = dao.insertTeam(builder);
        assertEquals(expectedName, result.getName());
        assertTrue(result.hasMember(1));
        assertTrue(result.hasMember(2));
        assertFalse(result.hasMember(3));
    }

    @Test(expected = TeamNameTakenException.class)
    public void insertTeamThrowsTeamNameTakenException() throws Exception
    {
        TeamBuilder builder = new TeamBuilder();
        String expectedName = "A";
        builder.setName(expectedName);
        builder.addMember(UserReference.of(1));
        builder.addMember(UserReference.of(2));
        Team result = dao.insertTeam(builder);
        assertEquals(expectedName, result.getName());
        assertTrue(result.hasMember(1));
        assertTrue(result.hasMember(2));
        assertFalse(result.hasMember(3));
    }

    @Test
    public void updateTeam() throws Exception
    {
        Team before = dao.getTeam(1);
        assertEquals("A", before.getName());
        assertEquals(3, before.getMembers().size());
        before.setName("D");
        dao.updateTeam(before);
        Team after = dao.getTeam(1);
        assertEquals(before, after);
    }

    @Test(expected = UnknownTeamException.class)
    public void updateTeamThrowsUnknownTeamException() throws Exception
    {
        Team team = new Team(99, "D");
        dao.updateTeam(team);
    }

    @Test(expected = TeamNameTakenException.class)
    public void updateTeamThrowsTeamNameTakenException() throws Exception
    {
        Team before = dao.getTeam(1);
        assertEquals("A", before.getName());
        assertEquals(3, before.getMembers().size());
        before.setName("B");
        dao.updateTeam(before);
    }

    @Test
    public void deleteTeam() throws Exception
    {
        Team team = dao.getTeam(1);
        dao.deleteTeam(team);

        try {
            dao.getTeam(1);
            fail();
        } catch (Exception e) {

        }
    }

    @Test(expected = UnknownTeamReferenceException.class)
    public void deleteTeamThrowsUnknownTeamReferenceException() throws Exception
    {
        TeamReference teamReference = TeamReference.of(100);
        dao.deleteTeam(teamReference);
    }
}