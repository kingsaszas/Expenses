public class QueryCreator {
    String month;
    String year;
    double amount;
    String description;
    String tablename;

    public QueryCreator(String month, String year, double amount, String description, String tablename) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.description = description;
        this.tablename = tablename;
    }

    public String createQuery() {

        StringBuilder stringBuilder = new StringBuilder("INSERT INTO " + tablename + " (YEAR, MONTH, DESCRIPTION, AMOUNT)\n" +
                "VALUES (" + year +", \"" + month + "\", \"" + description + "\", " + amount +");");
        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }
}
