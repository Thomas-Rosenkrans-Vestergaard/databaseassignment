package tvestergaard.databaseassignment.database.teams;

import org.junit.Before;
import org.junit.Test;
import tvestergaard.databaseassignment.DefaultMysqlDataSource;
import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
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

	@Test(expected = UnknownTeamIdException.class)
	public void testGetTeamThrowsUnknownTeamIdException() throws Exception
	{
		// Negative test
		dao.getTeam(99);
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

	@Test(expected = UnknownTeamNameException.class)
	public void testGetTeamThrowsUnknownTeamNameException() throws Exception
	{
		// negative test
		dao.getTeam("Not a team name!");
	}

	@Test
	public void testGetTeamMembersByID() throws Exception
	{
		// positive test
		List<User> teamMembers = dao.getTeamMembers(1);
		assertNotNull(teamMembers);
		assertEquals(3, teamMembers.size());
	}

	@Test(expected = UnknownTeamIdException.class)
	public void testGetTeamMembersByIDThrowsUnknownTeamIdException() throws Exception
	{
		// negative test
		dao.getTeamMembers(99);
	}

	@Test
	public void testGetTeamMembersByTeamName() throws Exception
	{
		// positive test
		List<User> teamMembers = dao.getTeamMembers("A");
		assertNotNull(teamMembers);
		assertEquals(3, teamMembers.size());
	}

	@Test(expected = UnknownTeamNameException.class)
	public void testGetTeamMembersByTeamNameThrowsUnknownTeamNameException() throws Exception
	{
		// negative test
		dao.getTeamMembers("UNKNOWN TEAM NAME!");
	}
}