package tvestergaard.databaseassignment.database.users;

import org.omg.CORBA.UnknownUserException;
import tvestergaard.databaseassignment.database.DAO;

import java.util.List;

public interface UserDAO extends DAO
{

    /**
     * Returns a complete list of the users in the provided {@link UserDAO}.
     *
     * @return The complete list of the users in the provided {@link UserDAO}.
     */
    List<User> getUsers();

    /**
     * Returns the user with the provided id in the {@link UserDAO}.
     *
     * @param id The id of the user to retrieve.
     * @return The user with the provided id in the {@link UserDAO}. Returns <code>null</code> in case
     * no user with the provided constrain exists.
     * @throws UnknownUserIdException When a user with the provided id doesn't exist.
     */
    User getUser(int id) throws UnknownUserIdException;

    /**
     * Returns the user with the provided username in the {@link UserDAO}.
     *
     * @param username The username of the user to retrieve.
     * @return The user with the provided username in the {@link UserDAO}. Returns <code>null</code> in
     * case no user with the provided constrain exists.
     * @throws UnknownUsernameException When a user with the provided username doesn't exist.
     */
    User getUser(String username) throws UnknownUsernameException;

    /**
     * Inserts the provided {@link User} into the {@link UserDAO}. Relations to members of the {@link User}
     * are also created.
     *
     * @param userBuilder The {@link UserBuilder} to model the {@link User} to insert into the {@link UserDAO}.
     * @return The newly created {@link User} record.
     */
    User insertUser(UserBuilder userBuilder);

    /**
     * Updates the provided {@link User} in the {@link UserDAO}.
     *
     * @param user The {@link User} to update in the {@link UserDAO}.
     * @throws UnknownUserException When the {@link User} to update can't be found in the {@link UserDAO}.
     */
    void updateUser(User user) throws UnknownUserException;

    /**
     * Deletes the provided {@link User} from the provided {@link UserDAO}.
     *
     * @param user The {@link UserReference} to delete from the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the provided {@link UserReference} doesn't exist in the
     *                                       {@link UserDAO}.
     */
    void deleteUser(UserReference user) throws UnknownUserReferenceException;
}


