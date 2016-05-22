package JavaFXGUI;

import javafx.scene.control.*;

@SuppressWarnings("restriction")
public class OptionButton extends Button implements Playable{
	private String valueText;
	private AnimatedGif gfA1;
	private AnimatedGif gfA2;
	
	public OptionButton(String t, String value){
		setText(t);
		valueText = value;
		init();
	}
	public OptionButton(String t, String value, String style){
		setText(t);
		valueText = value;
		setPosStyle(style);
		init();
	}
	
	private void init(){

		gfA1 = new AnimatedGif("src/img/gifCheckmark.gif", 500);
		gfA1.setCycleCount(1);
		setGraphic(gfA1.getView());
		
		gfA2 = new AnimatedGif("src/img/gifCheckmarkReverse.gif", 500);
		gfA2.setCycleCount(1);

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
	

	public void play(){
		setGraphic(gfA1.getView());
		gfA1.play();
	}
	public void reverse(){
		setGraphic(gfA2.getView());
		gfA2.play();
	}
	
  
	private boolean equals(OptionButton other){
		return (other.valueText == valueText);
	}
    


}
