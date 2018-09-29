package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ErrorMassController implements Initializable {
	
    @FXML
    private Pane head;

    @FXML
    private Button closeHead;

    @FXML
    private Label textError;
    
    @FXML
    private Pane iconView;

    @FXML
    private JFXButton okButtton;

    @FXML
    void closeHeadAction(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void okButtAction(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTextError(String statement) {
		textError.setText(statement);
	}

}
