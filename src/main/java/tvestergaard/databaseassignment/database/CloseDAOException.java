package tvestergaard.databaseassignment.database;

/**
 * Thrown when an exception occurs while closing a {@link DAO}.
 */
public class CloseDAOException extends DAOException
{

    /**
     * The cause of the error.
     */
    private Exception cause;

    /**
     * Creates a new {@link CloseDAOException}.
     *
     * @param cause The exception thrown while closing the {@link DAO}.
     */
    public CloseDAOException(Exception cause)
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
