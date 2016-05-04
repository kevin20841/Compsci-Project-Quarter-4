package JavaFXGUI;

import backend.Student;
import backend.StudentList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class StartTab extends Tab {
	private MenuTabPane parent;
	private StudentList studentData =  readStudentDatabase();
	public StartTab(MenuTabPane p, String title){
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
		EnterInfoTab tab2 = new EnterInfoTab(parent, this, "Enter ID",  studentData, signIn);
		setDisable(true);
		parent.getTabs().add(tab2);
		parent.getSelectionModel().select(tab2);
		
	}
	//TODO Implement importing database
	private StudentList readStudentDatabase(){
		StudentList stData = new StudentList();
	
		for (int i = 0; i < 12000; i++){
			stData.add(new Student("test" + i, i % 10 ,i));
		}
		return stData;
	}

}
