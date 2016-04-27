package JavaFXGUI;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class StartTab extends Tab {
	private MenuTabPane parent;
	public StartTab(MenuTabPane p, String title){
		parent = p;
		setText(title);
		BorderPane content = new BorderPane();

		HBox buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		buttonHBox.setPadding(new Insets(15, 12, 15, 12));
		buttonHBox.setSpacing(10);
		
		HBox viewButtonHBox = new HBox();
		
		Button buttonSignIn = new Button("Sign In");
		buttonSignIn.setPrefSize(200, 40);
		buttonSignIn.setOnAction(e -> createEnterIdTab(true));
		
		Button buttonSignOut = new Button("Sign Out");
		buttonSignOut.setPrefSize(200, 40);
		buttonSignOut.setOnAction(e -> createEnterIdTab(false));
		
		Button viewButton = new Button("View Records");
		viewButton.setPrefSize(100, 20);
		
		buttonHBox.getChildren().addAll(buttonSignIn, buttonSignOut);
		viewButtonHBox.getChildren().add(viewButton);
		
		content.setCenter(buttonHBox);
		content.setBottom(viewButtonHBox);
		setContent(content);
	}
	
	private void createEnterIdTab(boolean signIn){
		EnterIDTab tab2 = new EnterIDTab(parent, "Enter ID", signIn);
		setDisable(true);
		parent.getTabs().add(tab2);
		parent.getSelectionModel().select(tab2);
		
	}
	public void die(){
		parent.getTabs().remove(this);
	}
}
