package tvestergaard.databaseassignment.database.users;

public class UserBuilder
{
    private String username;
    private String password;
    private boolean admin;

    public String getUsername()
    {
        return this.username;
    }

    public UserBuilder setUsername(String username)
    {
        this.username = username;

        return this;
    }

    public String getPassword()
    {
        return this.password;
    }

    public UserBuilder setPassword(String password)
    {
        this.password = password;

        return this;
    }

    public boolean isAdmin()
    {
        return this.admin;
    }

    public UserBuilder setAdmin(boolean admin)
    {
        this.admin = admin;

        return this;
    }
}
