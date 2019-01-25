package controller;

import java.awt.MouseInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import startApp.Main;

public class MainViewController implements Initializable {

    @FXML
    private AnchorPane coverPane;
    
    @FXML
    private AnchorPane mainAnchPane;

	@FXML
    private AnchorPane menuBox;

    @FXML
    private Button statisticButt;

    @FXML
    private Button lendBookButt;

    @FXML
    private Button returnBookButt;

    @FXML
    private Button extensionButt;

    @FXML
    private Button readersButt;

    @FXML
    private Button catalogueButt;

    @FXML
    private AnchorPane head;

    @FXML
    private Button closeButton;

    @FXML
    private JFXButton signOutButt;

    @FXML
    private Button maxiMizeButton;

    @FXML
    private ImageView changeSizeWind;

    @FXML
    private Button minButton;

    @FXML
    private AnchorPane selectViewWindow;

	private double xOffSet;
	private double yOffSet;
	private boolean maximize = false;
	private AnchorPane newLoadedPane;
	
	private String ButtSelectedColor = "-fx-background-color: #028090";
	private String ButtDefaultColor = "-fx-background-color: #00A896";
	private String nameButton= "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new Thread(()->{
			moveWindow();
		}).start();
		
		lendBookButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			lendBookButt.setStyle(ButtSelectedColor);
			nameButton = "lendBookButt";
		});
		
		returnBookButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			returnBookButt.setStyle(ButtSelectedColor);
			nameButton = "returnBookButt";
		});
		
		extensionButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			extensionButt.setStyle(ButtSelectedColor);
			nameButton = "extensionButt";
		});
		
		readersButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			readersButt.setStyle(ButtSelectedColor);
			nameButton = "readersButt";
		});
		
		catalogueButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			catalogueButt.setStyle(ButtSelectedColor);
			nameButton = "catalogueButt";
		});
		
		statisticButt.setOnMouseClicked(e->{
			setButtDefaultColor(nameButton);
			statisticButt.setStyle(ButtSelectedColor);
			nameButton = "statisticButt";
		});
		
		statisticButt.getOnMouseClicked().handle(null);
		statistic(null);
		
		mainAnchPane.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ESCAPE && maximize == true)
				maxiMizeButton.getOnAction().handle(null);
		});
	}

    @FXML
    void statistic(ActionEvent event) {
    	selectViewInAnchorP("/fxml/Statistic.fxml", "/css/statistic.css");
    }
	
	@FXML
	void lendBook(ActionEvent event) {
		selectViewInAnchorP("/fxml/LendBook.fxml", "/css/lendBook.css");
	}

    @FXML
    void returnBook(ActionEvent event) {
    	selectViewInAnchorP("/fxml/ReturnBook.fxml", "/css/returnBook.css");
    }
	
    @FXML
    void extension(ActionEvent event) {
    	selectViewInAnchorP("/fxml/Extension.fxml", "/css/extension.css");
    }
    
    @FXML
    void readers(ActionEvent event) {
    	selectViewInAnchorP("/fxml/Reader.fxml", "/css/reader.css");
    }
    
    @FXML
    void catalogue(ActionEvent event) {
    	selectViewInAnchorP("/fxml/Catalogue.fxml", "/css/catalogue.css");
    }
    
	@FXML
	void changeSize(ActionEvent event) {
		if (maximize == false) {
			Main.primaryStage.setMaximized(true);
			maximize = true;
		} else {
			Main.primaryStage.setMaximized(false);
			maximize = false;
		}

		changeImageButton(maximize);
	}

	@FXML
	void signOut(ActionEvent event) {
		
		Main.primaryStage.setMaximized(false);
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

			Main.primaryStage.getScene().getStylesheets().clear();

			Main.primaryStage.setScene(scene);

			Main.primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void closeButtAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void minButtAction(ActionEvent event) {
		Main.primaryStage.setIconified(true);
	}

	private void moveWindow() {

		head.setOnMousePressed(e -> {

			xOffSet = MouseInfo.getPointerInfo().getLocation().getX() / 2 - e.getScreenX();
			yOffSet = MouseInfo.getPointerInfo().getLocation().getY() - e.getScreenY();

			head.setCursor(javafx.scene.Cursor.CLOSED_HAND);
		});

		head.setOnMouseDragged(e -> {
			if (maximize == true) {
				Main.primaryStage.setMaximized(false);
				maximize = false;
			}

			changeImageButton(maximize);

			Main.primaryStage.setX(e.getScreenX() + xOffSet);
			Main.primaryStage.setY(e.getScreenY() + yOffSet);

		});

		head.setOnMouseReleased(e -> {

			head.setCursor(javafx.scene.Cursor.DEFAULT);
		});
	}
	
	public void selectViewInAnchorP(String urlFxml, String urlCss) {
		try {		
			newLoadedPane = FXMLLoader.load(getClass().getResource(urlFxml));
			newLoadedPane.getStylesheets().addAll(getClass().getResource(urlCss).toExternalForm());
			newLoadedPane.prefHeightProperty().bind(selectViewWindow.heightProperty());
			newLoadedPane.prefWidthProperty().bind(selectViewWindow.widthProperty());
			selectViewWindow.getChildren().setAll(newLoadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeImageButton(boolean maximizeImage) {
		if (maximizeImage == true) {
			// load the image
			Image image = new Image("/image/miniMize.png");
			// simple displays ImageView the image as is
			changeSizeWind.setImage(image);
			changeSizeWind.setFitWidth(13);
			changeSizeWind.setFitHeight(15);
		} else {
			// load the image
			Image image = new Image("/image/Full_Screen.png");
			// simple displays ImageView the image as is
			changeSizeWind.setImage(image);
			changeSizeWind.setFitWidth(13);
			changeSizeWind.setFitHeight(15);
		}
	}
	
	void setButtDefaultColor(String name) {
		switch (name) {
			case "lendBookButt":
				lendBookButt.setStyle(ButtDefaultColor);
				break;		
			case "returnBookButt":
				returnBookButt.setStyle(ButtDefaultColor);
				break;
			case "extensionButt":
				extensionButt.setStyle(ButtDefaultColor);
				break;
			case "readersButt":
				readersButt.setStyle(ButtDefaultColor);
				break;
			case "catalogueButt":
				catalogueButt.setStyle(ButtDefaultColor);
				break;
			case "statisticButt":
				statisticButt.setStyle(ButtDefaultColor);
				break;
		default:
			break;
		}
	}
}
