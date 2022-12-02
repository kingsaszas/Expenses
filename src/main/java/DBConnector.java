import java.io.ObjectInputFilter;
import java.sql.*;

public class DBConnector {

    private ConfigManager configManager = new ConfigManager();

    public void createConnection(String tableQuery) {

        try {
            Connection connection = DriverManager.getConnection(configManager.dbSchema, configManager.username,
                    configManager.password);
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, configManager.tableName, null);
            Statement statement = connection.createStatement();
            statement.executeUpdate(tableQuery);

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } {

        }
    }

}
