package tvestergaard.databaseassignment.database.teams;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DuplicateTeamExceptionTest
{
	@Test
	public void getDuplicateTeam() throws Exception
	{
		Team                   expected  = new Team(0, null);
		DuplicateTeamException exception = new DuplicateTeamException(expected);
		assertSame(expected, exception.getDuplicateTeam());
	}
}