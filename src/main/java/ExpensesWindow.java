import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ExpensesWindow {

    private final Stage STAGE_EXPENSES;
    FXMLLoader loader;

    public ExpensesWindow(Stage stage) {
        this.STAGE_EXPENSES = stage;
    }

    protected void createListExpensesWindow() {
        try {
            loader = new FXMLLoader(getClass().getResource("/ExpensesWindowInterface.fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);

            STAGE_EXPENSES.setScene(scene);
            STAGE_EXPENSES.initStyle(StageStyle.TRANSPARENT);
            ((ExpensesWindowController)loader.getController()).init(STAGE_EXPENSES);
            STAGE_EXPENSES.setResizable(false);
            //STAGE_EXPENSES.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLabels(String month, String year) {
        ((ExpensesWindowController)loader.getController()).setMonthLbl(month);
        ((ExpensesWindowController)loader.getController()).setYearLbl(year);
    }

    public void setAmountAndDescription(ArrayList<String> amountList, ArrayList<String> descList) {
        ((ExpensesWindowController) loader.getController()).setExpensesTable(amountList,descList);

    }

}
