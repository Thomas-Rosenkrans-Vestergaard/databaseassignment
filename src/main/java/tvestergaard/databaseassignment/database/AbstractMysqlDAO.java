package tvestergaard.databaseassignment.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An abstract base class for data access objects using a {@link MysqlDataSource}.
 */
public abstract class AbstractMysqlDAO
{

	/**
	 * The {@link MysqlDataSource} used to retrieve data.
	 */
	private MysqlDataSource dataSource;

	/**
	 * Creates a new {@link AbstractMysqlDAO}.
	 *
	 * @param dataSource The {@link MysqlDataSource} used to retrieve data.
	 */
	public AbstractMysqlDAO(MysqlDataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	/**
	 * Returns a new {@link Connection} that can be used to query the {@link MysqlDataSource}.
	 *
	 * @return The new {@link Connection} used to query the {@link MysqlDataSource}.
	 * @throws SQLException When an exception occurs while creating the new {@link Connection}.
	 */
	protected Connection newConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
