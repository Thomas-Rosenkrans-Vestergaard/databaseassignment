package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.DAOException;

public class UnknownTeamNameException extends DAOException
{

    /**
     * The unknown team name.
     */
    private String unknownTeamName;

    /**
     * Creates a new {@link UnknownTeamNameException}.
     *
     * @param unknownTeamName The unknown team name.
     */
    public UnknownTeamNameException(String unknownTeamName)
    {
        this.unknownTeamName = unknownTeamName;
    }

    /**
     * Returns the unknown team name.
     *
     * @return The unknown team name.
     */
    public String getUnknownTeamName()
    {
        return this.unknownTeamName;
    }
}
