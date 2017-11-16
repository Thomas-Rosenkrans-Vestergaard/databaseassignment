package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

public interface TeamDAO
{

	/**
	 * Returns a complete list of the teams in the provided {@link javax.sql.DataSource}.
	 *
	 * @return The complete list of the teams in the provided {@link javax.sql.DataSource}.
	 */
	List<Team> getTeams();

	/**
	 * Returns the team with the provided id in the {@link javax.sql.DataSource}.
	 *
	 * @param id The id of the team to retrieve.
	 * @return The team with the provided id in the {@link javax.sql.DataSource}. Returns <code>null</code> in case
	 * no team with the provided constrain exists.
	 * @throws UnknownTeamIdException When a team with the provided id
	 *                                doesn't exist.
	 */
	Team getTeam(int id) throws UnknownTeamIdException;

	/**
	 * Returns the team with the provided teamName in the {@link javax.sql.DataSource}.
	 *
	 * @param teamName The teamName of the team to retrieve.
	 * @return The team with the provided teamName in the {@link javax.sql.DataSource}. Returns <code>null</code> in
	 * case no team with the provided constrain exists.
	 * @throws UnknownTeamNameException When the provided teamName doesn't
	 *                                  exist.
	 */
	Team getTeam(String teamName) throws UnknownTeamNameException;

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
	List<User> getTeamMembers(int id) throws UnknownTeamIdException;

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
	List<User> getTeamMembers(String teamName) throws UnknownTeamNameException;
}
