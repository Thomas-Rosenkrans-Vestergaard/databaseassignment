package tvestergaard.databaseassignment.database.users;

import java.util.Objects;

public class UserReference
{

    /**
     * The user id referenced by the {@link UserReference}.
     */
    private int userId;

    /**
     * Creates a new {@link UserReference}.
     *
     * @param userId The user id referenced by the {@link UserReference}.
     */
    public UserReference(int userId)
    {
        this.userId = userId;
    }

    /**
     * Creates a new {@link UserReference}.
     *
     * @param userId The user id referenced by the {@link UserReference}.
     */
    public static UserReference of(int userId)
    {
        return new UserReference(userId);
    }

    /**
     * Returns the user id referenced by the {@link UserReference}.
     *
     * @return The user id referenced by the {@link UserReference}.
     */
    public int getId()
    {
        return this.userId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserReference)) return false;
        UserReference that = (UserReference) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId);
    }
}
