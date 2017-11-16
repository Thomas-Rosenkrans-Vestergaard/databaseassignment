package tvestergaard.databaseassignment.database.teams;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.databaseassignment.AbstractMysqlDAO;
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

	private static final String ID_COLUMN   = "team_id";
	private static final String NAME_COLUMN = "team_name";

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
		try {
			Connection        connection     = newConnection();
			PreparedStatement teamsStatement = connection.prepareStatement("SELECT * FROM teams");
			ResultSet         teams          = teamsStatement.executeQuery();
			ArrayList<Team>   result         = new ArrayList<>();

			if (teams.next()) {
				Team team = new Team(teams.getInt(ID_COLUMN), teams.getString(NAME_COLUMN));
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
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM teams WHERE team_id = ?");
			statement.setInt(1, id);
			ResultSet teams = statement.executeQuery();
			if (!teams.first())
				throw new UnknownTeamIdException();
			return new Team(teams.getInt(ID_COLUMN), teams.getString(NAME_COLUMN));
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
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM teams WHERE team_name = ?");
			statement.setString(1, teamName);
			ResultSet teams = statement.executeQuery();
			if (!teams.first())
				throw new UnknownTeamNameException();

			return new Team(teams.getInt(ID_COLUMN), teams.getString(NAME_COLUMN));
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
		return null;
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
		return null;
	}
}
