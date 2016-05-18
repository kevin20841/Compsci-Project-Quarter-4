package JavaFXGUI;
import java.util.ArrayList;
import java.util.HashMap;

import backend.Student;
import backend.StudentList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
@SuppressWarnings("restriction")
public class EnterInfoTab extends Tab{
	
	private boolean goingIn;
	private MenuTabPane parent;
	private Tab previous;
	
	public EnterInfoTab(MenuTabPane par, Tab prev, String title,
			HashMap<String, StudentList> data, boolean gIn, Student student){
		
		setText(title);
		
		previous = prev;
		parent = par;
	

		OptionSelect test = new OptionSelect(700, 500, this);
		test.addPage("TestPage2");
		test.addPage("TestPage3");
		for (int i = 0; i < 5; i ++){
			test.addButton(0, "TEXT" + i, "" + i);
			
		}
		for (int i = 4; i < 10; i ++){
			test.addButton(1, "TEXT" + i, "" + i);
			
		}
		test.addButton(1, "TEXT", "hi");
		test.addButton(1, "TEXT1", "hi1");
		test.setAlignment(Pos.CENTER);
		BorderPane content = new BorderPane();
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> goBack());
		backButton.setPrefSize(150, 20);
		
		HBox navHBox = new HBox();
		navHBox.setPadding(new Insets(15, 12, 15, 12));
		navHBox.setSpacing(10);
		
		navHBox.getChildren().add(backButton);
		
		
		content.setCenter(test);
		content.setBottom(navHBox);
		setContent(content);
	}
	
	public void goBack(){
		previous.setDisable(false);
		parent.getSelectionModel().select(previous);
		die();
	}
	
	public void die(){
		parent.getTabs().remove(this);
	}
	public ArrayList<String> readOptionList(){
		
		return null;
	}
}
