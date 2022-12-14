public class QueryCreator {
    String month;
    String year;
    double amount;
    String description;
    String tablename;

    protected QueryCreator(String month, String year, double amount, String description, String tablename) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.description = description;
        this.tablename = tablename;
    }
    protected QueryCreator(String month, String year, String tablename) {
        this.month = month;
        this.year = year;
        this.tablename = tablename;
    }

    protected String createInsertQuery() {

        StringBuilder stringBuilder = new StringBuilder("INSERT INTO " + tablename + " (YEAR, MONTH, DESCRIPTION, AMOUNT)\n" +
                "VALUES (" + year +", \"" + month + "\", \"" + description + "\", " + amount +");");
        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    protected String createSumByMonthYearQuery() {

        StringBuilder stringBuilder = new StringBuilder("SELECT SUM(AMOUNT) FROM " + tablename + " WHERE YEAR = " +
                 year +" AND MONTH = \"" + month + "\";");
        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }

    protected String fillTableWithData() {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM " + tablename + " WHERE YEAR = " +
                year +" AND MONTH = \"" + month + "\";");
        return stringBuilder.toString();
    }

    protected String createCountRowsQuery() {

        StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) FROM " + tablename + " WHERE YEAR = " +
                year +" AND MONTH = \"" + month + "\";");

        return stringBuilder.toString();
    }
    protected String createGetAmountQuery() {

        StringBuilder stringBuilder = new StringBuilder("SELECT AMOUNT FROM " + tablename + " WHERE YEAR = " +
                year +" AND MONTH = \"" + month + "\";");

        return stringBuilder.toString();
    }
    protected String createGetDescriptionQuery() {

        StringBuilder stringBuilder = new StringBuilder("SELECT DESCRIPTION FROM " + tablename + " WHERE YEAR = " +
                year +" AND MONTH = \"" + month + "\";");

        return stringBuilder.toString();
    }
}
