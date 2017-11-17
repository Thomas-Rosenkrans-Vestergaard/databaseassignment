package tvestergaard.databaseassignment.database.users;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserBuilderTest
{
    @Test
    public void getUsername() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        String expected = "Name";
        builder.setUsername(expected);
        assertEquals(expected, builder.getUsername());
    }

    @Test
    public void setUsername() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        String expected = "Name";
        builder.setUsername(expected);
        assertEquals(expected, builder.getUsername());
    }


    @Test
    public void getPassword() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        String expected = "Password";
        builder.setPassword(expected);
        assertEquals(expected, builder.getPassword());
    }

    @Test
    public void setPassword() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        String expected = "Password";
        builder.setPassword(expected);
        assertEquals(expected, builder.getPassword());
    }

    @Test
    public void isAdmin() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        boolean expected = true;
        builder.setAdmin(expected);
        assertEquals(expected, builder.isAdmin());
    }

    @Test
    public void setAdmin() throws Exception
    {
        UserBuilder builder = new UserBuilder();
        boolean expected = true;
        builder.setAdmin(expected);
        assertEquals(expected, builder.isAdmin());
    }

}