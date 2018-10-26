package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import dao.LendBookDAO;
import dao.ReaderDAO;
import dataValidation.AlertMaker;
import dataValidation.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LendBookController implements Initializable {

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

	private LendBookDAO lendBookDAO = new LendBookDAO();
	private ReaderDAO readerDAO = new ReaderDAO();
	private AlertMaker alertMaker = new AlertMaker();

	@FXML
	void applyButtAction(ActionEvent event) {

		boolean idRead = DataValidation.textNumber(idReader.getText(), 1, 20);
		boolean IdBook = DataValidation.textNumber(idBook.getText(), 1, 20);

		if (!idReader.getText().isEmpty() && !idBook.getText().isEmpty()
				&& !datePicker.getValue().equals(LocalDate.now())) {
			if (idRead && IdBook) {
				if (readerDAO.findReader(Long.valueOf(idReader.getText())) != null
						&& lendBookDAO.checkExistBook(Long.valueOf(idBook.getText()))) {
					lendBookDAO.lendBook(Long.valueOf(idBook.getText()), Long.valueOf(idReader.getText()),
							java.sql.Date.valueOf(datePicker.getValue()));
					alertMaker.showSimpleAlert("The book has been borrowed.", "applyMassage.css");
					idReader.clear();
					idBook.clear();
					datePicker.setValue(LocalDate.now());

				} else {
					alertMaker.showSimpleAlert("Book or Reader is no correct.\nPlease try again!",
							"errorMessage.css");
				}
			}else {
				alertMaker.showSimpleAlert("Book or Reader is no correct.\nPlease try again!",
						"errorMessage.css");
			}
		} else {
			String message = "Please enter field:\n";

			if (idReader.getText().isEmpty()) {
				message = message + "Reader ";
			}
			if (idBook.getText().isEmpty()) {
				message = message + " Book ";
			}
			if (datePicker.getValue().equals(LocalDate.now())) {
				message = message + " Date";
			}

			message = message.replaceAll("  ", ", ");
			alertMaker.showSimpleAlert(message, "errorMessage.css");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		mainAnchorPane.heightProperty().addListener(e -> {
			dataAnchorPane.translateXProperty()
					.bind((mainAnchorPane.widthProperty().subtract(dataAnchorPane.widthProperty())).divide(2));
			dataAnchorPane.translateYProperty()
					.bind((mainAnchorPane.heightProperty().subtract(dataAnchorPane.heightProperty())).divide(4));
			butttonAnchorPane.translateXProperty()
					.bind((mainAnchorPane.widthProperty().subtract(butttonAnchorPane.widthProperty())).divide(2));
		});

		datePicker.setValue(LocalDate.now());
	}

}
