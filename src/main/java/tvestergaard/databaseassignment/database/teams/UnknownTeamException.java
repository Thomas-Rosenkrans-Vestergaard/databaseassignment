package tvestergaard.databaseassignment.database.teams;

public class UnknownTeamException extends UnknownTeamReferenceException
{

    /**
     * The unknown {@link Team}.
     */
    private Team unknownTeam;

    /**
     * Creates a new {@link UnknownTeamException}.
     *
     * @param unknownTeam The unknown {@link Team}.
     */
    public UnknownTeamException(Team unknownTeam)
    {
        super(unknownTeam);

        this.unknownTeam = unknownTeam;
    }

    /**
     * Returns the unknown {@link Team}.
     *
     * @return The unknown {@link Team}.
     */
    public Team getUnknownTeam()
    {
        return this.unknownTeam;
    }
}
