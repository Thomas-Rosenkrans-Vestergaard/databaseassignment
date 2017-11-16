package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
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

	@Override public String toString()
	{
		return "Team{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   '}';
	}
}
