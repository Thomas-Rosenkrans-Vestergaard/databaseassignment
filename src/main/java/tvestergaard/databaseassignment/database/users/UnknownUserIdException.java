package tvestergaard.databaseassignment.database.users;

public class UnknownUserIdException extends UserDAOException
{

	/**
	 * The unknown unknownId.
	 */
	private int unknownId;

	/**
	 * Creates a new {@link UnknownUserIdException}.
	 *
	 * @param unknownId The unknown unknownId.
	 */
	public UnknownUserIdException(int unknownId)
	{
		this.unknownId = unknownId;
	}

	/**
	 * Returns the unknown unknownId.
	 *
	 * @return The unknown unknownId.
	 */
	public int getUnknownId()
	{
		return this.unknownId;
	}
}
