package tvestergaard.databaseassignment.database.teams;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.databaseassignment.database.AbstractMysqlDAO;
import tvestergaard.databaseassignment.database.OpenDAOException;
import tvestergaard.databaseassignment.database.users.User;
import tvestergaard.databaseassignment.database.users.UserReference;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlTeamDAO extends AbstractMysqlDAO implements TeamDAO
{

    private static final String TEAM_ID_COLUMN = "team_id";
    private static final String TEAM_NAME_COLUMN = "team_name";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String ADMIN_COLUMN = "admin";
    private static final String TEAM_MEMBERS_TEAM_COLUMN = "team_id";
    private static final String TEAM_MEMBERS_USER_COLUMN = "user_id";

    /**
     * Creates a new {@link MysqlTeamDAO}.
     *
     * @param dataSource The {@link MysqlDataSource} used to retrieve data.
     * @throws OpenDAOException When a {@link Connection} couldn't be created using the provided {@link MysqlDataSource}.
     */
    public MysqlTeamDAO(MysqlDataSource dataSource) throws OpenDAOException
    {
        super(dataSource);
    }

    /**
     * Returns a complete list of the teams in the provided {@link DataSource}.
     *
     * @return The complete list of the teams in the provided {@link DataSource}.
     */
    @Override
    public List<Team> getTeams()
    {
        String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
                ".team_id = team_members.team_id LEFT JOIN users ON users.user_id = team_members.user_id ORDER BY teams.team_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet teams = statement.executeQuery();
            ArrayList<Team> result = new ArrayList<>();

            int currentTeamId = -1;
            Team currentTeam = null;

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

            teams.close();
            statement.close();

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
    @Override
    public Team getTeam(int id) throws UnknownTeamIdException
    {
        String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
                ".team_id = team_members.team_id LEFT JOIN users ON " +
                "users.user_id = team_members.user_id WHERE teams" +
                ".team_id = ? ORDER BY teams.team_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet teams = statement.executeQuery();
            if (!teams.first())
                throw new UnknownTeamIdException(id);

            Team team = new Team(teams.getInt(TEAM_ID_COLUMN), teams.getString(TEAM_NAME_COLUMN));
            int userId = teams.getInt(USER_ID_COLUMN);
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

            teams.close();
            statement.close();

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
    @Override
    public Team getTeam(String teamName) throws UnknownTeamNameException
    {

        String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
                ".team_id = team_members.team_id LEFT JOIN users ON " +
                "users.user_id = team_members.user_id WHERE " +
                "teams.team_name = ? ORDER BY teams.team_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teamName);
            ResultSet teams = statement.executeQuery();

            if (!teams.first())
                throw new UnknownTeamNameException(teamName);

            Team team = new Team(teams.getInt(TEAM_ID_COLUMN), teams.getString(TEAM_NAME_COLUMN));
            int userId = teams.getInt(USER_ID_COLUMN);
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

            teams.close();
            statement.close();

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
    @Override
    public List<User> getTeamMembers(int id) throws UnknownTeamIdException
    {
        String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
                ".team_id = team_members.team_id LEFT JOIN users ON " +
                "users.user_id = team_members.user_id WHERE teams" +
                ".team_id = ? ORDER BY teams.team_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet teams = statement.executeQuery();
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

            teams.close();
            statement.close();

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
    @Override
    public List<User> getTeamMembers(String teamName) throws UnknownTeamNameException
    {
        String sql = "SELECT * FROM teams LEFT JOIN team_members ON teams" +
                ".team_id = team_members.team_id LEFT JOIN users ON " +
                "users.user_id = team_members.user_id WHERE " +
                "teams.team_name = ? ORDER BY teams.team_id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teamName);
            ResultSet teams = statement.executeQuery();
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

            teams.close();
            statement.close();

            return members;

        } catch (UnknownTeamNameException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Inserts the provided {@link Team} into the {@link DataSource}. Relations to members of the {@link Team}
     * are also created. Notice that members of the {@link Team} are not created, only their relation to the {@link Team}.
     *
     * @param teamBuilder The {@link TeamBuilder} to model the {@link Team} the insert into the {@link DataSource}.
     * @return The newly created {@link Team} record.
     * @throws TeamNameTakenException When the {@link TeamBuilder} name is already taken.
     */
    @Override public Team insertTeam(TeamBuilder teamBuilder) throws TeamNameTakenException
    {
        String teamCheckSQL = String.format("SELECT count(*) FROM teams WHERE team_name = ?;");
        String teamSQL = String.format("INSERT INTO teams (%s) VALUES (?);", TEAM_NAME_COLUMN);
        String memberSQL = String.format("INSERT INTO team_members (%s, %s) VALUES (?, ?);", TEAM_MEMBERS_TEAM_COLUMN, TEAM_MEMBERS_USER_COLUMN);

        PreparedStatement teamCheckStatement = null;
        PreparedStatement teamStatement = null;
        PreparedStatement memberStatement = null;

        try {

            try {

                teamCheckStatement = connection.prepareStatement(teamCheckSQL);
                teamCheckStatement.setString(1, teamBuilder.getName());
                ResultSet teamCheckResult = teamCheckStatement.executeQuery();
                teamCheckResult.next();

                if (teamCheckResult.getInt(1) > 0)
                    throw new TeamNameTakenException(teamBuilder.getName());

                teamStatement = connection.prepareStatement(teamSQL, Statement.RETURN_GENERATED_KEYS);
                memberStatement = connection.prepareStatement(memberSQL);
                teamStatement.setString(1, teamBuilder.getName());
                teamStatement.executeUpdate();
                ResultSet insertedIndex = teamStatement.getGeneratedKeys();
                insertedIndex.next();
                int teamId = insertedIndex.getInt(1);

                for (UserReference member : teamBuilder.getMembers()) {
                    memberStatement.setInt(1, teamId);
                    memberStatement.setInt(2, member.getId());
                    memberStatement.executeUpdate();
                }

                connection.commit();

                return getTeam(teamId);

            } catch (TeamNameTakenException e) {
                throw e;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                if (teamCheckStatement != null)
                    teamCheckStatement.close();
                if (teamStatement != null)
                    teamStatement.close();
                if (memberStatement != null)
                    memberStatement.close();
            }

        } catch (TeamNameTakenException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Updates the provided {@link Team} in the {@link DataSource}.
     *
     * @param team The {@link Team} to update in the {@link DataSource}.
     * @throws UnknownTeamException   When the {@link Team} to update can't be found in the {@link tvestergaard.databaseassignment.database.DAO}.
     * @throws TeamNameTakenException When the name to update the {@link Team} to is already taken.
     */
    @Override public void updateTeam(Team team) throws UnknownTeamException, TeamNameTakenException
    {
        String teamCheckIDSQL = String.format("SELECT count(*) FROM teams WHERE team_id = ?;");
        String teamCheckNameSQL = String.format("SELECT count(*) FROM teams WHERE team_name = ?;");
        String teamSQL = String.format("UPDATE teams SET `%s` = ? WHERE team_id = ?;", TEAM_NAME_COLUMN);

        PreparedStatement teamCheckIDStatement = null;
        PreparedStatement teamCheckNameStatement = null;
        PreparedStatement teamStatement = null;

        try {

            try {

                teamCheckIDStatement = connection.prepareStatement(teamCheckIDSQL);
                teamCheckIDStatement.setInt(1, team.getId());
                ResultSet teamCheckIDResult = teamCheckIDStatement.executeQuery();
                teamCheckIDResult.next();

                if (teamCheckIDResult.getInt(1) < 1)
                    throw new UnknownTeamException(team);

                teamCheckNameStatement = connection.prepareStatement(teamCheckNameSQL);
                teamCheckNameStatement.setString(1, team.getName());
                ResultSet teamCheckNameResult = teamCheckNameStatement.executeQuery();
                teamCheckNameResult.next();

                if (teamCheckNameResult.getInt(1) > 0)
                    throw new TeamNameTakenException(team.getName());

                teamStatement = connection.prepareStatement(teamSQL);
                teamStatement.setString(1, team.getName());
                teamStatement.setInt(2, team.getId());
                teamStatement.executeUpdate();

                connection.commit();

            } catch (TeamNameTakenException | UnknownTeamException e) {
                throw e;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                if (teamCheckIDStatement != null)
                    teamCheckIDStatement.close();
                if (teamCheckNameStatement != null)
                    teamCheckNameStatement.close();
                if (teamStatement != null)
                    teamStatement.close();
            }

        } catch (TeamNameTakenException | UnknownTeamException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Deletes the provided {@link Team} from the provided {@link DataSource}.
     *
     * @param team The {@link TeamReference} to delete from the {@link DataSource}.
     * @throws UnknownTeamReferenceException When the provided {@link TeamReference} doesn't exist in the
     *                                       {@link DataSource}.
     */
    @Override public void deleteTeam(TeamReference team) throws UnknownTeamReferenceException
    {
        String selectMembersSQL = String.format("SELECT * FROM team_members WHERE `%s` = ?;", TEAM_ID_COLUMN);
        String deleteMembersSQL = String.format("DELETE FROM team_members WHERE `%s` = ? && `%s` = ?;", TEAM_MEMBERS_TEAM_COLUMN, TEAM_MEMBERS_USER_COLUMN);
        String deleteTeamSQL = String.format("DELETE FROM teams WHERE `%s` = ?;", TEAM_ID_COLUMN);

        PreparedStatement selectMembersStatement = null;
        PreparedStatement deleteMembersStatement = null;
        PreparedStatement deleteTeamStatement = null;

        try {

            try {
                selectMembersStatement = connection.prepareStatement(selectMembersSQL);
                selectMembersStatement.setInt(1, team.getId());
                ResultSet members = selectMembersStatement.executeQuery();

                deleteMembersStatement = connection.prepareStatement(deleteMembersSQL);
                while (members.next()) {
                    deleteMembersStatement.setInt(1, team.getId());
                    deleteMembersStatement.setInt(2, members.getInt(USER_ID_COLUMN));
                }

                deleteTeamStatement = connection.prepareStatement(deleteTeamSQL);
                deleteTeamStatement.setInt(1, team.getId());
                int updated = deleteTeamStatement.executeUpdate();

                connection.commit();

                if (updated == 0)
                    throw new UnknownTeamReferenceException(team);

            } catch (UnknownTeamReferenceException e) {
                throw e;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                if (selectMembersStatement != null)
                    selectMembersStatement.close();
                if (deleteMembersStatement != null)
                    deleteMembersStatement.close();
                if (deleteTeamStatement != null)
                    deleteTeamStatement.close();
            }

        } catch (UnknownTeamReferenceException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
