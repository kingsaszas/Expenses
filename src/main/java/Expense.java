public class Expense {
    protected String amount;
    protected String description;

    public Expense(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
