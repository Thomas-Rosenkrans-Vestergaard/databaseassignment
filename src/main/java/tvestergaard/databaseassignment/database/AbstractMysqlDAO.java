package tvestergaard.databaseassignment.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;

/**
 * An abstract base class for data access objects using a {@link MysqlDataSource}.
 */
public abstract class AbstractMysqlDAO implements DAO
{

    /**
     * The {@link MysqlDataSource} used to retrieve data.
     */
    protected final Connection connection;

    /**
     * Creates a new {@link AbstractMysqlDAO}.
     *
     * @param dataSource The {@link MysqlDataSource} used to retrieve data.
     * @throws OpenDAOException When a {@link Connection} couldn't be created using the provided {@link MysqlDataSource}.
     */
    public AbstractMysqlDAO(MysqlDataSource dataSource) throws OpenDAOException
    {
        try {
            this.connection = dataSource.getConnection();
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new OpenDAOException(e);
        }
    }

    /**
     * Closes the resource used by the {@link DAO}.
     *
     * @throws CloseDAOException When the {@link DAO} cannot be closed.
     */
    @Override public void close() throws CloseDAOException
    {
        try {
            connection.close();
        } catch (Exception e) {
            throw new CloseDAOException(e);
        }
    }
}
