package JavaFXGUI;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.*;
@SuppressWarnings("restriction")
public class StartTab extends Tab {
	public StartTab(String title){
		setText(title);
		BorderPane content = new BorderPane();


		HBox buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		buttonHBox.setPadding(new Insets(15, 12, 15, 12));
		buttonHBox.setSpacing(10);
		
		
		HBox viewButtonHBox = new HBox();
		Button buttonSignIn = new Button("SignIn");
		buttonSignIn.setPrefSize(200, 40);
		
		Button buttonSignOut = new Button("SignOut");
		buttonSignOut.setPrefSize(200, 40);

		
		Button viewButton = new Button("View Records");
		viewButton.setPrefSize(100, 20);
		
		buttonHBox.getChildren().addAll(buttonSignIn, buttonSignOut);
		viewButtonHBox.getChildren().add(viewButton);
		
		
		content.setCenter(buttonHBox);
		content.setBottom(viewButtonHBox);
		setContent(content);
	}
}
