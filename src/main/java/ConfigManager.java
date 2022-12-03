public class ConfigManager {

    public String dbSchema;
    public String username;
    public String password;
    public static String tableName;

    protected ConfigManager() {

        dbSchema = "jdbc:mysql://localhost:3306/expenses";
        username = "root";
        password = "root1234";
        tableName = "data";
    }

    public String getTableName() {
        return tableName;
    }
}
