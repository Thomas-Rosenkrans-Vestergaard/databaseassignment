package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
import tvestergaard.databaseassignment.database.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team extends TeamReference
{

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
        super(id);
        this.name = name;
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
     * Returns the member with the provided username.
     *
     * @param username The username of the member to return.
     * @return The member with the provided username.
     * @throws UnknownUsernameException When a member with the provided username doesn't exist.
     */
    public User getMember(String username) throws UnknownUsernameException
    {
        for (User user : members)
            if (user.getUsername().equals(username))
                return user;

        throw new UnknownUsernameException(username);
    }

    /**
     * Returns the {@link User} member with the provided id.
     *
     * @param id The id of the {@link User} member to return.
     * @return The {@link User} member with the provided id.
     * @throws UnknownUsernameException When a {@link User} member with the provided id doesn't exist.
     */
    public User getMember(int id) throws UnknownUserIdException
    {
        for (User user : members)
            if (user.getId() == id)
                return user;

        throw new UnknownUserIdException(id);
    }

    /**
     * Returns the {@link User} member referenced by the provided {@link UserReference}.
     *
     * @param userReference The {@link UserReference} referencing the {@link User} member to return.
     * @return The {@link User} member referenced by the provided {@link UserReference}.
     * @throws UnknownUsernameException When a {@link User} member referenced by the provided {@link UserReference} doesn't exist.
     */
    public User getMember(UserReference userReference) throws UnknownUserReferenceException
    {
        try {
            return getMember(userReference.getId());
        } catch (UnknownUserIdException e) {
            throw new UnknownUserReferenceException(userReference);
        }
    }

    /**
     * Removes the member with the provided id.
     *
     * @param id The id of the member to remove.
     * @throws UnknownUserIdException When a member with the provided id doesn't exist.
     */
    public void removeMember(int id) throws UnknownUserIdException
    {
        for (User member : members) {
            if (member.getId() == id) {
                members.remove(member);
                return;
            }
        }

        throw new UnknownUserIdException(id);
    }

    /**
     * Removes the member referenced by the provided {@link UserReference}.
     *
     * @param userReference The {@link UserReference} referencing the member to remove.
     * @throws UnknownUserReferenceException When the member referenced by the {@link UserReference} doesn't exist.
     */
    public void removeMember(UserReference userReference) throws UnknownUserReferenceException
    {
        try {
            removeMember(userReference.getId());
        } catch (UnknownUserIdException e) {
            throw new UnknownUserReferenceException(userReference);
        }
    }

    /**
     * Removes the member matching the provided {@link User}.
     *
     * @param member The member to remove.
     * @throws UnknownUserException When the provided {@link User} doesn't exist as a member.
     */
    public void removeMember(User member) throws UnknownUserException
    {
        try {
            removeMember(member.getId());
        } catch (UnknownUserIdException e) {
            throw new UnknownUserException(member);
        }
    }

    /**
     * Checks if a {@link User} with the provided id is a members of the {@link Team}.
     *
     * @param id The id of the {@link User} to check for.
     * @return True when a {@link User} with the provided id is a member of the {@link Team}. Returns false otherwise.
     */
    public boolean hasMember(int id)
    {
        for (User member : members)
            if (member.getId() == id)
                return true;

        return false;
    }

    /**
     * Checks if the {@link User} referenced by the provided {@link UserReference} is a members of the {@link Team}.
     *
     * @param userReference The {@link UserReference} referencing the {@link User} to check the membership of.
     * @return True when a {@link User} referenced by the provided {@link UserReference} is a member of the
     * {@link Team}. Returns false otherwise.
     */
    public boolean hasMember(UserReference userReference)
    {
        return hasMember(userReference.getId());
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

    @Override
    public String toString()
    {
        return "Team{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(getName(), team.getName()) &&
                Objects.equals(getMembers(), team.getMembers()) &&
                Objects.equals(getId(), team.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getName(), getMembers());
    }
}
