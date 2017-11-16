package tvestergaard.databaseassignment.database;

public interface DAO
{

    /**
     * Closes the resource used by the {@link DAO}.
     *
     * @throws CloseDAOException When the {@link DAO} cannot be closed.
     */
    void close() throws CloseDAOException;
}
