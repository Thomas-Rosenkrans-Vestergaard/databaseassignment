package tvestergaard.databaseassignment.database.teams;

public class UnknownTeamReferenceException extends UnknownTeamIdException
{

    /**
     * The unknown {@link TeamReference}.
     */
    private TeamReference teamReference;

    /**
     * Creates a new {@link UnknownTeamReferenceException}.
     *
     * @param teamReference The unknown {@link TeamReference}.
     */
    public UnknownTeamReferenceException(TeamReference teamReference)
    {
        super(teamReference.getId());
        this.teamReference = teamReference;
    }

    /**
     * Returns the unknown {@link TeamReference}.
     *
     * @return The unknown {@link TeamReference}.
     */
    public TeamReference getTeamReference()
    {
        return this.teamReference;
    }
}
