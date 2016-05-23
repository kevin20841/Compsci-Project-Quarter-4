package JavaFXGUI;

import java.util.HashMap;

import backend.StudentList;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.animation.*;

@SuppressWarnings("restriction")
public class MenuTabPane extends TabPane{

public StartApplication parent;
	public MenuTabPane(StartApplication p, HashMap<String, StudentList> data){
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		StartTab init = new StartTab(this, "Start", data);
		getTabs().addAll(init);
		parent = p;
	}
	
	
	

}
