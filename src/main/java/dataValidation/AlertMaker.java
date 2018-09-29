package dataValidation;

import java.io.IOException;

import controller.ErrorMassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertMaker {

	public void showSimpleAlert(String content) {
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/ErrorMessage.fxml").openStream());
			ErrorMassController errorMassController = (ErrorMassController) loader.getController();
			
			errorMassController.setTextError(content);

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/errorMessage.css").toExternalForm());
			
			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.initStyle(StageStyle.UNDECORATED);
			window.setResizable(false);
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
