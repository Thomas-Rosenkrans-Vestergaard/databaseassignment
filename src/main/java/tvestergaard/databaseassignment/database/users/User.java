package tvestergaard.databaseassignment.database.users;

public class User extends UserReference
{

    /**
     * The username of the {@link User}.
     */
    private String username;

    /**
     * The password of the {@link User}.
     */
    private String password;

    /**
     * Whether or not the {@link User} is an administrator.
     */
    private boolean admin;

    /**
     * Creates a new {@link User}.
     *
     * @param id       The id of the {@link User}.
     * @param username The username of the {@link User}.
     * @param password The password of the {@link User}.
     * @param admin    Whether or not the {@link User} is an administrator.
     */
    public User(int id, String username, String password, boolean admin)
    {
        super(id);
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    /**
     * Returns the {@link User}s username.
     *
     * @return The {@link User}s username.
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Returns the {@link User}s password.
     *
     * @return The {@link User}s password.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Return whether or not the {@link User} is an administrator.
     *
     * @return Whether or not the {@link User} is an administrator.
     */
    public boolean isAdmin()
    {
        return this.admin;
    }

    /**
     * Sets the username of the {@link User}.
     *
     * @param username The username to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Sets the password of the {@link User}
     *
     * @param password The password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Sets {@link User} administrator status.
     *
     * @param admin The administrator status to set.
     */
    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (admin != user.admin) return false;
        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode()
    {
        int result = getId();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (admin ? 1 : 0);
        return result;
    }
}
