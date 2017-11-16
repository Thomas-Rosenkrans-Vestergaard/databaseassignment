package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UnknownTeamIdExceptionTest
{
	@Test
	public void getUnknownId() throws Exception
	{
		int                    expected  = 5;
		UnknownTeamIdException exception = new UnknownTeamIdException(expected);
		assertSame(expected, exception.getUnknownId());
	}
}