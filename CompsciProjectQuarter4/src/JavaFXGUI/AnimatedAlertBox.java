package JavaFXGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.shape.*;



@SuppressWarnings("restriction")
public class AnimatedAlertBox extends HBox{
	public AnimatedAlertBox(String message){
		getStyleClass().add("alertError");
		Label studentIDLabel = new Label(message);
		studentIDLabel.getStyleClass().add("alertText");
		getChildren().add(studentIDLabel);
		setAlignment(Pos.CENTER);
		setPrefHeight(40);
	}
	
}
