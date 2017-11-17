package tvestergaard.databaseassignment.database.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.databaseassignment.database.AbstractMysqlDAO;
import tvestergaard.databaseassignment.database.OpenDAOException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO extends AbstractMysqlDAO implements UserDAO
{

    private static final String ID_COLUMN = "user_id";
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ADMIN_COLUMN = "admin";
    private static final String TEAM_MEMBER_ID_COLUMN = "user_id";

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
     * Returns the {@link User} with the provided id in the {@link UserDAO}.
     *
     * @param id The id of the {@link User} to retrieve.
     * @return The {@link User} referenced by the provided id in the {@link UserDAO}.
     * @throws UnknownUserIdException When the {@link User} referenced by the provided id doesn't exist.
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

            return new User(
                    teams.getInt(ID_COLUMN),
                    teams.getString(USERNAME_COLUMN),
                    teams.getString(PASSWORD_COLUMN),
                    teams.getBoolean(ADMIN_COLUMN));

        } catch (UnknownUserIdException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns the {@link User} referenced by the provided {@link UserReference} in the {@link UserDAO}.
     *
     * @param userReference The {@link UserReference} referencing the {@link User} to retrieve.
     * @return The {@link User} referenced by the provided {@link UserReference} in the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the {@link User} referenced by the provided {@link UserReference}
     *                                       doesn't exist.
     */
    @Override
    public User getUser(UserReference userReference) throws UnknownUserReferenceException
    {
        try {
            return getUser(userReference.getId());
        } catch (UnknownUserIdException e) {
            throw new UnknownUserReferenceException(userReference);
        }
    }

    /**
     * Returns the {@link User} with the provided username in the {@link UserDAO}.
     *
     * @param username The username of the {@link User} to retrieve.
     * @return The {@link User} with the provided username in the {@link UserDAO}.
     * @throws UnknownUsernameException When a {@link User} with the provided username doesn't exist.
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

            return new User(
                    teams.getInt(ID_COLUMN),
                    teams.getString(USERNAME_COLUMN),
                    teams.getString(PASSWORD_COLUMN),
                    teams.getBoolean(ADMIN_COLUMN));

        } catch (UnknownUsernameException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Inserts the provided {@link User} into the {@link UserDAO}.
     *
     * @param userBuilder The {@link UserBuilder} used to model the {@link User} to insert into the {@link UserDAO}.
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

            } catch (Exception e) {
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
        String userSQL = String.format("UPDATE users SET `%s` = ? WHERE user_id = ?;", USERNAME_COLUMN);

        PreparedStatement teamCheckNameStatement = null;
        PreparedStatement teamStatement = null;

        try {

            try {

                teamStatement = connection.prepareStatement(userSQL);
                teamStatement.setString(1, user.getUsername());
                teamStatement.setInt(2, user.getId());
                int updated = teamStatement.executeUpdate();

                if (updated < 1)
                    throw new UnknownUserException(user);

                connection.commit();

            } catch (Exception e) {
                connection.rollback();
                throw e;
            } finally {
                if (teamCheckNameStatement != null)
                    teamCheckNameStatement.close();
                if (teamStatement != null)
                    teamStatement.close();
            }

        } catch (UnknownUserException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Deletes the provided {@link UserReference} from the {@link UserDAO}.
     *
     * @param user The {@link UserReference} to delete from the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the provided {@link UserReference} doesn't exist in the
     *                                       {@link UserDAO}.
     */
    @Override
    public void deleteUser(UserReference user) throws UnknownUserReferenceException
    {
        String deleteMembersSQL = String.format("DELETE FROM team_members WHERE `%s` = ?;", TEAM_MEMBER_ID_COLUMN);
        String deleteUserSQL = String.format("DELETE FROM users WHERE `%s` = ?;", ID_COLUMN);

        PreparedStatement deleteMembersStatement = null;
        PreparedStatement deleteUserStatement = null;

        try {

            try {
                deleteMembersStatement = connection.prepareStatement(deleteMembersSQL);
                deleteMembersStatement.setInt(1, user.getId());
                deleteMembersStatement.executeUpdate();

                deleteUserStatement = connection.prepareStatement(deleteUserSQL);
                deleteUserStatement.setInt(1, user.getId());
                int updated = deleteUserStatement.executeUpdate();

                if (updated == 0)
                    throw new UnknownUserReferenceException(user);

                connection.commit();

            } catch (Exception e) {
                connection.rollback();
                throw e;
            } finally {
                if (deleteMembersStatement != null)
                    deleteMembersStatement.close();
                if (deleteUserStatement != null)
                    deleteUserStatement.close();
            }

        } catch (UnknownUserReferenceException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
