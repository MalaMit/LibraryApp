package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dao.ReaderDAO;
import dataValidation.DataValidation;
import entities.Reader;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ReaderController implements Initializable {
	
    @FXML
    private AnchorPane coverPane;

	@FXML
	private JFXTextField dataField;

	@FXML
	private JFXComboBox<String> typeSearch;

	@FXML
	private JFXButton searchButt;

	@FXML
	private JFXButton createReaderButt;

	@FXML
	private JFXButton editReaderButt;

	@FXML
	private JFXButton deleteReaderButt;

	@FXML
	private TableView<Reader> tableReader;

	@FXML
	private TableColumn<Reader, Long> idReaderColumn;

	@FXML
	private TableColumn<Reader, String> nameReaderColumn;

	@FXML
	private TableColumn<Reader, String> surnameReaderColumn;

	@FXML
	private TableColumn<Reader, Long> peselReaderColumn;

	@FXML
	private TableColumn<Reader, String> addressReaderColumn;

	@FXML
	private TableColumn<Reader, String> phoneReaderColumn;

	private ReaderDAO readearDAO = new ReaderDAO();
	private ObservableList<Reader> readers = readearDAO.allReaders();
	private ObservableList<String> typeToSearchReader = FXCollections.observableArrayList("Identifical Number", "Name and surname", "Pesel");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idReaderColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReader_ID()));
		nameReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		surnameReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
		peselReaderColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPesel()));
		addressReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
		phoneReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone_number()));
		
		tableReader.setItems(readers);
		
		typeSearch.setItems(typeToSearchReader);
		
		tableReader.heightProperty().addListener(e->{
			idReaderColumn.setPrefWidth((tableReader.getWidth()/6));
			nameReaderColumn.setPrefWidth((tableReader.getWidth()/6));
			surnameReaderColumn.setPrefWidth((tableReader.getWidth()/6));
			peselReaderColumn.setPrefWidth((tableReader.getWidth()/6));
			addressReaderColumn.setPrefWidth((tableReader.getWidth()/6));
			phoneReaderColumn.setPrefWidth((tableReader.getWidth()/6));
		});
		
	}
	
	@FXML
	void createReader(ActionEvent event) {
		coverPane.setPrefHeight(coverPane.getScene().getHeight());
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/CreateNewReader.fxml"));

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/createNewReader.css").toExternalForm());

			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.initStyle(StageStyle.UNDECORATED);
			window.setTitle("Create Reader");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		coverPane.setPrefHeight(0);
		
		tableReader.setItems(readearDAO.allReaders());
	}

	@FXML
	void deleteReader(ActionEvent event) {
		if (!tableReader.getSelectionModel().isEmpty()) {
				readearDAO.deleteReader(tableReader.getSelectionModel().getSelectedItem());
				tableReader.setItems(readearDAO.allReaders());
		}
	}

	@FXML
	void editReader(ActionEvent event) {
		if(!tableReader.getSelectionModel().isEmpty()) {
			
			coverPane.setPrefHeight(coverPane.getScene().getHeight());
			
			Parent parent = null;

			try {
				FXMLLoader loader = new FXMLLoader();
				parent = loader.load(getClass().getResource("/fxml/EditDataReader.fxml").openStream());
				EditDataReaderController editDataReaderController = (EditDataReaderController) loader.getController();
				editDataReaderController
						.loadDataReader(tableReader.getSelectionModel().getSelectedItem());

				Scene scene = new Scene(parent);

				scene.getStylesheets().add(getClass().getResource("/css/editDataReader.css").toExternalForm());

				Stage window = new Stage();

				window.setScene(scene);
				window.initModality(Modality.APPLICATION_MODAL);
				window.initStyle(StageStyle.UNDECORATED);
				window.setTitle("Update Data");
				window.setResizable(false);
				window.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			coverPane.setPrefHeight(0);
			
			tableReader.setItems(readearDAO.allReaders());
		}
	}

	@FXML
	void search(ActionEvent event) {
		if(!typeSearch.getSelectionModel().isEmpty() && !dataField.getText().isEmpty()){
			switch (typeSearch.getSelectionModel().getSelectedItem()) {
			case "Identifical Number":
				if(DataValidation.textNumber(dataField.getText(), 1, 20))
					readers = readearDAO.searchReader(dataField.getText(), "0");
				break;
			case "Name and surname":
				if(DataValidation.nameAndSurname(dataField.getText()))
					readers = readearDAO.searchReader(dataField.getText(), "1");
				break;
			case "Pesel":
				if(DataValidation.textNumber(dataField.getText(), 1, 20))
					readers = readearDAO.searchReader(dataField.getText(), "2");
				break;
			default:
				readers.clear();
				break;
			}
			tableReader.setItems(readers);
		}
	}

}
