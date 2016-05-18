package JavaFXGUI;
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
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> goBack());
		backButton.setPrefSize(100, 20);

		OptionSelect test = new OptionSelect(700, 400,"I LIKE PIE");
		test.addPage("TestPage2");
		test.addPage("TestPage3");
		for (int i = 0; i < 5; i ++){
			test.addButton(0, "TEXT" + i, "" + i);
			
		}
		for (int i = 4; i < 12; i ++){
			test.addButton(1, "TEXT" + i, "" + i);
			
		}
		test.addButton(2, "TEXT", "hi");
		test.addButton(2, "TEXT1", "hi1");
		test.setAlignment(Pos.CENTER);
		BorderPane test2 = new BorderPane();
		test2.setCenter(test);
		setContent(test2);
	}
	
	public void goBack(){
		previous.setDisable(false);
		parent.getSelectionModel().select(previous);
		die();
	}
	
	public void die(){
		parent.getTabs().remove(this);
	}
}
