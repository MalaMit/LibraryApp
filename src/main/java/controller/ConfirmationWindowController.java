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

public class ConfirmationWindowController implements Initializable {

	@FXML
	private Pane head;

	@FXML
	private Button closeHead;

	@FXML
	private Label text;

	@FXML
	private JFXButton yesButton;

	@FXML
	private JFXButton noButton;
	
	public boolean choicYes = false;

	@FXML
	void closeHeadAction(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void noButtAction(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void yesButtAction(ActionEvent event) {
		choicYes = true;
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setText(String statement) {
		text.setText(statement);
	}

}
