package JavaFXGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import backend.Student;
import backend.StudentList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class StartTab extends Tab {
	
	private MenuTabPane parent;
	private HashMap<String, StudentList> data;
	private AnimatedAlertBox alert;
	
	public StartTab(MenuTabPane p, String title, HashMap<String, StudentList> data){
		
		this.data = data;
		parent = p;
		setText(title);
		BorderPane content = new BorderPane();

		HBox buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		buttonHBox.setPadding(new Insets(15, 12, 15, 12));
		buttonHBox.setSpacing(10);
		
		HBox viewButtonHBox = new HBox();
		viewButtonHBox.setPadding(new Insets(15, 12, 15, 12));
		viewButtonHBox.setSpacing(10);
		
		
		Button buttonSignIn = new Button("Sign In");
		buttonSignIn.setPrefSize(200, 40);
		buttonSignIn.setOnAction(e -> moveOn(true));

		
		Button buttonSignOut = new Button("Sign Out");
		buttonSignOut.setPrefSize(200, 40);
		buttonSignOut.setOnAction(e -> moveOn(false));
		
		Button viewButton = new Button("View Records");
		viewButton.setPrefSize(150, 20);
		viewButton.setOnAction(e -> parent.parent.showOptionsPage());
		
		buttonHBox.getChildren().addAll(buttonSignIn, buttonSignOut);
		viewButtonHBox.getChildren().add(viewButton);
		
		alert = new AnimatedAlertBox("Submission Sucessful!", false);
		
		content.setTop(alert);
		
		content.setCenter(buttonHBox);
		content.setBottom(viewButtonHBox);
		setContent(content);
		
	}
	
	private void moveOn(boolean signIn){
		
		EnterStudentTab tab2 = new EnterStudentTab(parent, this, "Enter ID",  data, signIn);
		setDisable(true);
		parent.getTabs().add(tab2);
		parent.getSelectionModel().select(tab2);
		
	}

	
	
	public void displaySucess(){
		alert.play();
	}
	
	
	
	

}
