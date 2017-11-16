package tvestergaard.databaseassignment;

import com.mysql.cj.jdbc.MysqlDataSource;

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
	 *
	 * @return The team with the provided id in the {@link DataSource}. Returns <code>null</code> in case
	 * no team with the provided constrain exists.
	 */
	@Override public Team getTeam(int id)
	{
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM teams WHERE team_id = ?");
			statement.setInt(1, id);
			ResultSet teams = statement.executeQuery();
			if (teams.first())
				return new Team(teams.getInt(ID_COLUMN), teams.getString(NAME_COLUMN));

			return null;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the team with the provided teamName in the {@link DataSource}.
	 *
	 * @param teamName The teamName of the team to retrieve.
	 *
	 * @return The team with the provided teamName in the {@link DataSource}. Returns <code>null</code> in
	 * case no team with the provided constrain exists.
	 */
	@Override public Team getTeam(String teamName)
	{
		try {
			Connection        connection = newConnection();
			PreparedStatement statement  = connection.prepareStatement("SELECT * FROM teams WHERE team_name = ?");
			statement.setString(1, teamName);
			ResultSet teams = statement.executeQuery();
			if (teams.first())
				return new Team(teams.getInt(ID_COLUMN), teams.getString(NAME_COLUMN));

			return null;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
