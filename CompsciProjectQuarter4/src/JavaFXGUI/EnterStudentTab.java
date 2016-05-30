package JavaFXGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javafx.scene.input.MouseEvent;
import backend.Student;
import backend.StudentList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;


@SuppressWarnings("restriction")
public class EnterStudentTab extends Tab {
	private boolean goingIn;
	private MenuTabPane parent;
	private StartTab previous;
	private ObservableList<String> nameEntries;  
	private ListView<String> list;
	private StudentList studentData;
	private HashMap<String, StudentList> data;
	private TextField searchTextField;
	private AnimatedAlertBox alert;

	public EnterStudentTab(MenuTabPane par, StartTab prev, String title, HashMap<String, 
			StudentList> d, boolean gIn){

		list = new ListView<String>();

		goingIn = gIn;

		parent = par;
		previous = prev;
		studentData = d.get("database");
		data = d;

		setText(title);
		BorderPane content = new BorderPane();

		VBox imageHBox = new VBox();
		imageHBox.setAlignment(Pos.CENTER);
		imageHBox.setPadding(new Insets(15, 12, 15, 12));
		imageHBox.setSpacing(10);

		alert = new AnimatedAlertBox("The ID or Student name does not exist.", true);




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

		Label studentIDLabel = new Label("Enter Student Name, six-digit ID, or scan your student card below: ");
		Button submitButton = new Button("Submit");
		submitButton.setDefaultButton(true);
		submitButton.setPrefSize(100, 20);
		submitButton.setOnAction(e -> submitButton());

		labelHBox.getChildren().addAll(studentIDLabel);
		labelHBox.setAlignment(Pos.CENTER);

		HBox navHBox = new HBox();
		navHBox.setPadding(new Insets(15, 12, 15, 12));
		navHBox.setSpacing(10);

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> goBack(false));
		backButton.setPrefSize(150, 20);

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
					moveOn(goingIn, data.get("database").getStudentByToString(currentItemSelected));
				}
			}
		});



		imageHBox.getChildren().addAll(photoIDView, labelHBox, searchVBox, submitButton);
		content.setCenter(imageHBox);
		content.setBottom(navHBox);
		content.setTop(alert);
		setContent(content);
	}

	public void goBack(boolean sucess){
		previous.setDisable(false);
		parent.getSelectionModel().select(previous);
		die();
		if (sucess){
			previous.displaySucess();
		}
	}
	public void die(){
		parent.getTabs().remove(this);
	}


	public void searchStudent(ObservableList<String> entries, String oldVal, String newVal) {

		if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
			list.setItems( entries );
		}

		String[] parts = newVal.toUpperCase().split(" ");

		ObservableList<String> subentries = FXCollections.observableArrayList();
		for ( Object entry: list.getItems() ) {
			boolean match = true;
			String entryText = (String)entry;
			for ( String part: parts ) {
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
		ArrayList<String> IDList = data.get("database").getIDList();
		ArrayList<String> NameList = data.get("database").getNameList();

		if (IDList.contains(submittedText)){
			moveOn(goingIn, data.get("database").getStudentByID(submittedText));
		}
		else if (NameList.contains(submittedText)){
			moveOn(goingIn, data.get("database").getStudentByName(submittedText));
		}
		else{
			if (submittedText.trim().isEmpty() ){
				alert.play("Please submit your name, submit your ID, or scan your Student ID");
			}
			else{
				alert.play("The student \"" + submittedText + "\" was not found.");
			}

		}

	}
	private void moveOn(boolean signIn, Student student){

		EnterInfoTab tab3;
		boolean outin = false;
		int j = 0;
		for (int i =0; i < data.get("outin").getStudentList().size(); i++){
			if (student.equals(data.get("outin").getStudentList().get(i))){
				j = i;
				outin=true;
			}

		}
		if (outin){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
			LocalTime todayTime = LocalTime.now();
			String time = formatter.format(todayTime);

			data.get("outin").getStudentList().get(j).setArrTime(time);
			LocalDate todayDate = LocalDate.now();
			String date = todayDate.toString();
			File f = new File("src/backup/" + date+"-OUT.bup");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				PrintWriter printWriter = new PrintWriter (f);
				printWriter.println("DATE,ID,NAME,GR,REASON,EXCUSED,TIME,ARRTIME,NOTE");
				for(Student st : data.get("outin").getStudentList()){
					printWriter.print("\"" + st.getDate() + "\",");
					printWriter.print("\"" + st.getStudentID() + "\",");
					printWriter.print("\"" + st.getName() + "\",");
					printWriter.print("\"" + st.getGrade() + "\",");
					printWriter.print("\"" + st.getReason() + "\",");
					printWriter.print("\"" + st.getExcused() + "\",");
					printWriter.print("\"" + st.getTime() + "\",");
					printWriter.print("\"" + st.getArrTime() + "\",");
					printWriter.print("\"" + st.getNote() + "\"");
					printWriter.println();
				}
				printWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			goBack(true);
		}
		else 
		{
			if (signIn){
				tab3= new EnterInfoTab(parent, this, "Sign In",  data, signIn, student);
			}
			else{
				tab3 = new EnterInfoTab(parent, this, "Sign Out",  data, signIn, student);
			}
			setDisable(true);
			parent.getTabs().add(tab3);
			parent.getSelectionModel().select(tab3);
		}

	}



}
