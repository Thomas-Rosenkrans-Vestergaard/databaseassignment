package tvestergaard.databaseassignment.database.teams;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.databaseassignment.database.AbstractMysqlDAO;
import tvestergaard.databaseassignment.database.users.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link TeamDAO} interface. Teams are retrieved from a provided {@link MysqlDataSource}.
 */
public class MysqlTeamDAO extends AbstractMysqlDAO implements TeamDAO
{

	private static final String TEAM_ID_COLUMN   = "team_id";
	private static final String TEAM_NAME_COLUMN = "team_name";
	private static final String USER_ID_COLUMN   = "user_id";
	private static final String USERNAME_COLUMN  = "username";
	private static final String PASSWORD_COLUMN  = "password";
	private static final String ADMIN_COLUMN     = "admin";

	/**
	 * Creates a new {@link MysqlTeamDAO}.
	 *
	 * @param dataSource The {@link MysqlDataSource} from where to retrieve teams.
	 */
	public MysqlTeamDAO(MysqlDataSource dataSource)
	{
		super(dataSource);
	}

	/**
	 * Returns a complete list of the teams in the provided {@link DataSource}.
	 *
	 * @return The complete list of the teams in the provided {@link DataSource}.
	 */
	@Override public List<Team> getTeams()
	{
		String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
					 ".team_id = team_members.team_id LEFT JOIN users ON users.user_id = team_members.user_id ORDER BY teams.team_id";

		try {
			Connection        connection     = newConnection();
			PreparedStatement teamsStatement = connection.prepareStatement(sql);
			ResultSet         teams          = teamsStatement.executeQuery();
			ArrayList<Team>   result         = new ArrayList<>();

			int  currentTeamId = -1;
			Team currentTeam   = null;

			while (teams.next()) {
				int teamId = teams.getInt(TEAM_ID_COLUMN);
				if (currentTeamId != teamId) {
					currentTeamId = teamId;
					currentTeam = new Team(teamId, teams.getString(TEAM_NAME_COLUMN));
					result.add(currentTeam);
					int userId = teams.getInt(USER_ID_COLUMN);
					if (!teams.wasNull()) {
						User user = new User(userId, teams.getString
								(USERNAME_COLUMN), teams.getString
								(PASSWORD_COLUMN), teams.getBoolean
								(ADMIN_COLUMN));
						currentTeam.addMember(user);
					}
				} else {
					int userId = teams.getInt(USER_ID_COLUMN);
					if (!teams.wasNull()) {
						User user = new User(userId, teams.getString
								(USERNAME_COLUMN), teams.getString
								(PASSWORD_COLUMN), teams.getBoolean
								(ADMIN_COLUMN));
						currentTeam.addMember(user);
					}
				}
			}

			if (teams.next()) {
				Team team = new Team(teams.getInt(TEAM_ID_COLUMN), teams.getString(TEAM_NAME_COLUMN));
				result.add(team);
			}

			return result;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the team with the provided id in the {@link DataSource}.
	 *
	 * @param id The id of the team to retrieve.
	 * @return The team with the provided id in the {@link DataSource}. Returns <code>null</code> in case
	 * no team with the provided constrain exists.
	 * @throws UnknownTeamIdException When a team with the provided id
	 *                                doesn't exist.
	 */
	@Override public Team getTeam(int id) throws UnknownTeamIdException
	{
		String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
					 ".team_id = team_members.team_id LEFT JOIN users ON " +
					 "users.user_id = team_members.user_id WHERE teams" +
					 ".team_id = ? ORDER BY teams.team_id";

		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet teams = statement.executeQuery();
			if (!teams.first())
				throw new UnknownTeamIdException(id);

			Team team   = new Team(teams.getInt(TEAM_ID_COLUMN), teams.getString(TEAM_NAME_COLUMN));
			int  userId = teams.getInt(USER_ID_COLUMN);
			if (!teams.wasNull()) {
				team.addMember(new User(userId, teams.getString
						(USERNAME_COLUMN), teams.getString
						(PASSWORD_COLUMN), teams.getBoolean
						(ADMIN_COLUMN)));
			}

			while (teams.next()) {
				userId = teams.getInt(USER_ID_COLUMN);
				if (!teams.wasNull()) {
					team.addMember(new User(userId, teams.getString
							(USERNAME_COLUMN), teams.getString
							(PASSWORD_COLUMN), teams.getBoolean
							(ADMIN_COLUMN)));
				}
			}

			return team;

		} catch (UnknownTeamIdException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the team with the provided teamName in the {@link DataSource}.
	 *
	 * @param teamName The teamName of the team to retrieve.
	 * @return The team with the provided teamName in the {@link DataSource}. Returns <code>null</code> in
	 * case no team with the provided constrain exists.
	 * @throws UnknownTeamNameException When the provided teamName doesn't
	 *                                  exist.
	 */
	@Override public Team getTeam(String teamName) throws UnknownTeamNameException
	{

		String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
					 ".team_id = team_members.team_id LEFT JOIN users ON " +
					 "users.user_id = team_members.user_id WHERE " +
					 "teams.team_name = ? ORDER BY teams.team_id";

		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			statement.setString(1, teamName);
			ResultSet teams = statement.executeQuery();

			if (!teams.first())
				throw new UnknownTeamNameException(teamName);

			Team team   = new Team(teams.getInt(TEAM_ID_COLUMN), teams.getString(TEAM_NAME_COLUMN));
			int  userId = teams.getInt(USER_ID_COLUMN);
			if (!teams.wasNull()) {
				team.addMember(new User(userId, teams.getString
						(USERNAME_COLUMN), teams.getString
						(PASSWORD_COLUMN), teams.getBoolean
						(ADMIN_COLUMN)));
			}

			while (teams.next()) {
				userId = teams.getInt(USER_ID_COLUMN);
				if (!teams.wasNull()) {
					team.addMember(new User(userId, teams.getString
							(USERNAME_COLUMN), teams.getString
							(PASSWORD_COLUMN), teams.getBoolean
							(ADMIN_COLUMN)));
				}
			}

			return team;

		} catch (UnknownTeamNameException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns a list of the {@link User}s in the {@link Team} with the
	 * provided identifier.
	 *
	 * @param id The id of the team from which to return the members.
	 * @return The list of the {@link User}s in the {@link Team} with the
	 * provided identifier.
	 * @throws UnknownTeamIdException When the team with the provided id
	 *                                doesn't exist.
	 */
	@Override public List<User> getTeamMembers(int id) throws UnknownTeamIdException
	{
		String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
					 ".team_id = team_members.team_id LEFT JOIN users ON " +
					 "users.user_id = team_members.user_id WHERE teams" +
					 ".team_id = ? ORDER BY teams.team_id";

		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet  teams   = statement.executeQuery();
			List<User> members = new ArrayList<>();
			if (!teams.first())
				throw new UnknownTeamIdException(id);

			do {
				int userId = teams.getInt(USER_ID_COLUMN);
				if (!teams.wasNull()) {
					members.add(new User(userId, teams.getString
							(USERNAME_COLUMN), teams.getString
							(PASSWORD_COLUMN), teams.getBoolean
							(ADMIN_COLUMN)));
				}
			} while (teams.next());

			return members;

		} catch (UnknownTeamIdException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns a list of the {@link User}s in the {@link Team} with the
	 * teamName teamName.
	 *
	 * @param teamName The teamName of the team from which to return the members.
	 * @return The list of the {@link User}s in the {@link Team} with the
	 * teamName teamName.
	 * @throws UnknownTeamNameException When the team with the teamName teamName
	 *                                  doesn't exist.
	 */
	@Override public List<User> getTeamMembers(String teamName) throws UnknownTeamNameException
	{
		String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
					 ".team_id = team_members.team_id LEFT JOIN users ON " +
					 "users.user_id = team_members.user_id WHERE " +
					 "teams.team_name = ? ORDER BY teams.team_id";

		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement(sql);
			statement.setString(1, teamName);
			ResultSet  teams   = statement.executeQuery();
			List<User> members = new ArrayList<>();

			if (!teams.first())
				throw new UnknownTeamNameException(teamName);

			do {
				int userId = teams.getInt(USER_ID_COLUMN);
				if (!teams.wasNull()) {
					members.add(new User(userId, teams.getString
							(USERNAME_COLUMN), teams.getString
							(PASSWORD_COLUMN), teams.getBoolean
							(ADMIN_COLUMN)));
				}
			} while (teams.next());

			return members;

		} catch (UnknownTeamNameException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Inserts the provided {@link Team} into the
	 * {@link DataSource}. Relations to members of the {@link Team}
	 * are also inserted.
	 *
	 * @param team The {@link Team} to insertTeam into the
	 *             {@link DataSource}.
	 * @throws DuplicateTeamException When a team with the provided id
	 *                                already exists in the {@link DataSource}.
	 */
	@Override public void insertTeam(Team team) throws DuplicateTeamException
	{

	}

	/**
	 * Updates the provided {@link Team} in the {@link DataSource}.
	 *
	 * @param team The {@link Team} to update in the
	 *             {@link DataSource}.
	 */
	@Override public void updateTeam(Team team) throws UnknownTeamException
	{

	}

	/**
	 * Deletes the provided {@link Team} from the provided
	 * {@link DataSource}.
	 *
	 * @param team The {@link Team} to delete from the
	 *             {@link DataSource}.
	 * @throws UnknownTeamException When the {@link Team} doesn't exist in
	 *                              the {@link DataSource}.
	 */
	@Override public void deleteTeam(Team team) throws UnknownTeamException
	{

	}

	/**
	 * Deletes the {@link Team} with the provided id from the
	 * {@link DataSource}.
	 *
	 * @param id The id of the {@link Team} to delete.
	 * @throws UnknownTeamIdException When a {@link Team} with the provided
	 *                                id doesn't exist in the {@link DataSource}.
	 */
	@Override public void deleteTeam(int id) throws UnknownTeamIdException
	{

	}

	/**
	 * Deletes the {@link Team} with the provided teamName from the
	 * {@link DataSource}.
	 *
	 * @param teamName The name of the {@link Team} to delete from the
	 *                 {@link DataSource}.
	 * @throws UnknownTeamNameException When a {@link Team} with the provided
	 *                                  name doesn't exist in the {@link DataSource}.
	 */
	@Override public void deleteTeam(String teamName) throws UnknownTeamNameException
	{

	}
}
