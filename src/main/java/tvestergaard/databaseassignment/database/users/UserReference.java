package tvestergaard.databaseassignment.database.users;

public class UserReference
{

    private int userId;

    public UserReference(int userId)
    {
        this.userId = userId;
    }

    public static UserReference of(int id)
    {
        return new UserReference(id);
    }

    public int getId()
    {
        return this.userId;
    }
}
