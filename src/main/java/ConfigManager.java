public class ConfigManager {

    public String dbSchema;
    public String username;
    public String password;
    public String tableName;
    private static ConfigManager instance;

    protected ConfigManager() {

        dbSchema = "jdbc:mysql://localhost:3306/expenses";
        username = "root";
        password = "root1234";
        tableName = "data";
    }

}
