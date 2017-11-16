package tvestergaard.databaseassignment;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DefaultMysqlDataSource extends MysqlDataSource
{
	public DefaultMysqlDataSource()
	{
		setServerName("localhost");
		setPort(3306);
		setDatabaseName("databaseassignment");
		setUser("root");
		setPassword("2231302509");
	}
}
