import java.io.ObjectInputFilter;
import java.sql.*;

public class DBConnector {

    private static ConfigManager configManager = new ConfigManager();
    public static Connection connection;
    public static Statement statement;

    public DBConnector() {
        try {
            connection = DriverManager.getConnection(configManager.dbSchema, configManager.username,
                    configManager.password);
            statement = connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

//    public static void createConnection(String tableQuery) {
//        System.out.println(configManager.dbSchema);
//        System.out.println(configManager.username);
//        System.out.println(configManager.password);
//
//        try {
//            connection = DriverManager.getConnection(configManager.dbSchema, configManager.username,
//                    configManager.password);
//            //DatabaseMetaData dbm = connection.getMetaData();
//            //ResultSet tables = dbm.getTables(null, null, configManager.tableName, null);
//            statement = connection.createStatement();
//            statement.executeUpdate(tableQuery);
//            connection.close();
//
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//    }

    public void updateSqlDataBase(String query) throws SQLException {
        statement.executeUpdate(query);
        //connection.close();
    }

    public String getTotalAmountByMonthYear(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);
        result.next();
        String sum = result.getString(1);
        if (sum == null)
            sum = "0.0";
        System.out.println("SUM: " + sum);
        //connection.close();
        return sum;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
