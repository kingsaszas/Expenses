import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private ImageView btnClose, btnHide;
    @FXML
    private Pane titlePane;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<String> monthCBox, yearCBox;
    @FXML
    private TextField amountField, titleField;
    @FXML
    private Label warningLabel;

    private double x, y;


    public void init(Stage stage) {
        warningLabel.setVisible(false);
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        ObservableList<String> months =
                FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December");
        monthCBox.setItems(months);

        ObservableList<String> years =
                FXCollections.observableArrayList("2022", "2023");
        yearCBox.setItems(years);

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
    void onSaveBtnClicked(MouseEvent mouseEvent) {
        System.out.println(monthCBox.getValue());
        System.out.println(yearCBox.getValue());
        System.out.println(amountField.getText());
        System.out.println(titleField.getText());
        if (monthCBox.getValue() == null || yearCBox.getValue() == null || amountField.getText() == null
        || titleField.getText() == null) {
            //open new window with warning or show information about empty fields.
            warningLabel.setVisible(true);
        }else {
            warningLabel.setVisible(false);
            DataManager dm = new DataManager(monthCBox.getValue(), yearCBox.getValue(),
                    Double.parseDouble(amountField.getText()), titleField.getText());
        }


    }


}
