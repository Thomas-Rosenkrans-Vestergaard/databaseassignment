package tvestergaard.databaseassignment.database;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DefaultMysqlDataSource extends MysqlDataSource
{
    public DefaultMysqlDataSource()
    {
        setServerName("localhost");
        setUser("test");
        setPassword("test");
        setDatabaseName("databaseassignment");
    }
}
