package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.DAO;
import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

public interface TeamDAO extends DAO
{

    /**
     * Returns a complete list of the teams in the provided {@link TeamDAO}.
     *
     * @return The complete list of the teams in the provided {@link TeamDAO}.
     */
    List<Team> getTeams();

    /**
     * Returns the team with the provided id in the {@link TeamDAO}.{.
     *
     * @param id The id of the team to retrieve.
     * @return The team with the provided id in the {@link TeamDAO}. Returns <code>null</code> in case
     * no team with the provided constrain exists.
     * @throws UnknownTeamIdException When a team with the provided id doesn't exist.
     */
    Team getTeam(int id) throws UnknownTeamIdException;

    /**
     * Returns the team with the provided teamName in the {@link TeamDAO}.
     *
     * @param teamName The teamName of the team to retrieve.
     * @return The team with the provided teamName in the {@link TeamDAO}. Returns <code>null</code> in
     * case no team with the provided constrain exists.
     * @throws UnknownTeamNameException When the provided teamName doesn't exist.
     */
    Team getTeam(String teamName) throws UnknownTeamNameException;

    /**
     * Returns a list of the {@link User}s in the {@link Team} with the provided identifier.
     *
     * @param id The id of the team from which to return the members.
     * @return The list of the {@link User}s in the {@link Team} with the provided identifier.
     * @throws UnknownTeamIdException When the team with the provided id doesn't exist.
     */
    List<User> getTeamMembers(int id) throws UnknownTeamIdException;

    /**
     * Returns a list of the {@link User}s in the {@link Team} with the teamName.
     *
     * @param teamName The teamName of the team from which to return the members.
     * @return The list of the {@link User}s in the {@link Team} with the teamName teamName.
     * @throws UnknownTeamNameException When the team with the teamName teamName doesn't exist.
     */
    List<User> getTeamMembers(String teamName) throws UnknownTeamNameException;

    /**
     * Inserts the provided {@link Team} into the {@link TeamDAO}. Relations to members of the {@link Team}
     * are also created. Notice that members of the {@link Team} are not created, only their relation to the {@link Team}.
     *
     * @param teamBuilder The {@link TeamBuilder} to model the {@link Team} the insert into the {@link TeamDAO}.
     * @return The newly created {@link Team} record.
     * @throws TeamNameTakenException When the {@link TeamBuilder} name is already taken.
     */
    Team insertTeam(TeamBuilder teamBuilder) throws TeamNameTakenException;

    /**
     * Updates the provided {@link Team} in the {@link TeamDAO}.
     *
     * @param team The {@link Team} to update in the {@link TeamDAO}..
     * @throws UnknownTeamException   When the {@link Team} to update can't be found in the {@link DAO}.
     * @throws TeamNameTakenException When the name to update the {@link Team} to is already taken.
     */
    void updateTeam(Team team) throws UnknownTeamException, TeamNameTakenException;

    /**
     * Deletes the provided {@link Team} from the {@link TeamDAO}.
     *
     * @param team The {@link TeamReference} to delete from the {@link TeamDAO}.
     * @throws UnknownTeamReferenceException When the provided {@link TeamReference} doesn't exist in the {@link TeamDAO}.
     */
    void deleteTeam(TeamReference team) throws UnknownTeamReferenceException;
}
