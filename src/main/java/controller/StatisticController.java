package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatisticController implements Initializable{

	@FXML
    private HBox amountBookBox;

    @FXML
    private Label booksLabel;

    @FXML
    private HBox copyBookBox;

    @FXML
    private Label copiesLabel;

    @FXML
    private HBox accountBox;

    @FXML
    private Label accountLabel;

    @FXML
    private HBox lendBookBox;

    @FXML
    private Label borrowLabel;

    @FXML
    private HBox holdoverBox;

    @FXML
    private Label timeOutLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
