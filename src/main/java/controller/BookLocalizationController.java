package controller;

import dao.CatalogueDAO;
import entities.Book;
import entities.Catalogue;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class BookLocalizationController implements Initializable {

    @FXML
    private TableView<Catalogue> localizationBookTable;

    @FXML
    private TableColumn<Catalogue, Long> idFieldTable;

    @FXML
    private TableColumn<Catalogue, String> localizationBookFieldTable;

    @FXML
    private TableColumn<Catalogue, Long> isbnFieldTable;

    @FXML
    private JFXButton closeButtonID;

    @FXML
    private JFXButton addNewLocalizationID;

    @FXML
    private JFXButton editLocalizationID;

    @FXML
    private JFXButton deleteLocalizationID;

    private CatalogueDAO catalogueDAO = new CatalogueDAO();
    private ObservableList<Catalogue> books = FXCollections.observableArrayList();
    private Timer timer = new Timer();
    private long currentBookIsbn = 0;

    @FXML
    void addNewLocalization(ActionEvent event) {
        Parent parent = null;

        try {
            FXMLLoader loader = new FXMLLoader();
            parent = loader.load(getClass().getResource("/fxml/AddNewBookLocalization.fxml").openStream());
            AddNewBookLocalizationController addNewBookLocalizationController = (AddNewBookLocalizationController) loader.getController();
            addNewBookLocalizationController
                    .setISBN(currentBookIsbn);

            Scene scene = new Scene(parent);

            scene.getStylesheets().add(getClass().getResource("/css/addNewBookLocalization.css").toExternalForm());

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
        books = catalogueDAO.findBooksByISBN(currentBookIsbn);
        localizationBookTable.setItems(books);
    }

    @FXML
    void deleteLocalizationID(ActionEvent event) {
        if(localizationBookTable.getSelectionModel().getSelectedItem() != null &&
                !localizationBookTable.getSelectionModel().getSelectedItem().getLocalization().equalsIgnoreCase("Borrowed")){
            catalogueDAO.deleteBookLocalization(localizationBookTable.getSelectionModel().getSelectedItem());
            books = catalogueDAO.findBooksByISBN(currentBookIsbn);
            localizationBookTable.setItems(books);
        }
    }

    @FXML
    void editLocalizationID(ActionEvent event) {
        if(localizationBookTable.getSelectionModel().getSelectedItem() != null){
            Parent parent = null;

            try {
                FXMLLoader loader = new FXMLLoader();
                parent = loader.load(getClass().getResource("/fxml/ChangeBookLocalization.fxml").openStream());
                ChangeBookLocalizationController changeBookLocalizationController = (ChangeBookLocalizationController) loader.getController();
                changeBookLocalizationController
                        .setViewCurrentLocalization(localizationBookTable.getSelectionModel().getSelectedItem().getLocalization(),localizationBookTable.getSelectionModel().getSelectedItem().getBook_ID());

                Scene scene = new Scene(parent);

                scene.getStylesheets().add(getClass().getResource("/css/addNewBookLocalization.css").toExternalForm());

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
            books = catalogueDAO.findBooksByISBN(currentBookIsbn);
            localizationBookTable.setItems(books);
        }
    }

    @FXML
    void close(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idFieldTable.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getBook_ID()));
        isbnFieldTable.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getBook().getIsbn()));
        localizationBookFieldTable.setCellValueFactory(cellDate -> new SimpleObjectProperty<>(cellDate.getValue().getLocalization()));

        localizationBookTable.widthProperty().addListener((e)->{
            idFieldTable.setPrefWidth(localizationBookTable.getWidth()/3);
            isbnFieldTable.setPrefWidth(localizationBookTable.getWidth()/3);
            localizationBookFieldTable.setPrefWidth(localizationBookTable.getWidth()/3);
        });

        timer.schedule(timerTask, 100);
    }

    void searchBookByIsbn(Book book){
        currentBookIsbn = book.getIsbn();
        books = catalogueDAO.findBooksByISBN(book.getIsbn());
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(()-> {
                localizationBookTable.setItems(books);
            });
        }
    };
}
