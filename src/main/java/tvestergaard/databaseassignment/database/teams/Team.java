package tvestergaard.databaseassignment.database.teams;

import com.google.common.collect.ImmutableList;
import org.omg.CORBA.UserException;
import tvestergaard.databaseassignment.database.users.UnknownUserIdException;
import tvestergaard.databaseassignment.database.users.UnknownUsernameException;
import tvestergaard.databaseassignment.database.users.User;

import java.util.ArrayList;
import java.util.List;

public class Team
{

    private final int id;
    private String name;
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


    public boolean hasMember(String username)
    {
        for (User member : members)
            if (member.getUsername() == username)
                return true;

        return false;
    }

    public boolean hasMember(int id)
    {
        for (User member : members)
            if (member.getId() == id)
                return true;

        return false;
    }

    public boolean hasMember(User member)
    {
        for (User x : members)
            if (x.equals(member))
                return true;

        return false;
    }

    public User getMember(String username) throws UnknownUsernameException
    {
        for (User user : members)
            if (user.equals(username))
                return user;

        throw new UnknownUsernameException(username);
    }
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (!name.equals(team.name)) return false;
        return members.equals(team.members);
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + members.hashCode();
        return result;
    }
}
