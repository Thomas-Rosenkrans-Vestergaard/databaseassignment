package tvestergaard.databaseassignment.database;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DefaultMysqlDataSource extends MysqlDataSource
{
	public DefaultMysqlDataSource()
	{
		setServerName("localhost");
		setPort(3306);
		setDatabaseName("databaseassignment");
		setUser("test");
		setPassword("test");
	}
}
