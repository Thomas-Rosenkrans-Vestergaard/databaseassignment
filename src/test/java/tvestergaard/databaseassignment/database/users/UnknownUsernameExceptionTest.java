package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UnknownUsernameExceptionTest
{
	@Test
	public void getUnknownUsername() throws Exception
	{
		String                   expected  = "Username";
		UnknownUsernameException exception = new UnknownUsernameException(expected);
		assertSame(expected, exception.getUnknownUsername());
	}
}