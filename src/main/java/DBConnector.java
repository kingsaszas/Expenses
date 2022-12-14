import java.io.ObjectInputFilter;
import java.sql.*;
import java.util.ArrayList;

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

    public int getCountRows(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);
        result.next();
        String countRows = result.getString(1);
        return Integer.parseInt(countRows);
    }

    public ArrayList<String> getSimpleColumnValues(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);
        result.next();
        ArrayList<String> tempArray = new ArrayList<>();
        do {
            tempArray.add(result.getString(1));
        } while (result.next());
        return tempArray;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
