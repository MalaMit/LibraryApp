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

public class LendBookController implements Initializable{
	
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane dataAnchorPane;

    @FXML
    private JFXTextField idBook;

    @FXML
    private JFXTextField idReader;

    @FXML
    private Label idBookLabel;

    @FXML
    private Label idReaderLabel;

    @FXML
    private Label datalabel;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private AnchorPane butttonAnchorPane;

    @FXML
    private JFXButton applyButt;

    @FXML
    void applyButtAction(ActionEvent event) {
    	System.out.println(mainAnchorPane.getHeight());
    	System.out.println(mainAnchorPane.getWidth());		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mainAnchorPane.heightProperty().addListener(e->{
		    	dataAnchorPane.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(dataAnchorPane.widthProperty())).divide(2));
				dataAnchorPane.translateYProperty().bind((mainAnchorPane.heightProperty().subtract(dataAnchorPane.heightProperty())).divide(4));
				butttonAnchorPane.translateXProperty().bind((mainAnchorPane.widthProperty().subtract(butttonAnchorPane.widthProperty())).divide(2));
		});
		
	}

}
