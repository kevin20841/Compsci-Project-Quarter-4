package JavaFXGUI;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.scene.input.MouseEvent;
import backend.StudentList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.util.Duration;


@SuppressWarnings("restriction")
// TODO implement ID searching
public class EnterInfoTab extends Tab {
	private boolean goingIn;
	private MenuTabPane parent;
	private Tab previous;
	private ObservableList<String> nameEntries;  
	private ListView<String> list = new ListView();
	private StudentList studentData;
	private TextField searchTextField;
	private SequentialTransition seqT;

	public EnterInfoTab(MenuTabPane p, Tab prev, String title, StudentList stData, boolean gIn){

		goingIn = gIn;
		parent = p;
		previous = prev;
		studentData = stData;
		
		setText(title);
		BorderPane content = new BorderPane();

		VBox imageHBox = new VBox();
		imageHBox.setAlignment(Pos.CENTER);
		imageHBox.setPadding(new Insets(15, 12, 15, 12));
		imageHBox.setSpacing(10);
		
		AnimatedAlertBox alert = new AnimatedAlertBox("The ID or Student name does not exist.");
		alert.setOpacity(0);
		
		FadeTransition ftIn = new FadeTransition(Duration.millis(750), alert);
		ftIn.setFromValue(0);
		ftIn.setToValue(1.0);
		ftIn.setCycleCount(1);
		PauseTransition pt = new PauseTransition(Duration.millis(4000));
		FadeTransition ftOut = new FadeTransition(Duration.millis(750), alert);
		ftOut.setFromValue(1.0);
		ftOut.setToValue(0);
		ftOut.setCycleCount(1);
		
		seqT = new SequentialTransition (ftIn, pt, ftOut);
	    

		Image photoID  = new Image("img/image.png");
		ImageView photoIDView = new ImageView();
		photoIDView.setImage(photoID);
		photoIDView.setFitWidth(300);
		photoIDView.setPreserveRatio(true);
		photoIDView.setSmooth(true);
		photoIDView.setCache(true);

		HBox labelHBox = new HBox();
		imageHBox.setPadding(new Insets(15, 12, 15, 12));
		imageHBox.setSpacing(10);

		Label studentIDLabel = new Label("Enter Student Name, ID, or scan you student card below: ");
		Button submitButton = new Button("Submit");
		submitButton.setPrefSize(100, 20);
		submitButton.setOnAction(e -> submitButton());

		labelHBox.getChildren().addAll(studentIDLabel);
		labelHBox.setAlignment(Pos.CENTER);

		HBox navHBox = new HBox();
		navHBox.setPadding(new Insets(15, 12, 15, 12));
		navHBox.setSpacing(10);

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> goBack());
		backButton.setPrefSize(100, 20);

		navHBox.getChildren().add(backButton);
		

		
		nameEntries = FXCollections.observableArrayList(studentData.getInfoList());
		searchTextField = new TextField();
		searchTextField.setPromptText("Search");
		searchTextField.textProperty().addListener(
				new ChangeListener<Object>() {
					public void changed(ObservableValue<?> observable, 
							Object oldVal, Object newVal) {
						searchStudent(nameEntries, (String)oldVal, (String)newVal);
					}
				});
		Platform.runLater(new Runnable() {
			public void run() {
				searchTextField.requestFocus();
			}
		});
        list.setMaxHeight(400);
        list.setItems(nameEntries);
        list.getStyleClass().add("searchTextField");
        
        VBox searchVBox = new VBox();
        searchVBox.setPadding(new Insets(15, 12, 15, 12));
        searchVBox.setSpacing(10);
        searchVBox.setMaxSize(500, 300);
        searchVBox.getChildren().addAll(searchTextField, list);
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                   String currentItemSelected = list.getSelectionModel().getSelectedItem();
                   list.getSelectionModel().select(-1);
                   // TODO Implement nextPage
                   System.out.println(currentItemSelected);
                }
            }
        });

                    	

        imageHBox.getChildren().addAll(photoIDView, labelHBox, searchVBox, submitButton);
		content.setCenter(imageHBox);
		content.setBottom(navHBox);
		content.setTop(alert);
		setContent(content);
	}

	public void goBack(){
		previous.setDisable(false);
		parent.getSelectionModel().select(previous);
		die();
	}
	public void die(){
		parent.getTabs().remove(this);
	}


	public void searchStudent(ObservableList<String> entries, String oldVal, String newVal) {
		// If the number of characters in the text box is less than last time
		// it must be because the user pressed delete
		if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
			// Restore the lists original set of entries 
			// and start from the beginning
			list.setItems( entries );
		}

		// Break out all of the parts of the search text 
		// by splitting on white space
		String[] parts = newVal.toUpperCase().split(" ");

		// Filter out the entries that don't contain the entered text
		ObservableList<String> subentries = FXCollections.observableArrayList();
		for ( Object entry: list.getItems() ) {
			boolean match = true;
			String entryText = (String)entry;
			for ( String part: parts ) {
				// The entry needs to contain all portions of the
				// search string *but* in any order
				if ( ! entryText.toUpperCase().contains(part) ) {
					match = false;
					break;
				}
			}

			if ( match ) {
				subentries.add(entryText);
			}
		}
		list.setItems(subentries);
	}
	
	private void submitButton(){
		String submittedText = searchTextField.getText();
		ArrayList<Integer> IDList = studentData.getIDList();
		ArrayList<String> NameList = studentData.getNameList();
			
		if (IDList.contains(submittedText)){
			moveOn();
		}
		else if (NameList.contains(submittedText)){
			moveOn();
		}
		else{
			displayError();
		}
		
	}
	private void moveOn(){
		System.out.println("Success!");
	}
	
	private void displayError(){
		seqT.play();

	}

}
