package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ReaderDAO;
import dataValidation.DataValidation;
import entities.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;


public class CreateNewReaderController implements Initializable {

    @FXML
    private StackPane mainStackPCreate;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField peselField;

    @FXML
    private TextField phoneField;

	private Reader reader = new Reader();
	private ReaderDAO readerDAO = new ReaderDAO();
	
    @FXML
    void closeWindow(ActionEvent event) {
    	mainStackPCreate.getScene().getWindow().hide();
    }

    @FXML
    void createButton(ActionEvent event) {
    	
    	boolean name = DataValidation.textAlphabetWithPolishMarks(nameField.getText(), 1, 30);
    	boolean surname = DataValidation.textAlphabetWithPolishMarks(surnameField.getText(), 1, 30);
    	boolean address = DataValidation.textAlphabetAndNumber(addressField.getText(), 1, 60);
    	boolean pesel = DataValidation.textNumber(peselField.getText(), 10, 20);
    	boolean phoneNumber = DataValidation.textPhone(phoneField.getText());

    	System.out.println(surnameField.getText() + " " + nameField.getText() );
    	
    	if(name && surname && address && pesel && phoneNumber) {
			reader.setName(nameField.getText());
			reader.setSurname(surnameField.getText());
			reader.setAddress(addressField.getText());
			reader.setPesel(Long.valueOf(peselField.getText()));
			reader.setPhone_number(phoneField.getText());

			readerDAO.createReader(reader);

			mainStackPCreate.getScene().getWindow().hide();
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

}
