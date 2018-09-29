package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ExtensionController implements Initializable{
	
    @FXML
    private AnchorPane mainPane;
    
	@FXML
    private HBox topBox;

    @FXML
    private JFXTextField bookID;

    @FXML
    private JFXTextField readerID;

    @FXML
    private JFXButton searchButt;

    @FXML
    private HBox middleBox;

    @FXML
    private Label dateLendText;

    @FXML
    private Label dateRetrunText;

    @FXML
    private Label dataLendShow;

    @FXML
    private Label dateRetrunShow;

    @FXML
    private HBox downBox;

    @FXML
    private Label newDataText;

    @FXML
    private JFXDatePicker dataPicker;

    @FXML
    private JFXButton applyButt;

    @FXML
    void apply(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		middleBox.translateYProperty().bind((mainPane.heightProperty().divide(2)).subtract(middleBox.heightProperty()));
		middleBox.translateXProperty().bind((mainPane.widthProperty().subtract(middleBox.widthProperty())).divide(2));
		
	}

}
