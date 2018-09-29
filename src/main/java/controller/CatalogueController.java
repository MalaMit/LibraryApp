package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CatalogueController implements Initializable {

	@FXML
	private JFXTextField dataSearch;

	@FXML
	private JFXComboBox<?> typeSearch;

	@FXML
	private JFXButton searchButt;

	@FXML
	private JFXButton addNewBookButt;

	@FXML
	private JFXButton editBookButt;

	@FXML
	private JFXButton deleteBookButt;

	@FXML
	private TableView<?> bookTable;

	@FXML
	private TableColumn<?, ?> idBookColumn;

	@FXML
	private TableColumn<?, ?> isbnColumn;

	@FXML
	private TableColumn<?, ?> tittleColumn;

	@FXML
	private TableColumn<?, ?> autorColumn;

	@FXML
	private TableColumn<?, ?> publicationYearColumn;

	@FXML
	private TableColumn<?, ?> typeColumn;

	@FXML
	private TableColumn<?, ?> localizationColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	void addNewBook(ActionEvent event) {

	}

	@FXML
	void deleteBook(ActionEvent event) {

	}

	@FXML
	void editBook(ActionEvent event) {

	}

	@FXML
	void search(ActionEvent event) {

	}

}
