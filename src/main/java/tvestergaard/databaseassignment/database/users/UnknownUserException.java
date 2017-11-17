package tvestergaard.databaseassignment.database.users;

public class UnknownUserException extends UnknownUserReferenceException
{

    /**
     * The unknown {@link User}.
     */
    private User unknownUser;

    /**
     * Creates a new {@link UnknownUserException}.
     *
     * @param unknownUser The unknown {@link User}.
     */
    public UnknownUserException(User unknownUser)
    {
        super(unknownUser);

        this.unknownUser = unknownUser;
    }

    /**
     * Returns the unknown {@link User}.
     *
     * @return The unknown {@link User}.
     */
    public User getUnknownUser()
    {
        return this.unknownUser;
    }
}
