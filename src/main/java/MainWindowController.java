import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;


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
    private Stage stage_expenses = new Stage();

    ConfigManager configManager = new ConfigManager();
    DBConnector dbConnector = new DBConnector();
    ExpensesWindowController expensesWindowController = new ExpensesWindowController();


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

        ObservableList<String> months =
                FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December");
        monthCBox.setItems(months);
        monthCBoxTotal.setItems(months);

        ObservableList<String> years =
                FXCollections.observableArrayList("2022", "2023");
        yearCBox.setItems(years);
        yearCBoxTotal.setItems(years);

        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                    amountField.setText(oldValue);
                }
            }
        });
    }

    @FXML
    void onSaveBtnClicked(MouseEvent mouseEvent) throws SQLException {

        System.out.println(monthCBox.getValue());
        System.out.println(yearCBox.getValue());
        System.out.println(amountField.getText());
        System.out.println(descriptionField.getText());

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


    }

    @FXML
    void onShowExpensesTotalClicked(MouseEvent event) throws SQLException {

        infoLabel.setVisible(false);
        LblTotal.setText("0.0");
        System.out.println("TOTAL SUM");
        System.out.println(monthCBoxTotal.getValue());
        System.out.println(yearCBoxTotal.getValue());
        if (!monthCBoxTotal.getValue().equals("") && !yearCBoxTotal.getValue().equals("")) {
            System.out.println("im here");
            QueryCreator queryCreator = new QueryCreator(monthCBoxTotal.getValue(), yearCBoxTotal.getValue(), configManager.getTableName());
            String sqlTotalQuery = queryCreator.createSumByMonthYearQuery();
            String sum = dbConnector.getTotalAmountByMonthYear(sqlTotalQuery);
            LblTotal.setText(sum);
        }

    }

    @FXML
    void onShowExpensesListClicked(MouseEvent event) {

        System.out.println("sdsds");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ExpensesWindowInterface.fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);

            stage_expenses.setScene(scene);
            stage_expenses.initStyle(StageStyle.TRANSPARENT);
            ((ExpensesWindowController)loader.getController()).init(stage_expenses);
            stage_expenses.setResizable(false);
            stage_expenses.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
