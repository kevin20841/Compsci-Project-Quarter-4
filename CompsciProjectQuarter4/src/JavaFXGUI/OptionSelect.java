package JavaFXGUI;
import java.util.ArrayList;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.event.*;
@SuppressWarnings("restriction")
public class OptionSelect extends VBox{
	private ArrayList<ArrayList<OptionButton>> buttonList;
//TODO SELECTED CHECK MARK
	private ArrayList<String> title = new ArrayList<String>();
	private VBox buttonVBox;
	private ArrayList<String> option;
	private int page;
	private Label titleLabel;
	private int height, width;
	private HBox textFieldOtherHBox;
	private TextField textFieldOther;
	private HBox bottomHBox;
	private Label pageNumberLabel;
	public OptionSelect(int w, int h, EnterInfoTab close){
		buttonList = new ArrayList<ArrayList<OptionButton>>();
		height = h;
		width = w;
		init();
	}


	
	public void init(){
		
		option = new ArrayList<String>();
		setMaxHeight(height);
		setMaxWidth(width);
		
		
		textFieldOtherHBox  = new HBox();
		
		Label textFieldOtherLabel = new Label("Other: ");
		textFieldOther = new TextField();
		textFieldOther.setPrefWidth(width-300);
		
		Button textFieldOtherButton = new Button("Set");
		textFieldOtherButton.setOnAction(new OtherHandler());
		textFieldOtherButton.getStyleClass().add("textFieldOtherButton");
		
		
		textFieldOtherHBox.getChildren().addAll(textFieldOtherLabel, textFieldOther, textFieldOtherButton);
		textFieldOtherHBox.setSpacing(10);
		
		textFieldOtherHBox.getStyleClass().add("optionTextFieldOther");
		
		titleLabel= new Label();
		titleLabel.getStyleClass().add("optionTitle");
		
		
		getChildren().add(titleLabel);
		getStyleClass().add("optionSelect");
		HBox contentHBox = new HBox();
		
		Button pageButtonLeft = new Button();
		pageButtonLeft.getStyleClass().add("pageButton-left");
		pageButtonLeft.setOnAction(new NextHandler(false));
		
		Button pageButtonRight = new Button();
		pageButtonRight.getStyleClass().add("pageButton-right");
		pageButtonRight.setOnAction(new NextHandler(true));
		
		contentHBox.getChildren().add(pageButtonLeft);
		buttonVBox = new VBox();

		contentHBox.getChildren().addAll(buttonVBox, pageButtonRight);
		getChildren().add(contentHBox);
		
		bottomHBox = new HBox();
		pageNumberLabel = new Label();
		
		bottomHBox.getChildren().add(pageNumberLabel);
		bottomHBox.getStyleClass().add("bottomHBox");
		getChildren().add(bottomHBox);
	}
	
	public void addButton(int page, String name, String mes){
		buttonList.get(page).add(new OptionButton(name, mes));
		updateState(0);
	}
	
	public void addButton(int page, OptionButton button){
		buttonList.get(page).add(button);
		updateState(0);
	}
	public void addPage(String t){
		buttonList.add(new ArrayList<OptionButton>());
		title.add(t);
		option.add("");
		updateState(0);
	}
	

	
	public void remove(int i){
		buttonList.remove(i);
		updateState(0);
	}
	
