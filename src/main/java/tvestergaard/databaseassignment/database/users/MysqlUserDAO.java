package tvestergaard.databaseassignment.database.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.omg.CORBA.UnknownUserException;
import tvestergaard.databaseassignment.database.AbstractMysqlDAO;
import tvestergaard.databaseassignment.database.OpenDAOException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link UserDAO} interface. Users are retrieved from a provided {@link MysqlDataSource}.
 */
public class MysqlUserDAO extends AbstractMysqlDAO implements UserDAO
{

    private static final String ID_COLUMN = "user_id";
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ADMIN_COLUMN = "admin";

    /**
     * Creates a new {@link MysqlUserDAO}.
     *
     * @param dataSource The {@link MysqlDataSource} used to retrieve data.
     * @throws OpenDAOException When a {@link Connection} couldn't be created using the provided {@link MysqlDataSource}.
     */
    public MysqlUserDAO(MysqlDataSource dataSource) throws OpenDAOException
    {
        super(dataSource);
    }

    /**
     * Returns a complete list of the users in the provided {@link DataSource}.
     *
     * @return The complete list of the users in the provided {@link DataSource}.
     */
    @Override
    public List<User> getUsers()
    {
        try {
            PreparedStatement teamsStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet teams = teamsStatement.executeQuery();
            ArrayList<User> result = new ArrayList<>();

            while (teams.next())
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
     * @throws UnknownUserIdException When a user with the provided id doesn't exist.
     */
    @Override
    public User getUser(int id) throws UnknownUserIdException
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
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
     * @throws UnknownUsernameException When a user with the provided username doesn't exist.
     */
    @Override
    public User getUser(String username) throws UnknownUsernameException
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
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

    /**
     * Inserts the provided {@link User} into the {@link UserDAO}. Relations to members of the {@link User}
     * are also created.
     *
     * @param userBuilder The {@link UserBuilder} to model the {@link User} to insert into the {@link UserDAO}.
     * @return The newly created {@link User} record.
     */
    @Override
    public User insertUser(UserBuilder userBuilder)
    {
        String userSQL = String.format("INSERT INTO users (`%s`, `%s`, `%s`) VALUES (?, ?, ?);",
                USERNAME_COLUMN,
                PASSWORD_COLUMN,
                ADMIN_COLUMN);

        PreparedStatement userStatement = null;

        try {

            try {

                userStatement = connection.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
                userStatement.setString(1, userBuilder.getUsername());
                userStatement.setString(2, userBuilder.getPassword());
                userStatement.setBoolean(3, userBuilder.isAdmin());
                userStatement.executeUpdate();
                ResultSet insertedIndex = userStatement.getGeneratedKeys();
                insertedIndex.next();
                int userId = insertedIndex.getInt(1);

                connection.commit();

                return getUser(userId);

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                if (userStatement != null)
                    userStatement.close();
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Updates the provided {@link User} in the {@link UserDAO}.
     *
     * @param user The {@link User} to update in the {@link UserDAO}.
     * @throws UnknownUserException When the {@link User} to update can't be found in the {@link UserDAO}.
     */
    @Override
    public void updateUser(User user) throws UnknownUserException
    {

    }

    /**
     * Deletes the provided {@link User} from the provided {@link UserDAO}.
     *
     * @param user The {@link UserReference} to delete from the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the provided {@link UserReference} doesn't exist in the
     *                                       {@link UserDAO}.
     */
    @Override
    public void deleteUser(UserReference user) throws UnknownUserReferenceException
    {

    }
}
