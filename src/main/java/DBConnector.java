import java.io.ObjectInputFilter;
import java.sql.*;

public class DBConnector {

    private static ConfigManager configManager = new ConfigManager();

    public static void createConnection(String tableQuery) {
        System.out.println(configManager.dbSchema);
        System.out.println(configManager.username);
        System.out.println(configManager.password);

        try {
            Connection connection = DriverManager.getConnection(configManager.dbSchema, configManager.username,
                    configManager.password);
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, configManager.tableName, null);
            Statement statement = connection.createStatement();
            statement.executeUpdate(tableQuery);
            connection.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } {

        }
    }

}