	private void updateState(int pg){
		titleLabel.setText(title.get(pg));
		page = pg;
		buttonVBox.getChildren().clear();
		double buttonHeight = (double)(height-80)/ (buttonList.get(page).size()+1);
		double buttonWidth = (double)(width-100);
		
		if (buttonList.get(page).size() >=3){
			buttonList.get(page).get(0).setPosStyle("top");
			buttonList.get(page).get(0).setOnAction(new ButtonHandler(buttonList.get(page).get(0).getValue()));
			buttonList.get(page).get(0).setPrefHeight(buttonHeight);
			buttonList.get(page).get(0).setPrefWidth(buttonWidth);
			buttonVBox.getChildren().add(buttonList.get(page).get(0));
			for (int i = 1; i < buttonList.get(page).size()-1; i++){
				buttonList.get(page).get(i).setPosStyle("mid");
				buttonVBox.getChildren().add(buttonList.get(page).get(i));
				
				buttonList.get(page).get(i).setPrefHeight(buttonHeight);
				buttonList.get(page).get(i).setPrefWidth(buttonWidth);
				
				buttonList.get(page).get(i).setOnAction(new ButtonHandler(buttonList.get(page).get(i).getValue()));
			}
			buttonList.get(page).get(buttonList.get(page).size()-1).setPosStyle("mid");
			buttonList.get(page).get(buttonList.get(page).size()-1).setOnAction(new ButtonHandler(buttonList.get(page).get(buttonList.get(page).size()-1).getValue()));
			buttonList.get(page).get(buttonList.get(page).size()-1).setPrefHeight(buttonHeight);
			buttonList.get(page).get(buttonList.get(page).size()-1).setPrefWidth(buttonWidth);
			buttonVBox.getChildren().add(buttonList.get(page).get(buttonList.get(page).size()-1));
		}
		else if (buttonList.get(page).size() == 2){
			buttonList.get(page).get(0).setPosStyle("top");
			buttonList.get(page).get(0).setOnAction(new ButtonHandler(buttonList.get(page).get(0).getValue()));
			buttonList.get(page).get(0).setPrefHeight(buttonHeight);
			buttonList.get(page).get(0).setPrefWidth(buttonWidth);
			buttonVBox.getChildren().add(buttonList.get(page).get(0));
			
			buttonList.get(page).get(buttonList.get(page).size()-1).setPosStyle("mid");
			buttonList.get(page).get(buttonList.get(page).size()-1).setOnAction(new ButtonHandler(buttonList.get(page).get(buttonList.get(page).size()-1).getValue()));
			buttonList.get(page).get(buttonList.get(page).size()-1).setPrefHeight(buttonHeight);
			buttonList.get(page).get(buttonList.get(page).size()-1).setPrefWidth(buttonWidth);
			buttonVBox.getChildren().add(buttonList.get(page).get(buttonList.get(page).size()-1));
			
		}
		else if (buttonList.get(page).size() == 1){
			buttonList.get(page).get(0).setPosStyle("mid");
			buttonList.get(page).get(0).setPrefHeight(buttonHeight);
			buttonList.get(page).get(0).setPrefWidth(buttonWidth);
			buttonList.get(page).get(0).setOnAction(new ButtonHandler(buttonList.get(page).get(0).getValue()));
			buttonVBox.getChildren().add(buttonList.get(page).get(0));
		}
		

		
		textFieldOtherHBox.setPrefHeight(buttonHeight);
		textFieldOtherHBox.setPrefWidth(buttonWidth);

		pageNumberLabel.setText(page + 1+ " / " + buttonList.size());
		buttonVBox.getChildren().add(textFieldOtherHBox);

		
		
	}
	private class ButtonHandler implements EventHandler<ActionEvent> {
		private String val ;
		public ButtonHandler(String opt) {
			this.val = opt ;
		}
		@Override
		public void handle(ActionEvent event) {
			addInfo(val);
		}

	}
	private void addInfo(String mes){
		String temp = mes;
		option.set(page, temp);
	}
	
	private void setPage(boolean next){
		if (next == true && (page + 1 < buttonList.size())){
			transitionPage(page+1);
		}
		else if ((next != true) && (page + 1>= 0)){
			transitionPage(page-1);
		}
		else if ((next == true) && (page + 1 == buttonList.size())){
			submit();
		}
	}
	
	private void transitionPage(int pg){
		FadeTransition ftIn = new FadeTransition(Duration.millis(250), buttonVBox);
		ftIn.setFromValue(0);
		ftIn.setToValue(1.0);
		ftIn.setCycleCount(1);
		

		FadeTransition ftOut = new FadeTransition(Duration.millis(250), buttonVBox);
		ftOut.setFromValue(1.0);
		ftOut.setToValue(0);
		ftOut.setCycleCount(1);
		

		ftOut.play();
		updateState(pg);
		ftIn.play();

	}
	
	private class NextHandler implements EventHandler<ActionEvent> {
		private boolean next ;
		public NextHandler(boolean n) {
			this.next = n;
		}
		@Override
		public void handle(ActionEvent event) {
			setPage(next);
		}

	}
	private class OtherHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			addInfo(textFieldOther.getText());
		}

	}
	private void submit(){
		for(String i : option){
			System.out.println(i);
		}

		
	}
	
	
}
