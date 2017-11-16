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

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isAdmin()
    {
        return this.admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }
}
