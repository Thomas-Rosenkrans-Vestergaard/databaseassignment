package tvestergaard.databaseassignment.database.teams;

public class TeamReference
{

    /**
     * The id referencing the {@link Team}.
     */
    private int id;

    /**
     * Creates a new {@link TeamReference}.
     *
     * @param id The id referencing the {@link Team}.
     */
    public TeamReference(int id)
    {
        this.id = id;
    }

    /**
     * Factory to create {@link TeamReference}s.
     *
     * @param id The id to create the {@link TeamReference} for.
     * @return The newly created {@link TeamReference}.
     */
    public static TeamReference of(int id)
    {
        return new TeamReference(id);
    }

    /**
     * Returns the id referencing the {@link Team}.
     *
     * @return The id referencing the {@link Team}.
     */
    public int getId()
    {
        return this.id;
    }
}
