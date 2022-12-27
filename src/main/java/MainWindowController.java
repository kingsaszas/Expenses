import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


public class MainWindowController {
    @FXML
    private ImageView btnClose, btnHide;
    @FXML
    private Pane titlePane;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> monthCBox, yearCBox;
    @FXML
    private TextField amountField, descriptionField;
    @FXML
    private Label infoLabel;

    //TOTAL SECTION
    @FXML
    private Label LblTotal;
    @FXML
    private Button btnShowExpensesTotal;
    @FXML
    private Button btnShowTotalAmount;
    @FXML
    private ComboBox<String> monthCBoxTotal;
    @FXML
    private ComboBox<String> yearCBoxTotal;

    private double x, y;
    private final Stage stage_expenses = new Stage();

    ConfigManager configManager = new ConfigManager();
    DBConnector dbConnector = new DBConnector();
    ExpensesWindow expensesWindow = new ExpensesWindow(stage_expenses);

    public void init(Stage stage) {

        infoLabel.setVisible(false);
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
            stage_expenses.close();
            try {
                dbConnector.closeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        Calendar now = Calendar.getInstance();

        ObservableList<String> months =
                FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December");
        monthCBox.setItems(months);
        monthCBox.getSelectionModel().select(now.get(Calendar.MONTH));
        monthCBoxTotal.setItems(months);
        monthCBoxTotal.getSelectionModel().select(now.get(Calendar.MONTH));

        ObservableList<String> years =
                FXCollections.observableArrayList("2022", "2023");
        yearCBox.setItems(years);
        yearCBox.getSelectionModel().select(String.valueOf(now.get(Calendar.YEAR)));
        yearCBoxTotal.setItems(years);
        yearCBoxTotal.getSelectionModel().select(String.valueOf(now.get(Calendar.YEAR)));

        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                    amountField.setText(oldValue);
                }
            }
        });

        expensesWindow.createListExpensesWindow();
    }

    @FXML
    void onSaveBtnClicked(MouseEvent mouseEvent) throws SQLException {

        if (monthCBox.getValue() == null || yearCBox.getValue() == null || amountField.getText() == null
                || descriptionField.getText() == null) {
            //open new window with warning or show information about empty fields.
            infoLabel.setText("Empty fields are not allowed");
            infoLabel.setVisible(true);
        } else {
            infoLabel.setVisible(false);
            QueryCreator queryCreator = new QueryCreator(monthCBox.getValue(), yearCBox.getValue(),
                    Double.parseDouble(amountField.getText()), descriptionField.getText(), configManager.getTableName());
            String sqlAddQuery = queryCreator.createInsertQuery();
            dbConnector.updateSqlDataBase(sqlAddQuery);
            infoLabel.setText("Expense was added");
            infoLabel.setVisible(true);
        }

        amountField.setText("");
        descriptionField.setText("");
    }

    @FXML
    void onShowExpensesTotalClicked(MouseEvent event) throws SQLException {

        infoLabel.setVisible(false);
        LblTotal.setText("0.0");
        if (!monthCBoxTotal.getValue().equals("") && !yearCBoxTotal.getValue().equals("")) {
            QueryCreator queryCreator = new QueryCreator(monthCBoxTotal.getValue(), yearCBoxTotal.getValue(), configManager.getTableName());
            String sqlTotalQuery = queryCreator.createSumByMonthYearQuery();
            String sum = dbConnector.getTotalAmountByMonthYear(sqlTotalQuery);
            LblTotal.setText(sum);
        }
    }

    @FXML
    void onShowExpensesListClicked(MouseEvent event) throws SQLException {

        if (!monthCBoxTotal.getValue().equals("") && !yearCBoxTotal.getValue().equals("")) {
            expensesWindow.setLabels(monthCBoxTotal.getValue(), yearCBoxTotal.getValue());

            QueryCreator queryCreator = new QueryCreator(monthCBoxTotal.getValue(), yearCBoxTotal.getValue(), configManager.getTableName());
            String sqlCountQuery = queryCreator.createCountRowsQuery();
            int countRows = dbConnector.getCountRows(sqlCountQuery);

            ArrayList<String> amountList;
            ArrayList<String> descriptionList;

            //take amountValues to the list
            String sqlAmountQuery = queryCreator.createGetAmountQuery();
            amountList = dbConnector.getSimpleColumnValues(sqlAmountQuery);

            //take descValues to the list
            String sqlDescQuery = queryCreator.createGetDescriptionQuery();
            descriptionList = dbConnector.getSimpleColumnValues(sqlDescQuery);

            //pass iterator and two lists to the method in expenses Window
            expensesWindow.setAmountAndDescription(amountList, descriptionList);
            amountList.clear();
            descriptionList.clear();
            stage_expenses.show();

        }
    }


}
