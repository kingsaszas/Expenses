import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    public void setExpensesTable(TableView<?> expensesTable) {
        this.expensesTable = expensesTable;
    }

    public void setMonthLbl(Label monthLbl) {
        this.monthLbl = monthLbl;
    }

    public void setYearLbl(Label yearLbl) {
        this.yearLbl = yearLbl;
    }

    private double x, y;
    protected String year, month;

//    public ExpensesWindowController(String year, String month) {
//        this.year = year;
//        this.month = month;
//    }

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
            stage.hide();
        });
        btnHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
}
