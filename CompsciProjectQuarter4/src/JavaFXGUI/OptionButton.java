package JavaFXGUI;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
@SuppressWarnings("restriction")
public class OptionButton extends Button{
	String valueText;
	public OptionButton(String title, String value){
		setText(title);
		valueText = value;
	}
	public OptionButton(String title, String value, String style){
		setText(title);
		valueText = value;
		setPosStyle(style);
	}
	public String getValue(){
		return valueText;
	}
	
	public void setPosStyle(String style){
		getStyleClass().clear();
		if (style == "top"){
			getStyleClass().add("optionButton-top");
		}
		else if (style == "mid"){ 
			getStyleClass().add("optionButton-mid");
		}
		
		else if (style == "bottom"){
			getStyleClass().add("optionButton-bottom");
		}
	}

}
