package tvestergaard.databaseassignment.database.teams;

import tvestergaard.databaseassignment.database.DAO;
import tvestergaard.databaseassignment.database.users.User;

import java.util.List;

public interface TeamDAO extends DAO
{

    /**
     * Returns a complete list of the teams in the {@link TeamDAO}.
     *
     * @return The complete list of the teams in the {@link TeamDAO}.
     */
    List<Team> getTeams();

    /**
     * Returns the {@link Team} with the provided id in the {@link TeamDAO}.
     *
     * @param id The id of the {@link Team} to retrieve.
     * @return The {@link Team} with the provided id in the {@link TeamDAO}.
     * @throws UnknownTeamIdException When the {@link Team} with the provided id doesn't exist.
     */
    Team getTeam(int id) throws UnknownTeamIdException;

    /**
     * Returns the {@link Team} referenced by the provided {@link TeamReference} in the {@link TeamDAO}.
     *
     * @param teamReference The {@link TeamReference} pointing to the {@link Team} to retrieve.
     * @return The {@link Team} referenced by the provided {@link TeamReference} in the {@link TeamDAO}.
     * @throws UnknownTeamReferenceException When the {@link Team} referenced by the provided {@link TeamReference} doesn't
     *                                       exist.
     */
    Team getTeam(TeamReference teamReference) throws UnknownTeamReferenceException;

    /**
     * Returns the team with the provided teamName in the {@link TeamDAO}.
     *
     * @param teamName The teamName of the team to retrieve.
     * @return The team with the provided teamName in the {@link TeamDAO}.
     * @throws UnknownTeamNameException When the {@link Team} with the provided name doesn't exist.
     */
    Team getTeam(String teamName) throws UnknownTeamNameException;

    /**
     * Returns a list of the {@link User}s in the {@link Team} with the provided id.
     *
     * @param id The id of the {@link Team} from which to return the members.
     * @return The list of the {@link User}s in the {@link Team} with the provided id.
     * @throws UnknownTeamIdException When the {@link Team} with the provided id doesn't exist.
     */
    List<User> getTeamMembers(int id) throws UnknownTeamIdException;

    /**
     * Returns a list of the {@link User}s in the {@link Team} referenced by the provided {@link TeamReference}.
     *
     * @param teamReference The {@link TeamReference} of the {@link Team} from which to return the members.
     * @return The list of the {@link User}s in the {@link Team} referenced by the provided {@link TeamReference}.
     * @throws UnknownTeamReferenceException When the {@link Team} referenced by the provided {@link TeamReference}
     *                                       doesn't exist.
     */
    List<User> getTeamMembers(TeamReference teamReference) throws UnknownTeamReferenceException;

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
