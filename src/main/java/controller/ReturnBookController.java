package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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
	    private Label borrowDateLabel;

	    @FXML
	    private Label bookBeReturnLabel;

	    @FXML
	    private Label timeOutLabel;

	    @FXML
	    private Label payLabel;

    @FXML
    void applyReturn(ActionEvent event) {

    }

    @FXML
    void searchReaderID(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainAnchorPane.heightProperty().addListener(e->{
				topSection.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(topSection.widthProperty())).divide(2));
				dataViewSection.translateYProperty().bind(topSection.minHeightProperty());
				dataViewSection.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(dataViewSection.widthProperty())).divide(2));
		});
		
	}

}
