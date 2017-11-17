package tvestergaard.databaseassignment.database.users;

public class UserBuilder
{
    /**
     * The name of the {@link User} to build.
     */
    private String username;

    /**
     * The password of the {@link User} to build.
     */
    private String password;

    /**
     * The administrative status of the {@link User} to build.
     */
    private boolean admin;

    /**
     * Returns the name of the {@link User} to build.
     *
     * @return The name of the {@link User} to build.
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Sets the username of the {@link User} to build.
     *
     * @param username The username to set.
     * @return this
     */
    public UserBuilder setUsername(String username)
    {
        this.username = username;

        return this;
    }

    /**
     * Returns the password of the {@link User} to build.
     *
     * @return The password of the {@link User} to build.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Sets the password of the {@link User} to build.
     *
     * @param password The password to set.
     * @return this
     */
    public UserBuilder setPassword(String password)
    {
        this.password = password;

        return this;
    }

    /**
     * Returns the administrative status of the {@link User} to build.
     *
     * @return The administrative status of the {@link User} to build.
     */
    public boolean isAdmin()
    {
        return this.admin;
    }

    /**
     * Sets the administrative status of the {@link User} to build.
     *
     * @param admin The administrative status to set.
     * @return this
     */
    public UserBuilder setAdmin(boolean admin)
    {
        this.admin = admin;

        return this;
    }
}
