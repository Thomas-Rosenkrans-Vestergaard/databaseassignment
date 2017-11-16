package tvestergaard.databaseassignment.database.teams;

import org.junit.Before;
import org.junit.Test;
import tvestergaard.databaseassignment.DefaultMysqlDataSource;
import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MysqlTeamDAOTest
{

	private MysqlTeamDAO dao;

	@Before
	public void setUp()
	{
		this.dao = new MysqlTeamDAO(new DefaultMysqlDataSource());
	}

	@Test
	public void testGetTeamMembersInValidTeamID() throws Exception
	{
		// Negative test
		List<User> teamMembers = dao.getTeamMembers(99);
		assertNotNull(teamMembers);
		assertTrue(teamMembers.isEmpty());
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
	public void testGetTeamByID() throws Exception
	{
		// positive test
		Team team = dao.getTeam(1);
		assertNotNull(team);
		assertEquals(team.getName(), "A");
		assertEquals(team.getMembers().size(), 3);
	}

	@Test
	public void testGetTeamByInvalidID() throws Exception
	{
		// Negative test
		Team team = dao.getTeam(99);
		assertNull(team);
	}

	@Test
	public void testGetTeamByTeamName() throws Exception
	{
		// positive test
		Team team = dao.getTeam("A");
		assertNotNull(team);
		assertEquals(team.getId(), 1);
		assertEquals(team.getMembers().size(), 3);
	}

	@Test
	public void testGetTeamByInvalidTeamName() throws Exception
	{
		// negative test
		Team team = dao.getTeam("Not a team name!");
		assertNull(team);
	}

}