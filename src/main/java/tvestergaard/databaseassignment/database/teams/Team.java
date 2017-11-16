package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
import tvestergaard.databaseassignment.database.users.UnknownUserIdException;
import tvestergaard.databaseassignment.database.users.UnknownUsernameException;
import tvestergaard.databaseassignment.database.users.User;

import java.util.ArrayList;
import java.util.List;

public class Team
{

    /**
     * The id of the {@link Team}.
     */
    private final int id;

    /**
     * The name of the {@link Team}.
     */
    private String name;

    /**
     * The member {@link User}s in the {@link Team}.
     */
    private final List<User> members = new ArrayList<>();

    /**
     * Creates a new {@link Team}.
     *
     * @param id   The id of the {@link Team}.
     * @param name The name of the {@link Team}.
     */
    public Team(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the id of the {@link Team}.
     *
     * @return The id of the {@link Team}.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Returns the name of the {@link Team}.
     *
     * @return The name of the {@link Team}.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name of the {@link Team}.
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Adds a new member {@link User} to the {@link Team}.
     *
     * @param member The {@link User} to add as a member.
     */
    public void addMember(User member)
    {
        this.members.add(member);
    }

    /**
     * Returns an {@link ImmutableList} of the members in the {@link Team}.
     *
     * @return The {@link ImmutableList} of the members in the {@link Team}.
     */
    public ImmutableList<User> getMembers()
    {
        return ImmutableList.copyOf(members);
    }

    /**
     * Checks if a {@link User} with the provided id is a members of the {@link Team}.
     *
     * @param id The id of the {@link User} to check for.
     * @return True when a {@link User} with the provided id is a member of the {@link Team}.
     */
    public boolean hasMember(int id)
    {
        for (User member : members)
            if (member.getId() == id)
                return true;

        return false;
    }

    /**
     * Checks if a {@link User} with the provided username is a members of the {@link Team}.
     *
     * @param username The username of the {@link User} to check for.
     * @return True when a {@link User} with the provided username is a member of the {@link Team}.
     */
    public boolean hasMember(String username)
    {
        for (User member : members)
            if (member.getUsername() == username)
                return true;

        return false;
    }

    /**
     * Checks if the provided {@link User} is a member of the {@link Team}. The {@link User} is compared using the
     * {@link User#equals(Object)} method.
     *
     * @param user The {@link User} to check for.
     * @return True when the provided {@link User} matches a member in the {@link Team}.
     */
    public boolean hasMember(User user)
    {
        for (User x : members)
            if (x.equals(user))
                return true;

        return false;
    }

    /**
     * Returns the member with the provided username.
     *
     * @param username The username of the member to return.
     * @return The member with the provided username.
     * @throws UnknownUsernameException When a member with the provided username doesn't exist.
     */
    public User getMember(String username) throws UnknownUsernameException
    {
        for (User user : members)
            if (user.equals(username))
                return user;

        throw new UnknownUsernameException(username);
    }

    /**
     * Returns the member with the provided id.
     *
     * @param id The id of the member to return.
     * @return The member with the provided id.
     * @throws UnknownUsernameException When a member with the provided id doesn't exist.
     */
    public User getMember(int id) throws UnknownUserIdException
    {
        for (User user : members)
            if (user.equals(id))
                return user;

        throw new UnknownUserIdException(id);
    }

    @Override
    public String toString()
    {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
