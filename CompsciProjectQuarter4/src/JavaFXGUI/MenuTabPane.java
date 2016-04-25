package JavaFXGUI;

import javafx.scene.control.*;

@SuppressWarnings("restriction")
public class MenuTabPane extends TabPane{

	public MenuTabPane(){
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		StartTab init = new StartTab("Start");
		getTabs().add(init);
	}
	
}
