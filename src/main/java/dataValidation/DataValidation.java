package dataValidation;

import javafx.scene.control.Label;

public class DataValidation {

    public static boolean textAlphabetWithPolishMarks(String inputTextField, int minimumLength, int requiredLength) {
        boolean isCorrect = true;

        if (!inputTextField.matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ ]{"+ minimumLength +","+ requiredLength +"}")) {
        	isCorrect = false;
        }

        System.out.println(inputTextField.matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ ]{"+ minimumLength +","+ requiredLength +"}") +" "+ "textAlphabetWithPolishMarks" );
        return isCorrect;

    }

	public static boolean textAlphabetAndNumber(String inputTextField, int minimumLength, int requiredLength) {
        boolean isCorrect = true;

        if (!inputTextField.matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ0-9/-]{"+ minimumLength +","+ requiredLength +"}[ ]{0,1}([a-zA-Z0-9/-]{0,6})")) {
        	isCorrect = false;
        }
  
        System.out.println(inputTextField.matches("[a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ0-9/-]{"+ minimumLength +","+ requiredLength +"}[ ]{0,1}([a-zA-Z0-9/-]{0,6})") + " textAlphabetAndNumber");
        return isCorrect;
	}
	
	public static boolean textNumber(String inputTextField, int minimumLength, int requiredLength) {
        boolean isCorrect = true;

        if (!inputTextField.matches("[0-9]{"+ minimumLength +","+ requiredLength +"}")) {
        	isCorrect = false;
        }

        System.out.println(inputTextField.matches("[0-9]{"+ minimumLength +","+ requiredLength +"}")+ " textNumber");
        return isCorrect;
	}
	
	public static boolean textFloat(String inputTextField) {
        boolean isCorrect = true;

        if (!inputTextField.matches("[0-9]{1,4}.[0-9]{2}")) {
        	isCorrect = false;
        }
        
        System.out.println(inputTextField.matches("[0-9]{1,4}.[0-9]{2}") + " textFloat");
        return isCorrect;
	}
	
	public static boolean textPhone(String inputTextField) {
        boolean isCorrect = true;
        
        if (!inputTextField.matches("([+]{0,1}[0-9]{0,2}[\\s]{0,1}[0-9]{3}[-]{0,1}[0-9]{3}[-]{0,1}[0-9]{3})")) {
        	isCorrect = false;

        }

        System.out.println(inputTextField.matches("([+]{0,1})([0-9]{0,2}[\\s]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3}[-]{0,1})([0-9]{0,3})") + " textPhone");
        return isCorrect;
	}
	
	public static boolean textPassword(String inputTextField, Label inputLabel, String validationText) {
        boolean isCorrect = true;
        String validationString = null;

        if (!inputTextField.matches("[a-zA-Z0-9a-zA-Z_!$@#^&]{5,20}")) {
        	isCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.matches("[a-zA-Z0-9a-zA-Z_!$@#^&]{5,20}") + " textPassword");
        return isCorrect;
	}
	
	public static boolean nameAndSurname(String inputTextField) {
        boolean isCorrect = true;

        if (!inputTextField.matches("([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,30})[ ]{1}([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,30})")) {
        	isCorrect = false;
        }

        System.out.println(inputTextField.matches("([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,30})[ ]{1}([a-zA-ZąęćżźńłóśĄĆĘŁŃÓŚŹŻ]{1,30})") + " nameAndSurname");
        return isCorrect;
	}
}
