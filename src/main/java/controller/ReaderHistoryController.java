package controller;

import com.jfoenix.controls.JFXButton;
import dao.ReaderDAO;
import entities.Reader;
import entities.ReaderHistory;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ReaderHistoryController implements Initializable {
    @FXML
    private AnchorPane readerHistoryPaneID;

    @FXML
    private TableView<ReaderHistory> readerHistoryTable;

    @FXML
    private TableColumn<ReaderHistory, String> bookNameField;

    @FXML
    private TableColumn<ReaderHistory, Date> lendDAteField;

    @FXML
    private TableColumn<ReaderHistory, Date> returnedDateField;

    @FXML
    private JFXButton closeButtonID;

    private ObservableList<ReaderHistory> readerHistoryObservableList = FXCollections.observableArrayList();
    private Timer timer = new Timer();

    @FXML
    void close(ActionEvent event) {
        readerHistoryPaneID.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookNameField.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getCatalogue().getTitle()));
        lendDAteField.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getLend_Date()));
        returnedDateField.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getReturn_Date()));

        readerHistoryTable.widthProperty().addListener((e)->{
            bookNameField.setPrefWidth(readerHistoryTable.getWidth()/3);
            lendDAteField.setPrefWidth(readerHistoryTable.getWidth()/3);
            returnedDateField.setPrefWidth((readerHistoryTable.getWidth()/3)-1);
        });

        timer.schedule(timerTask, 10);
    }

    public void setValumeForTable(Reader reader){
        ReaderDAO readerDAO = new ReaderDAO();
        readerHistoryObservableList = readerDAO.showAllReaderHistory(reader);
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(()-> {
                readerHistoryTable.setItems(readerHistoryObservableList);
            });
        }
    };
}
