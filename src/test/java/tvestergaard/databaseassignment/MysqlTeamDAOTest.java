package tvestergaard.databaseassignment;

import org.junit.Test;

import java.util.List;

public class MysqlTeamDAOTest
{
	@Test
	public void getTeams() throws Exception
	{
		MysqlTeamDAO dao   = new MysqlTeamDAO(new DefaultMysqlDataSource());
		List<Team>   teams = dao.getTeams();
		for (Team team : teams) {
			System.out.println(team);
			for (User user : team.getMembers())
				System.out.println("\t" + user.toString());
		}
	}

	@Test
	public void getTeam() throws Exception
	{
		MysqlTeamDAO dao  = new MysqlTeamDAO(new DefaultMysqlDataSource());
		Team         team = dao.getTeam(1);
		System.out.println(team);
	}

	@Test
	public void getTeam1() throws Exception
	{
		MysqlTeamDAO dao  = new MysqlTeamDAO(new DefaultMysqlDataSource());
		Team         team = dao.getTeam("B");
		System.out.println(team);
	}
}