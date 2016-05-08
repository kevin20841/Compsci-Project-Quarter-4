package JavaFXGUI;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.layout.*;



@SuppressWarnings("restriction")
public class AnimatedAlertBox extends HBox{
	private SequentialTransition  seqT; 
	private Label studentIDLabel;
	public AnimatedAlertBox(String defText){
		setOpacity(0);
		getStyleClass().add("alertError");
		studentIDLabel = new Label(defText);
		studentIDLabel.getStyleClass().add("alertText");
		getChildren().add(studentIDLabel);
		setAlignment(Pos.CENTER);
		setPrefHeight(40);
		
		
		FadeTransition ftIn = new FadeTransition(Duration.millis(500), this);
		ftIn.setFromValue(0);
		ftIn.setToValue(1.0);
		ftIn.setCycleCount(1);
		PauseTransition pt = new PauseTransition(Duration.millis(4000));
		FadeTransition ftOut = new FadeTransition(Duration.millis(500), this);
		ftOut.setFromValue(1.0);
		ftOut.setToValue(0);
		ftOut.setCycleCount(1);
		
		seqT = new SequentialTransition (ftIn, pt, ftOut);
	}
	
	public void play(String err){
		studentIDLabel.setText(err);
		seqT.play();
	}
	public void play(){
		seqT.play();
	}
	
}
