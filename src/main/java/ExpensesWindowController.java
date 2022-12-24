import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class ExpensesWindowController {

    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnHide;
    @FXML
    private TableView<Expense> expensesTable;
    @FXML
    private Label monthLbl;
    @FXML
    private Pane titlePane;
    @FXML
    private Label yearLbl;
    @FXML
    private TableColumn<Expense, String> descriptionColumn;
    @FXML
    private TableColumn<Expense, String> amountColumn;

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

    public void setExpensesTable(ArrayList<String> amountList, ArrayList<String> descList) {
        System.out.println("im here");
        ObservableList<Expense> expenseObservableList = FXCollections.observableArrayList();

        for(int i = 0; i < amountList.size(); i++) {
            System.out.println(amountList.get(i) + " " + descList.get(i));
            expenseObservableList.add(new Expense(amountList.get(i), descList.get(i)));
        }

        System.out.println(expenseObservableList.get(1).toString());

        amountColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("amount"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));

        expensesTable.setItems(expenseObservableList);

    }

    public void setMonthLbl(String monthString) {
        this.monthLbl.setText(monthString);
    }

    public void setYearLbl(String yearString) {
        this.yearLbl.setText(yearString);
    }

}
