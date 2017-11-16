package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.DAOException;

public class TeamNameTakenException extends DAOException
{

    /**
     * The name that was taken.
     */
    private String takenName;

    /**
     * Creates a new {@link TeamNameTakenException}.
     *
     * @param takenName The name that was taken.
     */
    public TeamNameTakenException(String takenName)
    {
        this.takenName = takenName;
    }

    /**
     * Returns the name that was taken.
     *
     * @return The name that was taken.
     */
    public String getTakenName()
    {
        return this.takenName;
    }
}
