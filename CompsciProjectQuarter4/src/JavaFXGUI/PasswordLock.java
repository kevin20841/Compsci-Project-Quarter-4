package JavaFXGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

import javafx.scene.effect.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import backend.StudentList;

@SuppressWarnings("restriction")
public class PasswordLock extends StackPane{
	// Does not have to 100% secure, just so student's can't access this.
	private String passwordHash;
	private String salt;
	private StartApplication parent;
	private SettingHBox optionBorderContentHBox;
	private PasswordField passwordField;
	
	
	public PasswordLock(StartApplication p, double width, HashMap<String, StudentList>d){
		SecureRandom random = new SecureRandom();
		salt = new BigInteger(130, random).toString(32);
		Scanner configScanner = null;
		File configFile = new File("src/data/settings.config");
		try {
			configScanner = new Scanner(configFile);
			passwordHash = configScanner.nextLine();
			salt = configScanner.nextLine();

			configScanner.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		
		parent = p;
		VBox passwordPanel = new VBox();
		passwordPanel.getStyleClass().add("passwordLock");
		
		Label optionLabel = new Label("Please enter the password: ");
		
		passwordPanel.setMaxHeight(150);
		passwordPanel.setMaxWidth(400);
		
		
		passwordField = new PasswordField();
		passwordField.getStyleClass().add("passwordTextField");
		

		HBox buttonHBox = new HBox();
		
		buttonHBox.setPadding(new Insets(15, 12, 15, 12));
		buttonHBox.setSpacing(10);
		buttonHBox.getStyleClass().add("center");
		
		Button cancelButton = new Button("Cancel");
		cancelButton.getStyleClass().add("closeButton");
		cancelButton.setOnAction(e -> cancel());
		
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(e-> submit());
		submitButton.setDefaultButton(true);
		
		
		buttonHBox.getChildren().addAll(cancelButton, submitButton);
		passwordPanel.getChildren().addAll(optionLabel, passwordField, buttonHBox);
		
		optionBorderContentHBox = new SettingHBox(p, width, d, passwordHash, salt);
        BoxBlur bb = new BoxBlur();
        bb.setIterations(3);
        optionBorderContentHBox.setEffect(bb);
        optionBorderContentHBox.setDisable(true);
		getChildren().add(optionBorderContentHBox);
		
		getChildren().add(passwordPanel);
	}
	
	private void cancel(){
		parent.hideOptionsPage();
	}
	
	private void submit(){
		String p = passwordField.getText();
		p = SettingConfig.generateHash(p, salt);
		if (passwordHash.equals(p)){
			optionBorderContentHBox.setEffect(null);
			getChildren().remove(1);
			optionBorderContentHBox.setDisable(false);
		}
		else{
			passwordField.getStyleClass().add("passwordTextField-error");
			ShakeTransition anim = new ShakeTransition(passwordField);
			anim.play();
		}
		
	}
	
	
}
