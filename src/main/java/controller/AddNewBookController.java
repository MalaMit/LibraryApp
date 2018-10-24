package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BookDAO;
import entities.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AddNewBookController implements Initializable {

	@FXML
    private StackPane mainStackPCreate;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField autorField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField pYearField;
    
    private BookDAO bookDAO = new BookDAO();
    private Book book = new Book();

    @FXML
    void closeWindow(ActionEvent event) {
    	mainStackPCreate.getScene().getWindow().hide();
    }

    @FXML
    void createButton(ActionEvent event) { 	
    	
    	book.setIsbn(Long.valueOf(isbnField.getText()));
    	book.setTitle(titleField.getText());
    	book.setAutor(autorField.getText());
    	book.setPublication_year(Integer.valueOf(pYearField.getText()));
    	book.setType(typeField.getText());
    	
    	if(bookDAO.createBook(book) == true) {
    		mainStackPCreate.getScene().getWindow().hide();
    	}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
