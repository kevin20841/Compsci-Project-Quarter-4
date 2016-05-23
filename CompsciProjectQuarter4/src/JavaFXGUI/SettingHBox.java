package JavaFXGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import JavaFXGUI.test.Person;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.*;
import javafx.util.Duration;
import javafx.collections.*;
import backend.*;
import javafx.scene.text.Text;
import javafx.print.*;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;

@SuppressWarnings("restriction")
public class SettingHBox extends HBox{
	private ObservableList<StudentProperty> goingIn = FXCollections.observableArrayList();
	private ObservableList<StudentProperty> goingOutIn =FXCollections.observableArrayList();
	private StartApplication parent;
	private TableView tableSignOut;
	private TableView tableSignIn;
	private HashMap<String, StudentList>data;
	private String[] headers = {"Date", "Student Name", "Student ID", "Grade", "Time In", 
			"Reason for Late Arrival", "Note Status"};
	private String[] headersOut = {"Date", "Student Name", "Student ID", "Grade", "Reason for leaving", 
			"Excused By", "Time of Departure", "Time of Return", "Note Status"};;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SettingHBox(StartApplication p, double width, HashMap<String, StudentList>d){
		getStyleClass().add("settingHBox");
		data = d;
		parent = p;
		for (Student st : data.get("in").getStudentList())	{
			goingIn.add(new StudentProperty(st));
		}

		for (Student st : data.get("outin").getStudentList())	{
			goingOutIn.add(new StudentProperty(st));
		}


		setPrefWidth(width);
		HBox contentHBox = new HBox();
		VBox buttonVBox = new VBox();
		AnchorPane buttonAnchorPane = new AnchorPane();
		buttonAnchorPane.getStyleClass().add("buttonAnchorPane");

		HBox viewButtonHBox = new HBox();
		viewButtonHBox.setPadding(new Insets(15, 12, 15, 12));
		viewButtonHBox.setSpacing(10);

		Button printButton = new Button("Print");
		printButton.setPrefSize(100, 40);
		printButton.setMinSize(100, 40);
		printButton.setOnAction(e -> print(true));

		Button openButton = new Button("Open");
		openButton.setPrefSize(100, 40);
		openButton.setMinSize(100, 40);
		openButton.getStyleClass().add("openButton");
		openButton.setOnAction(e -> openFile());

		Button saveButton = new Button("Save");
		saveButton.setPrefSize(100, 40);
		saveButton.setMinSize(100, 40);
		saveButton.getStyleClass().add("saveButton");
		saveButton.setOnAction(e -> saveFile());



		Button closeButton = new Button("Close");
		closeButton.setPrefSize(100, 40);
		closeButton.setMinSize(100, 40);
		closeButton.setOnAction(e -> parent.hideOptionsPage());
		closeButton.getStyleClass().add("closeButton");

		viewButtonHBox.getChildren().add(closeButton);
		buttonVBox.getChildren().addAll(printButton, openButton, saveButton);

		buttonVBox.setPadding(new Insets(15, 15, 15, 15));
		buttonVBox.setSpacing(20);

		AnchorPane.setTopAnchor(buttonVBox, 0.0);
		AnchorPane.setLeftAnchor(buttonVBox, 0.0);

		AnchorPane.setBottomAnchor(closeButton, 15.0);
		AnchorPane.setLeftAnchor(closeButton, 15.0);

		buttonAnchorPane.getChildren().addAll(buttonVBox, closeButton);
		contentHBox.getChildren().addAll(buttonAnchorPane);

		getChildren().add(contentHBox);

		TabPane tableTabPane = new TabPane();
		tableTabPane.setSide(Side.LEFT);
		tableTabPane.getStyleClass().add("tabTable");

		tableSignIn = new TableView();


		ArrayList<TableColumn> columnList = new ArrayList<TableColumn>();


		double[] widths = {0.1, 0.15, 0.15, 0.07, 0.1, 0.25, 0.18}; 

		for(int i = 0; i < headers.length; i++){
			columnList.add(createTableColumn(headers[i]));
			columnList.get(i).setResizable(false);
			columnList.get(i).prefWidthProperty().bind(tableSignIn.widthProperty().multiply(widths[i]));
			if (i != 3){
				columnList.get(i).setCellFactory(column -> {
					return new TableCell<StudentProperty, String>() {
						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							Text text = new Text(item);
							text.wrappingWidthProperty().bind(getTableColumn().widthProperty()); // Setting the wrapping width to the Text
							setGraphic(text);	
						}
					};
				});
			}
			tableSignIn.getColumns().add(columnList.get(i));
		}

