package JavaFXGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import backend.Student;
import backend.StudentList;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.scene.image.*;
@SuppressWarnings("restriction")
/**
 * The Main class of the program. This class is run to initialize everything. 
 * @author Kevin
 */
public class StartApplication extends Application {
	private StackPane content;
	private MenuTabPane tabPane;
	private FadeTransition brightenTransition;
	private BorderPane optionBorderPane;
	private HashMap<String, StudentList> data;
	private HBox contentPane;
	public Stage stage;

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Initializes all of the values. The School database is read from an internal
	 * file "src/data/SchoolDatabase.mer." The other two StudentLists are emtpy if the
	 * backup file does not exist, other they are populated with the backup file's data.
	 * The data itself is stored in a HashMap of Strings and StudentLists.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		StudentList studentData =  readStudentDatabase("src/data/SchoolDatabase.mer", "d");
		StudentList studentIn = new StudentList();
		StudentList studentOutIn = new StudentList();
		stage.getIcons().add(new Image("img/logo.jpg"));
		
		LocalDate todayDate = LocalDate.now();
		String date = todayDate.toString();
		File f = new File("src/backup/" + date+"-IN.csv");
		if(f.exists() && !f.isDirectory()) { 
			studentIn = readStudentDatabase(f.getPath(), "i");
		}
		else{
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter printWriter = new PrintWriter (f);
				printWriter.println("DATE,ID,NAME,GR,TIME,REASON,NOTE");
				printWriter.close();
			}
			catch(IOException e1){
				e1.printStackTrace();
				
			}
		}

		
		f = new File("src/backup/" + date+"-OUT.csv");
		if(f.exists() && !f.isDirectory()) { 
			studentOutIn = readStudentDatabase(f.getPath(), "o");
		}
		else{
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter printWriter = new PrintWriter (f);
				printWriter.println("DATE,ID,NAME,GR,REASON,EXCUSED,TIME,ARRTIME,NOTE");
				printWriter.close();
			}
			catch(IOException e1){
				e1.printStackTrace();
				
			}
		}

		
		
		data = new HashMap<String, StudentList>();
		data.put("database", studentData);
		data.put("in", studentIn);
		data.put("outin", studentOutIn);
		primaryStage.setTitle("Office Sign In"); //TODO Title
		Group root = new Group();
		Scene scene = new Scene(root, 1200, 750, Color.WHITE);
		tabPane = new MenuTabPane(this, data);

		content = new StackPane();
		BorderPane borderPane = new BorderPane();

		scene.getStylesheets().add("css/application.css");

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setCenter(tabPane);
		content.getChildren().add(borderPane);
		root.getChildren().add(content);
		primaryStage.setScene(scene);
		primaryStage.show();

		optionBorderPane= new BorderPane();
		optionBorderPane.getStyleClass().add("optionContent");
		optionBorderPane.setOpacity(0);
		contentPane = new HBox();


		contentPane.getStyleClass().add("ContentPane");


		contentPane.setPrefHeight(primaryStage.getHeight()-100);
		contentPane.setMaxHeight(primaryStage.getHeight()-100);

		contentPane.setPrefWidth(primaryStage.getWidth()-100);
		contentPane.setMaxWidth(primaryStage.getWidth()-100);

		optionBorderPane.setCenter(contentPane);

		brightenTransition = new FadeTransition(Duration.millis(100), optionBorderPane);
		brightenTransition.setFromValue(0);
		brightenTransition.setToValue(1.0);
		brightenTransition.setCycleCount(1);

	}
	/**
	 * Shows the settings page.
	 */

	public void showOptionsPage(){
		content.getChildren().add(optionBorderPane);
		PasswordLock optionBorderContentHBox = new PasswordLock(this, stage.getWidth() - 102, data);
		contentPane.getChildren().clear();
		contentPane.getChildren().add(optionBorderContentHBox);
		brightenTransition.play();
	}

	/**
	 * Hides the settings page.
	 */
	public void hideOptionsPage(){
		content.getChildren().remove(optionBorderPane);
	}
	/**
	 * Reads from a mer file. A Mer file is a .csv with an extra row on the top for headers.
	 * No spaces are in between elements of the .csv. If there is an error, a pop-up window that displays an eroor
	 * is displayed.
	 * @param fileName The file path of the mer.
	 * @param opt whcih type of data it ise (database, sign in, or signout)
	 * @return Returns a StudentList of students in the data file.
	 */
	@SuppressWarnings("resource")
	public StudentList readStudentDatabase(String fileName, String opt){
		try{
			StudentList list = new StudentList();
			Scanner file = null;
			try {
				file = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//create Map of Fields (line 1)
			Map<String, Integer> field = new HashMap<String,Integer>();
			int i = 0;
			for(String fieldName: file.nextLine().split(",")){
				field.put(fieldName.trim(), i++);
			}

			//Load in student data
			ArrayList<ArrayList<String>> studentData = new ArrayList<ArrayList<String>>();
			while(file.hasNext()){
				ArrayList<String> aStudent = new ArrayList<String>();
				String line = file.nextLine();
				line = line.substring(1);
				line = line.substring(0,line.length()-1);
				for(String studentField: line.split("\",\"")){
					aStudent.add(studentField.trim());
				}
				studentData.add(aStudent);

			}

			//eliminate duplicates
			for(i = 0; i < studentData.size();  i++){
				String id = studentData.get(i).get(field.get("ID"));
				for(int j = studentData.size() - 1; j > i; j--){
					String id2 = studentData.get(j).get(field.get("ID"));
					if(id.equals(id2)){
						studentData.remove(j);
					}
				}	
			}
			if (opt == "d"){
				for(ArrayList<String> student: studentData){
					String id = (student.get(field.get("ID")));
					String name = student.get(field.get("FIRST"))+" "+student.get(field.get("LAST"));
					int grade = Integer.parseInt(student.get(field.get("GR")));
					list.add(new Student (name,grade,id));
				}
			}
			else if (opt == "i"){
				for(ArrayList<String> student: studentData){
					String id = student.get(field.get("ID"));
					String name = student.get(field.get("NAME"));
					int grade = Integer.parseInt(student.get(field.get("GR")));
					Student st = new Student (name,grade,id);
					st.setTime(student.get(field.get("TIME")));
					st.setReason(student.get(field.get("REASON")));
					st.setNote(student.get(field.get("NOTE")));
					st.setDate(student.get(field.get("DATE")));
					list.add(st);
				}
			}
			else if (opt == "o"){
				for(ArrayList<String> student: studentData){
					String id = student.get(field.get("ID"));
					String name = student.get(field.get("NAME"));
					int grade = Integer.parseInt(student.get(field.get("GR")));
					Student st = new Student (name,grade,id);
					st.setTime(student.get(field.get("TIME")));
					st.setReason(student.get(field.get("REASON")));
					st.setNote(student.get(field.get("NOTE")));
					st.setArrTime(student.get(field.get("ARRTIME")));
					st.setExcused(student.get(field.get("EXCUSED")));
					st.setDate(student.get(field.get("DATE")));
					list.add(st);
				}
			}
			return list;
		} catch (Exception e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("File format error");
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("css/application.css");
			dialogPane.getStyleClass().add("alertBox");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.show();
			return new StudentList();
		}
	}


}
