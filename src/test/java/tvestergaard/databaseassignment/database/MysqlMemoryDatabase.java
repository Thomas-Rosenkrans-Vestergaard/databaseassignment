package tvestergaard.databaseassignment.database;

import ch.vorburger.mariadb4j.DB;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.Statement;

public class MysqlMemoryDatabase
{

    private static MysqlDataSource dataSource;
    private static Connection connection;

    public static MysqlDataSource getDataSource() throws Exception
    {
        if (dataSource == null) {
            dataSource = createMemoryDatabase();
        }

        return dataSource;
    }

    public static void reset() throws Exception
    {

        Statement rollbackStatement = dataSource.getConnection().createStatement();
        rollbackStatement.executeUpdate("DELETE FROM team_members");
        rollbackStatement.executeUpdate("DELETE FROM teams");
        rollbackStatement.executeUpdate("DELETE FROM users");
        rollbackStatement.executeUpdate("INSERT INTO `teams` VALUES (1, 'A'), (2, 'B'), (3, 'C');");
        rollbackStatement.executeUpdate("INSERT INTO `users` VALUES (1, 'Anders And', '1234', '\u0001'), (2, 'Mickey Mouse', '5678', '\u0001'), (3, 'Fedtmule', '1234', '\\0'), (4, 'George Gearløs', '1234', '\\0'), (5, 'Bedstemor And', '1234', '\\0'), (6, 'Onkel Joakim', '1234', '\\0'), (7, 'Pluto', '1234', '\\0'), (8, 'Fætte Guf', '1234', '\\0');");
        rollbackStatement.executeUpdate("INSERT INTO `team_members` VALUES (2, 1, 2), (3, 1, 3), (4, 1, 7), (5, 2, 1), (6, 2, 4), (7, 2, 8), (8, 3, 1), (9, 3, 2), (10, 3, 3), (11, 3, 7), (12, 2, 5), (13, 2, 6);");
    }

    public static MysqlDataSource createMemoryDatabase() throws Exception
    {
        DB database = DB.newEmbeddedDB(3306);
        database.start();
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("test");
        dataSource.setURL("jdbc:mysql://localhost/test?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP SCHEMA IF EXISTS test");
        statement.executeUpdate("CREATE SCHEMA test");
        statement.executeUpdate("USE test");
        statement.executeUpdate("CREATE TABLE `users` ( `user_id` INT(11) NOT NULL AUTO_INCREMENT, `username` VARCHAR(45) NOT NULL, `password` VARCHAR(45) NOT NULL, `admin` BIT(1) NOT NULL, PRIMARY KEY (`user_id`) ) ENGINE = InnoDB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8;");
        statement.executeUpdate("CREATE TABLE `teams` ( `team_id` INT(11) NOT NULL AUTO_INCREMENT, `team_name` VARCHAR(45) NOT NULL, PRIMARY KEY (`team_id`) ) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;");
        statement.executeUpdate("CREATE TABLE `team_members` ( `team_member_id` INT(11) NOT NULL AUTO_INCREMENT, `team_id` INT(11) NOT NULL, `user_id` INT(11) NOT NULL, PRIMARY KEY (`team_member_id`), KEY `team_id` (`team_id`), KEY `user_id` (`user_id`), CONSTRAINT `team_members_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`), CONSTRAINT `team_members_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ) ENGINE = InnoDB AUTO_INCREMENT = 14 DEFAULT CHARSET = utf8;");

        return dataSource;
    }
}
