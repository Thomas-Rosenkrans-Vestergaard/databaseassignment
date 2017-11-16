package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
import tvestergaard.databaseassignment.database.users.User;

import java.util.ArrayList;
import java.util.List;

public class Team
{

	private final int    id;
	private       String name;
	private final List<User> members = new ArrayList<>();

	public Team(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void addMember(User member)
	{
		this.members.add(member);
	}

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
