package dataValidation;

import java.io.IOException;

import controller.ConfirmationWindowController;
import controller.ErrorMassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertMaker {

	private static boolean decision = false;
	
	private static void setDecisionTrue() {
		decision = true;
	}
	
	private static void setDecisionFalse() {
		decision = false;
	}
	
	public void showSimpleAlert(String content, String cssPath) {
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/ErrorMessage.fxml").openStream());
			ErrorMassController errorMassController = (ErrorMassController) loader.getController();
			
			errorMassController.setTextError(content);
			
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/"+ cssPath +"").toExternalForm());
			
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
	
	public boolean confirmationAlert(String content) {
		boolean localFunDecision = false;

		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			parent = loader.load(getClass().getResource("/fxml/ConfirmationWindow.fxml").openStream());
			ConfirmationWindowController confWindController = (ConfirmationWindowController) loader.getController();

			confWindController.setText(content);

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/css/confirmationWindow.css").toExternalForm());

			Stage window = new Stage();

			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.initStyle(StageStyle.UNDECORATED);
			window.setResizable(false);
			window.setOnHiding(e -> {
				if (confWindController.choicYes)
					setDecisionTrue();
			});
			window.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

		localFunDecision = (decision) ? true : false;
		setDecisionFalse();
		
		return localFunDecision;
	}

}
