package tvestergaard.databaseassignment;

import java.sql.SQLException;
import java.util.List;

public interface TeamDAO
{

	/**
	 * Returns a complete list of the teams in the provided {@link javax.sql.DataSource}.
	 *
	 * @return The complete list of the teams in the provided {@link javax.sql.DataSource}.
	 */
	List<Team> getTeams() throws SQLException;

	/**
	 * Returns the team with the provided id in the {@link javax.sql.DataSource}.
	 *
	 * @param id The id of the team to retrieve.
	 *
	 * @return The team with the provided id in the {@link javax.sql.DataSource}. Returns <code>null</code> in case
	 * no team with the provided constrain exists.
	 */
	Team getTeam(int id);

	/**
	 * Returns the team with the provided teamName in the {@link javax.sql.DataSource}.
	 *
	 * @param teamName The teamName of the team to retrieve.
	 *
	 * @return The team with the provided teamName in the {@link javax.sql.DataSource}. Returns <code>null</code> in
	 * case no team with the provided constrain exists.
	 */
	Team getTeam(String teamName);
}
