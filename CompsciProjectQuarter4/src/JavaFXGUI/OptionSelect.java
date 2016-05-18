package JavaFXGUI;
import java.awt.TextArea;
import java.util.ArrayList;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.*;
@SuppressWarnings("restriction")
public class OptionSelect extends VBox{
	private ArrayList<ArrayList<OptionButton>> buttonList;
//TODO SELECTED CHECK MARK
	private ArrayList<String> title = new ArrayList<String>();
	private VBox buttonVBox;
	private String option;
	private int page;
	private Label titleLabel;
	private int height, width;
	private HBox textFieldOtherHBox;
	private TextField textFieldOther;
	public OptionSelect(int w, int h, String t){
		buttonList = new ArrayList<ArrayList<OptionButton>>();
		buttonList.add(new ArrayList<OptionButton>());
		height = h;
		width = w;
		
		title.add(t);
		init();
	}

	public OptionSelect(int h, int w, ArrayList<OptionButton> optionList, String t){
		buttonList = new ArrayList<ArrayList<OptionButton>>();
		buttonList.add(optionList);
		title.add(t);
		init();
		height = h;
		width = w;
		updateState(0);
		
	}
	
	public void init(){
		setMaxHeight(height);
		setMaxWidth(width);
		
		
		textFieldOtherHBox  = new HBox();
		
		Label textFieldOtherLabel = new Label("Other: ");
		textFieldOther = new TextField();
		Button textFieldOtherButton = new Button("Submit");
		
		textFieldOtherButton.setOnAction(new OtherHandler());
		textFieldOtherButton.getStyleClass().add("textFieldOtherButton");
		
		textFieldOtherHBox.getChildren().addAll(textFieldOtherLabel, textFieldOther, textFieldOtherButton);

		
		textFieldOtherHBox.getStyleClass().add("optionTextFieldOther");
		
		titleLabel= new Label(title.get(0));
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
		double buttonHeight = 50;
		if(buttonList.get(page).size() != 0){
			buttonHeight = (double)(height-50)/ (buttonList.get(page).size()+1);
		}
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
		buttonVBox.getChildren().add(textFieldOtherHBox);
		
		
		
		
	}
	private class ButtonHandler implements EventHandler<ActionEvent> {
		private String option ;
		public ButtonHandler(String opt) {
			this.option = opt ;
		}
		@Override
		public void handle(ActionEvent event) {
			addInfo(option);
		}

	}
	private void addInfo(String mes){
		option = mes;
		System.out.println(mes);
	}
	
	private void setPage(boolean next){
		if (next == true && (page + 1 < buttonList.size())){
			transitionPage(page+1);
		}
		else if (next != true && (page-1 >= 0)){
			transitionPage(page-1);
		}
		else{
			System.out.println("fail");
			System.out.println(next);
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
	
}
