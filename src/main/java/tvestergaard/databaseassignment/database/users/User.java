package tvestergaard.databaseassignment.database.users;

public class User
{
	private final int     id;
	private       String  username;
	private       String  password;
	private       boolean admin;

	public User(int id, String username, String password, boolean admin)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}

	public int getId()
	{
		return this.id;
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getPassword()
	{
		return this.password;
	}

	public boolean isAdmin()
	{
		return this.admin;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	@Override public String toString()
	{
		return "User{" +
			   "id=" + id +
			   ", username='" + username + '\'' +
			   ", password='" + password + '\'' +
			   ", admin=" + admin +
			   '}';
	}
}
