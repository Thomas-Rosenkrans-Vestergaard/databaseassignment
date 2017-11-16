package tvestergaard.databaseassignment.database.users;

public class UnknownUsernameException extends UserDAOException
{

	/**
	 * The unknown username.
	 */
	private String unknownUsername;

	/**
	 * Creates a new {@link UnknownUsernameException}.
	 *
	 * @param unknownUsername The unknown username.
	 */
	public UnknownUsernameException(String unknownUsername)
	{
		this.unknownUsername = unknownUsername;
	}

	/**
	 * Returns the unknown username.
	 *
	 * @return The unknown username.
	 */
	public String getUnknownUsername()
	{
		return this.unknownUsername;
	}
}
