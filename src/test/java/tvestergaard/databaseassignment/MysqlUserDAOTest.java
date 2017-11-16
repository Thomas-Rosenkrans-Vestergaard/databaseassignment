package tvestergaard.databaseassignment;

import org.junit.Test;
import tvestergaard.databaseassignment.database.users.MysqlUserDAO;
import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

public class MysqlUserDAOTest
{
	@Test
	public void getUsers() throws Exception
	{
		MysqlUserDAO dao   = new MysqlUserDAO(new DefaultMysqlDataSource());
		List<User>   users = dao.getUsers();
		for (User user : users)
			System.out.println(user);
	}

	@Test
	public void getUser() throws Exception
	{
		MysqlUserDAO dao  = new MysqlUserDAO(new DefaultMysqlDataSource());
		User         user = dao.getUser(1);
		System.out.println(user);
	}

	@Test
	public void getUser1() throws Exception
	{
		MysqlUserDAO dao  = new MysqlUserDAO(new DefaultMysqlDataSource());
		User         user = dao.getUser("Pluto");
		System.out.println(user);
	}
}