package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.DAOException;

public class UnknownTeamIdException extends DAOException
{

    /**
     * The unknown team id.
     */
    private int unknownId;

    /**
     * Creates a new {@link UnknownTeamIdException}.
     *
     * @param unknownId The unknown team id.
     */
    public UnknownTeamIdException(int unknownId)
    {
        this.unknownId = unknownId;
    }

    /**
     * Returns the unknown team id.
     *
     * @return The unknown team id.
     */
    public int getUnknownId()
    {
        return this.unknownId;
    }
}
