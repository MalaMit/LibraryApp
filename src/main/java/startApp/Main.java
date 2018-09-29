package startApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.HibernateUtil;

public class Main extends Application {
	public static Stage primaryStage; // **Declare static Stage**

	private void setPrimaryStage(Stage stage) {
		Main.primaryStage = stage;
	}

	@Override
	public void start(Stage stage) {
		
		HibernateUtil.openSessionWithTransaction();
		HibernateUtil.closeSessionWithTransaction();
		
		setPrimaryStage(stage);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
			
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

