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

public class AddNewBookLocalizationController implements Initializable {

    @FXML
    private AnchorPane newLocalizationPane;

    @FXML
    private JFXButton closeButtonID;

    @FXML
    private JFXButton applyButtonID;

    @FXML
    private TextField localizationBookID;

    private CatalogueDAO catalogueDAO = new CatalogueDAO();
    private long currentISBN = 0;

    @FXML
    void applyButton(ActionEvent event) {
        if(!localizationBookID.getText().isEmpty()){
            catalogueDAO.addNewBookLocalization(currentISBN,localizationBookID.getText());
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

    public void setISBN(long isbn){
        currentISBN = isbn;
    }
}
