package controller;

import java.awt.MouseInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.AdministratorDAO;
import dataValidation.AlertMaker;
import dataValidation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import startApp.Main;

public class LoginController implements Initializable {
	private AdministratorDAO adm = new AdministratorDAO();
	
	
	@FXML
    private AnchorPane backGround;

    @FXML
    private AnchorPane paneLogAndPass;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private Button loginInButton;

    @FXML
    private AnchorPane head;

    @FXML
    private JFXButton exitButt;

    @FXML
    private JFXButton minButt;
    
    @FXML
    private Label loginIsEmpty;

    @FXML
    private Label passIsEmpty;

    private double xOffSet;
    private double yOffSet;
    
    private AlertMaker alertMaker = new AlertMaker();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		moveWindow();
		
		passField.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER){
				login(null);
			}
		});
	}
	
    @FXML
    void login(ActionEvent event) {
    	
		boolean login = DataValidation.textAlphabetAndNumber(loginField.getText().toString(), 1, 20);
		boolean password = DataValidation.textAlphabetAndNumber(passField.getText().toString(), 1, 20);
		
		loginIsEmpty.setText("");
		passIsEmpty.setText("");

		if (login && password) {
			if (adm.checkLoginData(loginField.getText().toString(), passField.getText().toString())) {
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));

					Scene scene = new Scene(parent);
					scene.getStylesheets().add(getClass().getResource("/css/mainView.css").toExternalForm());

					Main.primaryStage.getScene().getStylesheets().clear();

					Main.primaryStage.setScene(scene);

					Main.primaryStage.centerOnScreen();

					Main.primaryStage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				alertMaker.showSimpleAlert("Username or password is no correct. Please try again!", "errorMessage.css");
			}
		} else {
			
			if(!login)
				loginIsEmpty.setText("Please enter your username");
			if(!password)
				passIsEmpty.setText("Please enter your password");
		}
	}
    
    @FXML
    void exitButt(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void minButt(ActionEvent event) {
    	Main.primaryStage.setIconified(true);
    }
    
    private void moveWindow() {
    	head.setOnMousePressed(e -> {
			
    		xOffSet = MouseInfo.getPointerInfo().getLocation().getX()/2 - e.getScreenX();
			yOffSet = MouseInfo.getPointerInfo().getLocation().getY() - e.getScreenY();
			
			head.setCursor(javafx.scene.Cursor.CLOSED_HAND);
		});
		
    	head.setOnMouseDragged(e -> {
			
			Main.primaryStage.setX(e.getScreenX() + xOffSet);
			Main.primaryStage.setY(e.getScreenY() + yOffSet);
			
		});
		
    	head.setOnMouseReleased(e -> {
			
    		head.setCursor(javafx.scene.Cursor.DEFAULT);
		});
	}

}
