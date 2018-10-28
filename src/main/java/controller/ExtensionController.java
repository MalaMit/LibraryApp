package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import dao.ReturnAndExtensionDAO;
import dataValidation.AlertMaker;
import dataValidation.DataValidation;
import entities.LendBook;
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
    private JFXTextField readerID;

    @FXML
    private JFXButton searchButt;

    @FXML
    private HBox middleBox;

    @FXML
    private Label textField1;

    @FXML
    private Label textField2;

    @FXML
    private Label textField3;

    @FXML
    private Label readerIDLabel;

    @FXML
    private Label dataLendShow;

    @FXML
    private Label dateRetrunShow;

    @FXML
    private HBox downBox;

    @FXML
    private Label newDataText;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXButton applyButt;
    
    private LendBook lendB = null;
    private AlertMaker alertMaker = new AlertMaker();
    private ReturnAndExtensionDAO retAndExtenDAO = new ReturnAndExtensionDAO();
    
    @FXML
    void apply(ActionEvent event) {
    	if(lendB != null && !datePicker.getValue().equals(LocalDate.now())) {
    		lendB.setReturn_Date(java.sql.Date.valueOf(datePicker.getValue()));
    		
    		retAndExtenDAO.extensionBookApply(lendB);
    		
    		readerID.clear();
    		datePicker.setValue(LocalDate.now());
    		readerIDLabel.setText("");
    		dataLendShow.setText("");
    		dateRetrunShow.setText("");
    		
    	}else {
    		if(lendB == null)
    			alertMaker.showSimpleAlert("Date and Book are wrong!!!", "errorMessage.css");
    		else
    			alertMaker.showSimpleAlert("Date is wrong!!!", "errorMessage.css");
    	}
    }

    @FXML
    void search(ActionEvent event) {
    	
    	boolean readerIDCheck = DataValidation.textNumber(readerID.getText(), 1, 20);
    	
    	if(!readerID.getText().isEmpty() && readerIDCheck)
    		lendB =  retAndExtenDAO.extensionBook(Long.valueOf(readerID.getText()));
    	
    	if(lendB != null) {
    		readerIDLabel.setText(String.valueOf(lendB.getReader().getReader_ID()));
    		dataLendShow.setText(String.valueOf(lendB.getLend_Date()));
    		dateRetrunShow.setText(String.valueOf(lendB.getReturn_Date()));
    	}else {
    		alertMaker.showSimpleAlert("Book is wrong!!!", "errorMessage.css");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		middleBox.translateYProperty().bind((mainPane.heightProperty().divide(2)).subtract(middleBox.heightProperty()));
		middleBox.translateXProperty().bind((mainPane.widthProperty().subtract(middleBox.widthProperty())).divide(2));
		
		datePicker.setValue(LocalDate.now());
		
	}

}