		columnList.get(0).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("date"));
		columnList.get(1).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("studentID"));
		columnList.get(2).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("name"));
		columnList.get(3).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				Integer>("grade"));
		columnList.get(4).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("time"));
		columnList.get(5).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("reason"));
		columnList.get(6).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("note"));

		tableSignIn.setItems(goingIn);


		tableSignOut = new TableView();


		ArrayList<TableColumn> columnListOut = new ArrayList<TableColumn>();


		double[] widthsOut = {0.05, 0.125, 0.10, 0.05, 0.15, 0.1, 0.15, 0.125, 0.15}; 

		for(int i = 0; i < headersOut.length; i++){
			columnListOut.add(createTableColumn(headersOut[i]));
			columnListOut.get(i).setResizable(false);
			columnListOut.get(i).prefWidthProperty().bind(tableSignOut.widthProperty().multiply(widthsOut[i]));
			if (i != 3){
				columnListOut.get(i).setCellFactory(column -> {
					return new TableCell<StudentProperty, String>() {
						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							Text text = new Text(item);
							text.wrappingWidthProperty().bind(getTableColumn().widthProperty()); // Setting the wrapping width to the Text
							setGraphic(text);	
						}
					};
				});
			}
			tableSignOut.getColumns().add(columnListOut.get(i));
		}

		columnListOut.get(0).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("date"));
		columnListOut.get(1).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("studentID"));
		columnListOut.get(2).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("name"));
		columnListOut.get(3).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				Integer>("grade"));
		columnListOut.get(4).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("reason"));
		columnListOut.get(5).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("excused"));
		columnListOut.get(6).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("time"));
		columnListOut.get(7).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("arrTime"));
		columnListOut.get(8).setCellValueFactory(new PropertyValueFactory<StudentProperty, 
				String>("note"));

		tableSignOut.setItems(goingOutIn);


		tableSignOut.setPrefWidth(3000);
		tableSignIn.setPrefWidth(3000);
		Tab signInTableTab = new Tab("Sign In Table");
		signInTableTab.setContent(tableSignIn);

		Tab signOutTableTab = new Tab("Sign Out Table");
		signOutTableTab.setContent(tableSignOut);
		tableTabPane.getTabs().addAll(signInTableTab, signOutTableTab);
		tableTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		getChildren().add(tableTabPane);
	}
	@SuppressWarnings("rawtypes")
	private TableColumn createTableColumn(String mes){
		return new TableColumn(mes);
	}
	
	
	
	//TODO Print
	@SuppressWarnings("unchecked")
	private void print(boolean signIn){
		TableView toBePrinted = new TableView();
		
	}
	private void openFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Database File");
		fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Database Files", "*.mer"));
		File selectedFile = fileChooser.showOpenDialog(parent.stage);
		System.out.println(selectedFile.getPath());
		StudentList studentData =  parent.readStudentDatabase(selectedFile.getPath());
		data.put("database", studentData);
	}
	
	private void saveFile(){
		DirectoryChooser fileChooser = new DirectoryChooser();
		fileChooser.setTitle("Save Log File");
		File selectedFile = fileChooser.showDialog((parent.stage));
		PrintWriter writer = null;
		LocalDate todayDate = LocalDate.now();
		String date = todayDate.toString();
		
		try {
			writer = new PrintWriter(selectedFile.getPath() + "/" + date + "-Sign-In.csv");
		} catch (FileNotFoundException e) {
			System.out.println("EROROR");
			e.printStackTrace();
		}
		
		
		for (String i : headers){
			writer.print( i+ ", ");
		}
		writer.println();
		ArrayList<Student> temp =  data.get("in").getStudentList();
		for (int i = 0; i < temp.size(); i++){
			writer.print (temp.get(i).getDate() + ", ");
			writer.print( temp.get(i).getName() + ", ");
			writer.print( temp.get(i).getStudentID() + ", ");
			writer.print( temp.get(i).getGrade() + ", ");
			writer.print( temp.get(i).getTime() + ", ");
			writer.print( temp.get(i).getReason() + ", ");
			writer.print( temp.get(i).getNote() + ", ");
		}
		
		
		writer.close();
	
		PrintWriter writerOutIn = null;
		try {
			writerOutIn = new PrintWriter(selectedFile.getPath() + "/" + date+"-Sign-Out.csv");
		} catch (FileNotFoundException e) {
			System.out.println("EROROR");
			e.printStackTrace();
		}
		
		for (String i : headersOut){
			writerOutIn.print("  \" "  + i+ " \", ");
		}
		writerOutIn.println();	
		temp =  data.get("outIn").getStudentList();
		for (int i = 0; i < temp.size(); i++){
			writerOutIn.print(temp.get(i).getDate() + ", ");
			writerOutIn.print(temp.get(i).getName() + " , ");
			writerOutIn.print(temp.get(i).getStudentID() + ", ");
			writerOutIn.print (temp.get(i).getGrade() + ", ");
			writerOutIn.print(temp.get(i).getReason() + ", ");
			writerOutIn.print( temp.get(i).getExcused() + ", ");
			writerOutIn.print( temp.get(i).getTime() + ", ");
			writerOutIn.print(temp.get(i).getArrTime() + ", ");
			writerOutIn.print(temp.get(i).getNote() + ", ");
		}
		writerOutIn.close();
		
	}
}
