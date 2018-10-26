package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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

public class ReturnBookController implements Initializable{
	
	 @FXML
	    private AnchorPane mainAnchorPane;

	    @FXML
	    private AnchorPane topSection;

	    @FXML
	    private JFXTextField idReader;

	    @FXML
	    private JFXButton searchButt;

	    @FXML
	    private JFXButton applyReturnButt;

	    @FXML
	    private AnchorPane dataViewSection;

	    @FXML
	    private Label lendDateLabel;

	    @FXML
	    private Label bookBeReturnLabel;

	    @FXML
	    private Label timeOutLabel;

	    @FXML
	    private Label payLabel;
	    
	    private LendBook lendB = null;
	    private AlertMaker alertMaker = new AlertMaker();
	    private ReturnAndExtensionDAO retAndExtenDAO = new ReturnAndExtensionDAO();

    @FXML
    void applyReturn(ActionEvent event) {

    }

    @FXML
    void searchReaderID(ActionEvent event) {
		boolean readerIDCheck = DataValidation.textNumber(idReader.getText(), 1, 20);

		if (!idReader.getText().isEmpty() && readerIDCheck)
			lendB = retAndExtenDAO.extensionBook(Long.valueOf(idReader.getText()));

		if (lendB != null) {
			lendDateLabel.setText(String.valueOf(lendB.getLend_Date()));
			bookBeReturnLabel.setText(String.valueOf(lendB.getReturn_Date()));
			
			long amountDays = daysBetween(java.sql.Date.valueOf(LocalDate.now()), lendB.getReturn_Date());
			
			if(amountDays > 0) {
				timeOutLabel.setText(amountDays + " days");
				payLabel.setText(amountDays * 0.50 + " zł");
			}else
				timeOutLabel.setText("0 days");
		} else {
			alertMaker.showSimpleAlert("Book is wrong!!!", "errorMessage.css");
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainAnchorPane.heightProperty().addListener(e->{
				topSection.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(topSection.widthProperty())).divide(2));
				dataViewSection.translateYProperty().bind(topSection.minHeightProperty());
				dataViewSection.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(dataViewSection.widthProperty())).divide(2));
		});
		
	}
	
	 public static long daysBetween(Date startDate, Date endDate) {
		 long diff = startDate.getTime() - endDate.getTime();
	 
	        return (diff / (1000 * 60 * 60 * 24));
	    }

}
