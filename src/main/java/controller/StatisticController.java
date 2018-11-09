package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import dao.BookDAO;
import dao.CatalogueDAO;
import dao.LendBookDAO;
import dao.ReaderDAO;
import dao.ReturnAndExtensionDAO;
import entities.Catalogue;
import javafx.application.Platform;
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
    private Label lendLabel;

    @FXML
    private HBox holdoverBox;

    @FXML
    private Label timeOutLabel;

    private BookDAO bookDao = new BookDAO();
    private CatalogueDAO catalogueDAO = new CatalogueDAO();
    private ReaderDAO readerDAO = new ReaderDAO();
    private LendBookDAO lendBookDAO = new LendBookDAO();
    private ReturnAndExtensionDAO retAndExtenDAO = new ReturnAndExtensionDAO();
    
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			Platform.runLater(()->{
				booksLabel.setText(String.valueOf(bookDao.countBook()));
				copiesLabel.setText(String.valueOf(catalogueDAO.countCopyBook()));
				accountLabel.setText(String.valueOf(readerDAO.countReader()));
				lendLabel.setText(String.valueOf(lendBookDAO.countLendBook()));
				timeOutLabel.setText(String.valueOf(retAndExtenDAO.countBookTimeOut()));
			});
			
		}
	};
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timer.schedule(timerTask, 2);
		
	}

}
