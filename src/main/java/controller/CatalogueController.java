package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dao.BookDAO;
import dataValidation.DataValidation;
import entities.Book;
import javafx.beans.property.SimpleObjectProperty;
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

public class CatalogueController implements Initializable {

    @FXML
    private AnchorPane coverPane;
    
	@FXML
	private JFXTextField dataSearch;

	@FXML
	private JFXComboBox<String> typeSearch;

	@FXML
	private JFXButton searchButt;

	@FXML
	private JFXButton addNewBookButt;

	@FXML
	private JFXButton editBookButt;

	@FXML
	private JFXButton deleteBookButt;
	
    @FXML
    private JFXButton localizationButt;

	@FXML
	private TableView<Book> bookTable;

	@FXML
	private TableColumn<Book, Long> isbnColumn;

	@FXML
	private TableColumn<Book, String> tittleColumn;

	@FXML
	private TableColumn<Book, String> autorColumn;

	@FXML
	private TableColumn<Book, Integer> publicationYearColumn;

	@FXML
	private TableColumn<Book, String> typeColumn;
	
	private BookDAO bookDAO = new BookDAO();
	private ObservableList<Book> books = bookDAO.allBooks();
	private ObservableList<String> typeToSearchBook = FXCollections.observableArrayList("ISBN", "Autor", "Tittle", "Type");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isbnColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIsbn()));
		tittleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTitle()));
		autorColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAutor()));
		publicationYearColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPublication_year()));
		typeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));
		
		bookTable.setItems(books);
		
		typeSearch.setItems(typeToSearchBook);
	
		bookTable.heightProperty().addListener(e->{
			isbnColumn.setPrefWidth((bookTable.getWidth()/5));
			tittleColumn.setPrefWidth((bookTable.getWidth()/5));
			autorColumn.setPrefWidth((bookTable.getWidth()/5));
			publicationYearColumn.setPrefWidth((bookTable.getWidth()/5));
			typeColumn.setPrefWidth((bookTable.getWidth()/5));
		});
	}

	@FXML
	void addNewBook(ActionEvent event) {
		coverPane.setPrefHeight(coverPane.getScene().getHeight());

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/AddNewBook.fxml"));

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/addNewBook.css").toExternalForm());

			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.initStyle(StageStyle.UNDECORATED);
			window.setTitle("Add Book");
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

		coverPane.setPrefHeight(0);

		bookTable.setItems(bookDAO.allBooks());
	}

	@FXML
	void deleteBook(ActionEvent event) {
		if (!bookTable.getSelectionModel().isEmpty()) {
			bookDAO.deleteBook(bookTable.getSelectionModel().getSelectedItem());
			bookTable.setItems(bookDAO.allBooks());
		}
	}

	@FXML
	void editBook(ActionEvent event) {
		if(!bookTable.getSelectionModel().isEmpty()) {
			
			coverPane.setPrefHeight(coverPane.getScene().getHeight());
			
			Parent parent = null;

			try {
				FXMLLoader loader = new FXMLLoader();
				parent = loader.load(getClass().getResource("/fxml/EditDataBook.fxml").openStream());
				EditDataBookController editDataBookController = (EditDataBookController) loader.getController();
				editDataBookController
						.setField(bookTable.getSelectionModel().getSelectedItem());

				Scene scene = new Scene(parent);

				scene.getStylesheets().add(getClass().getResource("/css/addNewBook.css").toExternalForm());

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
			
			bookTable.setItems(bookDAO.allBooks());
		}
	}

	@FXML
	void search(ActionEvent event) {
		if(!typeSearch.getSelectionModel().isEmpty() && !dataSearch.getText().isEmpty()){
			switch (typeSearch.getSelectionModel().getSelectedItem()) {
			case "ISBN":
				if(DataValidation.textNumber(dataSearch.getText(), 1, 20))
					books = bookDAO.searchBook(dataSearch.getText(), "0");
				break;
			case "Autor":
				if(DataValidation.textAlphabetWithPolishMarks(dataSearch.getText(), 1, 40))
					books = bookDAO.searchBook(dataSearch.getText(), "1");
				break;
			case "Tittle":
				if(DataValidation.textAlphabetWithPolishMarks(dataSearch.getText(), 1, 60))
					books = bookDAO.searchBook(dataSearch.getText(), "2");
				break;
			case "Type":
				if(DataValidation.textAlphabetWithPolishMarks(dataSearch.getText(), 1, 30))
					books = bookDAO.searchBook(dataSearch.getText(), "3");
				break;
			default:
				books.clear();
				break;
			}
			bookTable.setItems(books);
		}
	}
	
    @FXML
    void bookLocalization(ActionEvent event) {
    	System.out.println(bookTable.getSelectionModel().getSelectedItem().getAutor());
    }

}
