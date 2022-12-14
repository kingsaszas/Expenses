import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ExpensesWindow {

    private final Stage stage_expenses;
    FXMLLoader loader;

    public ExpensesWindow(Stage stage) {
        this.stage_expenses = stage;
    }

    protected void createListExpensesWindow() {
        try {
            loader = new FXMLLoader(getClass().getResource("/ExpensesWindowInterface.fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);

            stage_expenses.setScene(scene);
            stage_expenses.initStyle(StageStyle.TRANSPARENT);
            ((ExpensesWindowController)loader.getController()).init(stage_expenses);
            stage_expenses.setResizable(false);
            //stage_expenses.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLabels(String month, String year) {
        ((ExpensesWindowController)loader.getController()).setMonthLbl(month);
        ((ExpensesWindowController)loader.getController()).setYearLbl(year);
    }

    public void setAmount() {
        //(ExpensesWindowController)loader.getController()).
    }

    public void setDescription() {
        //(ExpensesWindowController)loader.getController()).
    }
}
