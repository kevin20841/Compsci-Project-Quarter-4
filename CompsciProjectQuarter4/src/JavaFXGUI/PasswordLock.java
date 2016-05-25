package JavaFXGUI;
import java.util.HashMap;
import javafx.scene.effect.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import backend.StudentList;

@SuppressWarnings("restriction")
public class PasswordLock extends StackPane{
	// Does not have to 100% secure, just so student's can't access this.
	private String password = "asdfawef";
	private boolean locked;
	private StartApplication parent;
	private SettingHBox optionBorderContentHBox;
	private PasswordField passwordField;
	
	
	public PasswordLock(StartApplication p, double width, HashMap<String, StudentList>d){
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
		
		optionBorderContentHBox = new SettingHBox(p, width, d);
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
		if (password.equals(p)){
			optionBorderContentHBox.setEffect(null);
			getChildren().remove(1);
			optionBorderContentHBox.setDisable(false);
		}
		else{
			System.out.println("FALSE");
			System.out.println(p);
		}
		
	}
	
	
}
