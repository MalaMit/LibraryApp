package controller;

import com.jfoenix.controls.JFXButton;
import dao.CatalogueDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeBookLocalizationController implements Initializable {

    @FXML
    private AnchorPane newLocalizationPane;

    @FXML
    private TextField currentLocalizationFieldID;

    @FXML
    private TextField newLocalizationFieldID;

    @FXML
    private JFXButton applyButtonID;

    @FXML
    private JFXButton closeButtonID;

    private long currentBookID = 0;
    private CatalogueDAO catalogueDAO = new CatalogueDAO();

    @FXML
    void applyButton(ActionEvent event) {
        if(!newLocalizationFieldID.getText().isEmpty()){
            catalogueDAO.changeBookLokalization(currentBookID, newLocalizationFieldID.getText());
            newLocalizationPane.getScene().getWindow().hide();
        }
    }

    @FXML
    void close(ActionEvent event) {
        newLocalizationPane.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setViewCurrentLocalization(String localization, long bookID){
        currentLocalizationFieldID.setText(localization);
        currentBookID = bookID;
    }
}
