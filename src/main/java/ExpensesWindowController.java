import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML
    private TableColumn<?, ?> descriptionColumn;
    @FXML
    private TableColumn<?, ?> amountColumn;

    private double x, y;
    protected String year, month;

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
            expensesTable.getItems().clear();
            stage.hide();
        });
        btnHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    public void setExpensesTable(TableView<?> expensesTable) {
        this.expensesTable = expensesTable;
    }

    public void setMonthLbl(String monthString) {
        this.monthLbl.setText(monthString);
    }

    public void setYearLbl(String yearString) {
        this.yearLbl.setText(yearString);
    }

    public void setAmountColumn(String amountValue) {
        this.amountColumn = new TableColumn<>(amountValue);
    }

    public void setDescriptionColumn(String descValue) {
        this.descriptionColumn = new TableColumn<>(descValue);
    }



}
