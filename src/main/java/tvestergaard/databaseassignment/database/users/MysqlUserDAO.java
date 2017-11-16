package tvestergaard.databaseassignment.database.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.databaseassignment.database.AbstractMysqlDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link UserDAO} interface. Users are retrieved from a provided {@link MysqlDataSource}.
 */
public class MysqlUserDAO extends AbstractMysqlDAO implements UserDAO
{

	private static final String ID_COLUMN       = "user_id";
	private static final String USERNAME_COLUMN = "username";
	private static final String PASSWORD_COLUMN = "password";
	private static final String ADMIN_COLUMN    = "admin";

	/**
	 * Creates a new {@link MysqlUserDAO}.
	 *
	 * @param dataSource The {@link MysqlDataSource} from where to retrieve teams.
	 */
	public MysqlUserDAO(MysqlDataSource dataSource)
	{
		super(dataSource);
	}

	/**
	 * Returns a complete list of the users in the provided {@link DataSource}.
	 *
	 * @return The complete list of the users in the provided {@link DataSource}.
	 */
	@Override public List<User> getUsers()
	{
		try {
			Connection        connection     = newConnection();
			PreparedStatement teamsStatement = connection.prepareStatement("SELECT * FROM users");
			ResultSet         teams          = teamsStatement.executeQuery();
			ArrayList<User>   result         = new ArrayList<>();

			if (teams.next())
				result.add(new User(teams.getInt(ID_COLUMN), teams.getString(USERNAME_COLUMN), teams.getString
						(PASSWORD_COLUMN), teams.getBoolean(ADMIN_COLUMN)));

			return result;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the user with the provided id in the {@link DataSource}.
	 *
	 * @param id The id of the user to retrieve.
	 * @return The user with the provided id in the {@link DataSource}. Returns <code>null</code> in case
	 * no user with the provided constrain exists.
	 * @throws UnknownUserIdException When a user with the provided id
	 *                                doesn't exist.
	 */
	@Override public User getUser(int id) throws UnknownUserIdException
	{
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			statement.setInt(1, id);
			ResultSet teams = statement.executeQuery();
			if (!teams.first())
				throw new UnknownUserIdException(id);

			return new User(teams.getInt(ID_COLUMN), teams.getString(USERNAME_COLUMN), teams.getString
					(PASSWORD_COLUMN), teams.getBoolean(ADMIN_COLUMN));
		} catch (UnknownUserIdException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the user with the provided username in the {@link DataSource}.
	 *
	 * @param username The username of the user to retrieve.
	 * @return The user with the provided username in the {@link DataSource}. Returns <code>null</code> in
	 * case no user with the provided constrain exists.
	 * @throws UnknownUsernameException When a user with the provided
	 *                                  username doesn't exist.
	 */
	@Override public User getUser(String username) throws UnknownUsernameException
	{
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			statement.setString(1, username);
			ResultSet teams = statement.executeQuery();
			if (!teams.first())
				throw new UnknownUsernameException(username);
			return new User(teams.getInt(ID_COLUMN), teams.getString(USERNAME_COLUMN), teams.getString
					(PASSWORD_COLUMN), teams.getBoolean(ADMIN_COLUMN));
		} catch (UnknownUsernameException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
