import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ExpensesWindowController {

    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnHide;
    @FXML
    private TableView<?> expensesTable;
    @FXML
    private Label monthLbl;
    @FXML
    private Pane titlePane;
    @FXML
    private Label yearLbl;

    private double x, y;

    public void init(Stage stage) {

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> {
            stage.close();
        });
        btnHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
}
