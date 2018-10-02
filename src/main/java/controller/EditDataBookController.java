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

public class EditDataBookController implements Initializable{
	
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
    void updateButton(ActionEvent event) {
    	
    	book.setIsbn(Long.valueOf(isbnField.getText()));
    	book.setTitle(titleField.getText());
    	book.setAutor(autorField.getText());
    	book.setPublication_year(Integer.valueOf(pYearField.getText()));
    	book.setType(typeField.getText());
    	
    	bookDAO.updateBook(book);
    	mainStackPCreate.getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	public void setField(Book book) {
		isbnField.setText(String.valueOf(book.getIsbn()));
		autorField.setText(book.getAutor());
		typeField.setText(book.getType());
		titleField.setText(book.getTitle());
		pYearField.setText(String.valueOf(book.getPublication_year()));
	}
}
