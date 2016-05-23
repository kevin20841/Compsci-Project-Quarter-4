package JavaFXGUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.event.*;
import javafx.geometry.*;
@SuppressWarnings("restriction")
public class OptionHBox extends HBox implements Playable{
	private TextField textFieldOther;
	private OptionSelect parent;
	private AnimatedGif gfA1;
	private AnimatedGif gfA2;
	public OptionHBox(int width, OptionSelect p) {
		parent  = p;
		
		Label textFieldOtherLabel = new Label("Other: ");
		textFieldOther = new TextField();
		textFieldOther.setPrefWidth(width-300);
		
		Button textFieldOtherButton = new Button("Set");
		textFieldOtherButton.setOnAction(new ButtonHandler(textFieldOther.getText(), this, parent, true ));
		textFieldOtherButton.getStyleClass().add("textFieldOtherButton");
		

		gfA1 = new AnimatedGif("src/img/gifCheckmark.gif", 500);
		gfA1.setCycleCount(1);
		
		
		gfA2 = new AnimatedGif("src/img/gifCheckmarkReverse.gif", 500);
		gfA2.setCycleCount(1);
		
		getChildren().addAll(gfA1.getView(), textFieldOtherLabel, textFieldOther, textFieldOtherButton);
		setSpacing(10);
		setAlignment(Pos.CENTER_LEFT);
	}
	@Override
	public void play() {
		getChildren().remove(0);
		getChildren().add(0, gfA1.getView());
		gfA1.play();
	}

	@Override
	public void reverse() {
		getChildren().remove(0);
		getChildren().add(0, gfA2.getView());
		gfA2.play();
	}
	
	public void clear(){
		textFieldOther.clear();
	}
	
	public String getText(){
		return textFieldOther.getText();
	}
	
}
