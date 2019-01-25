package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.BookDAO;
import dataValidation.DataValidation;
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
        boolean isbn = DataValidation.textNumber(isbnField.getText(), 1, 20);
        boolean title = DataValidation.textAlphabetWithPolishMarks(titleField.getText(), 1, 60);
        boolean autor = DataValidation.textAlphabetWithPolishMarks(autorField.getText(), 1, 40);
        boolean publicationYear = DataValidation.textNumber(pYearField.getText(), 4, 4);
        boolean typeBook = DataValidation.textAlphabetWithPolishMarks(typeField.getText(), 1, 30);

        if(isbn && title && autor && publicationYear &&typeBook) {
            book.setIsbn(Long.valueOf(isbnField.getText()));
            book.setTitle(titleField.getText());
            book.setAutor(autorField.getText());
            book.setPublication_year(Integer.valueOf(pYearField.getText()));
            book.setType(typeField.getText());

            if (bookDAO.createBook(book)) {
                mainStackPCreate.getScene().getWindow().hide();
            }
        }else{
            if(!isbn)
                isbnField.setStyle("-fx-background-color: #ff0000;");
            if(!title)
                titleField.setStyle("-fx-background-color: #ff0000;");
            if(!autor)
                autorField.setStyle("-fx-background-color: #ff0000;");
            if(!publicationYear)
                pYearField.setStyle("-fx-background-color: #ff0000;");
            if(!typeBook)
                typeField.setStyle("-fx-background-color: #ff0000;");
        }
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
