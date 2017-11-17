package tvestergaard.databaseassignment.database.users;

import tvestergaard.databaseassignment.database.DAO;

import java.util.List;

public interface UserDAO extends DAO
{

    /**
     * Returns a complete list of the {@link User}s in the {@link UserDAO}.
     *
     * @return The complete list of the {@link User}s in the {@link UserDAO}.
     */
    List<User> getUsers();

    /**
     * Returns the {@link User} with the provided id in the {@link UserDAO}.
     *
     * @param id The id of the {@link User} to retrieve.
     * @return The {@link User} referenced by the provided id in the {@link UserDAO}.
     * @throws UnknownUserIdException When the {@link User} referenced by the provided id doesn't exist.
     */
    User getUser(int id) throws UnknownUserIdException;

    /**
     * Returns the {@link User} referenced by the provided {@link UserReference} in the {@link UserDAO}.
     *
     * @param userReference The {@link UserReference} referencing the {@link User} to retrieve.
     * @return The {@link User} referenced by the provided {@link UserReference} in the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the {@link User} referenced by the provided {@link UserReference}
     *                                       doesn't exist.
     */
    User getUser(UserReference userReference) throws UnknownUserReferenceException;

    /**
     * Returns the {@link User} with the provided username in the {@link UserDAO}.
     *
     * @param username The username of the {@link User} to retrieve.
     * @return The {@link User} with the provided username in the {@link UserDAO}.
     * @throws UnknownUsernameException When a {@link User} with the provided username doesn't exist.
     */
    User getUser(String username) throws UnknownUsernameException;

    /**
     * Inserts the provided {@link User} into the {@link UserDAO}.
     *
     * @param userBuilder The {@link UserBuilder} used to model the {@link User} to insert into the {@link UserDAO}.
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
     * Deletes the provided {@link User} from the {@link UserDAO}.
     *
     * @param user The {@link UserReference} to delete from the {@link UserDAO}.
     * @throws UnknownUserReferenceException When the provided {@link UserReference} doesn't exist in the
     *                                       {@link UserDAO}.
     */
    void deleteUser(UserReference user) throws UnknownUserReferenceException;
}


