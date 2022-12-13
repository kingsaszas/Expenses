import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ExpensesWindow {

    private final Stage stage_expenses;

    public ExpensesWindow(Stage stage) {
        this.stage_expenses = stage;
    }

    protected void createListExpensesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ExpensesWindowInterface.fxml"));
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
}
