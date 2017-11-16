package tvestergaard.databaseassignment.database.users;

import org.junit.Test;
import tvestergaard.databaseassignment.database.teams.UnknownTeamIdException;

public class MysqlUserDAOTest
{
	@Test
	public void getUsers() throws Exception
	{
	}

	@Test
	public void getUserByID() throws Exception
	{
	}


	@Test(expected = UnknownTeamIdException.class)
	public void getUserByIDThrowsUnknownUserIdException() throws Exception
	{

	}

	@Test
	public void getUserByUsername() throws Exception
	{
	}

	@Test(expected = UnknownUsernameException.class)
	public void getUserByUsernameThrowsUnknownUsernameException() throws Exception
	{

	}
}