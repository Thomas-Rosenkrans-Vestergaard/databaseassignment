package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
import tvestergaard.databaseassignment.database.users.UserReference;

import java.util.HashSet;
import java.util.Set;

public class TeamBuilder
{

    /**
     * The name of the {@link Team} to build.
     */
    private String name;

    /**
     * The members of the {@link Team} to build.
     */
    private Set<UserReference> members = new HashSet<>();

    /**
     * Returns the name of the {@link Team} to build.
     *
     * @return The name of the {@link Team} to build.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name of the {@link Team} to build.
     *
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Adds the provided {@link UserReference} as a member of the {@link Team} to build.
     *
     * @param user The {@link UserReference} to add as a member.
     */
    public void addMember(UserReference user)
    {
        this.members.add(user);
    }

    /**
     * Returns an {@link ImmutableList} of the members in the {@link Team} to build.
     *
     * @return The {@link ImmutableList} of the members in the {@link Team} to build.
     */
    public ImmutableList<UserReference> getMembers()
    {
        return ImmutableList.copyOf(members);
    }
}
