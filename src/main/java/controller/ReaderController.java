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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
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
	private JFXButton createReaderButt;

	@FXML
	private JFXButton editReaderButt;

	@FXML
	private JFXButton deleteReaderButt;

	@FXML
	private JFXButton readerHistoryButton;

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
	private FilteredList<Reader> filteredData = new FilteredList<>(readers, p -> true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		idReaderColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReader_ID()));
		nameReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		surnameReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
		peselReaderColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPesel()));
		addressReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
		phoneReaderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone_number()));

		new Thread(()->{
			typeSearch.setItems(typeToSearchReader);
			typeSearch.setValue("Identifical Number");
			tableReader.setItems(readers);
		}).start();

		tableReader.heightProperty().addListener(e -> {
			idReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
			nameReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
			surnameReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
			peselReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
			addressReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
			phoneReaderColumn.setPrefWidth((tableReader.getWidth() / 6));
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
		if (!tableReader.getSelectionModel().isEmpty()) {

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
	void filterTable(KeyEvent event) {
		dataField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reader -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				switch (typeSearch.getSelectionModel().getSelectedItem()) {
					case "Identifical Number":
						if (String.valueOf(reader.getReader_ID()).toLowerCase().contains(lowerCaseFilter)) {
							return true;
						}
						break;
					case "Name and surname":
						StringBuilder sb = new StringBuilder().append(reader.getName()).append(" ").append(reader.getSurname());
						if (sb.toString().toLowerCase().contains(lowerCaseFilter)) {
							return true;
						}
						break;
					case "Pesel":
						if (String.valueOf(reader.getPesel()).toLowerCase().contains(lowerCaseFilter)) {
							return true;
						}
						break;
				}
				return false;
			});
		});

		SortedList<Reader> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tableReader.comparatorProperty());

		tableReader.setItems(sortedData);
	}

    @FXML
    void showReaderHistory(ActionEvent event) {
		if(!tableReader.getSelectionModel().isEmpty()) {

			coverPane.setPrefHeight(coverPane.getScene().getHeight());

			Parent parent = null;

			try {
				FXMLLoader loader = new FXMLLoader();
				parent = loader.load(getClass().getResource("/fxml/ReaderHistory.fxml").openStream());
				ReaderHistoryController readerHistoryController = (ReaderHistoryController) loader.getController();
				readerHistoryController
						.setValumeForTable(tableReader.getSelectionModel().getSelectedItem());

				Scene scene = new Scene(parent);

				scene.getStylesheets().add(getClass().getResource("/css/readerHistory.css").toExternalForm());

				Stage window = new Stage();

				window.setScene(scene);
				window.initModality(Modality.APPLICATION_MODAL);
				window.initStyle(StageStyle.UNDECORATED);
				window.setTitle("History");
				window.setResizable(false);
				window.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}

			coverPane.setPrefHeight(0);

			tableReader.setItems(readearDAO.allReaders());
		}
    }
}