package tvestergaard.databaseassignment.database.users;

public class UnknownUserReferenceException extends UnknownUserIdException
{

    /**
     * The unknown {@link UserReference}.
     */
    private UserReference userReference;

    /**
     * Creates a new {@link UnknownUserReferenceException}.
     *
     * @param userReference The unknown {@link UserReference}.
     */
    public UnknownUserReferenceException(UserReference userReference)
    {
        super(userReference.getId());
        this.userReference = userReference;
    }

    /**
     * Returns the unknown {@link UserReference}.
     *
     * @return The unknown {@link UserReference}.
     */
    public UserReference getUserReference()
    {
        return this.userReference;
    }
}



