package tvestergaard.databaseassignment.database;

/**
 * Thrown when a {@link DAO} cannot be created.
 */
public class OpenDAOException extends Exception
{

    /**
     * The cause of the exception.
     */
    private Exception cause;

    /**
     * Creates a new {@link OpenDAOException}.
     *
     * @param cause The cause of the exception.
     */
    public OpenDAOException(Exception cause)
    {
        this.cause = cause;
    }

    /**
     * Returns the cause of the exception.
     *
     * @return The cause of the exception.
     */
    @Override
    public Exception getCause()
    {
        return this.cause;
    }
}
