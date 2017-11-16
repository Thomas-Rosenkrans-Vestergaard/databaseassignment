package tvestergaard.databaseassignment.database.teams;

public class DuplicateTeamException extends TeamDAOException
{

	/**
	 * The duplicate {@link Team}.
	 */
	private Team duplicateTeam;

	/**
	 * Creates a new {@link DuplicateTeamException}.
	 *
	 * @param duplicateTeam The duplicate {@link Team}.
	 */
	public DuplicateTeamException(Team duplicateTeam)
	{
		this.duplicateTeam = duplicateTeam;
	}

	/**
	 * Returns the duplicate {@link Team}.
	 *
	 * @return The duplicate {@link Team}.
	 */
	public Team getDuplicateTeam()
	{
		return this.duplicateTeam;
	}
}
