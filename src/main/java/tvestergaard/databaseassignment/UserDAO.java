package tvestergaard.databaseassignment;

import java.util.List;

public interface UserDAO
{

	/**
	 * Returns a complete list of the users in the provided {@link javax.sql.DataSource}.
	 *
	 * @return The complete list of the users in the provided {@link javax.sql.DataSource}.
	 */
	List<User> getUsers();

	/**
	 * Returns the user with the provided id in the {@link javax.sql.DataSource}.
	 *
	 * @param id The id of the user to retrieve.
	 *
	 * @return The user with the provided id in the {@link javax.sql.DataSource}. Returns <code>null</code> in case
	 * no user with the provided constrain exists.
	 */
	User getUser(int id);

	/**
	 * Returns the user with the provided username in the {@link javax.sql.DataSource}.
	 *
	 * @param username The username of the user to retrieve.
	 *
	 * @return The user with the provided username in the {@link javax.sql.DataSource}. Returns <code>null</code> in
	 * case no user with the provided constrain exists.
	 */
	User getUser(String username);
}
