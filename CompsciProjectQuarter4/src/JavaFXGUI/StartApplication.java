package JavaFXGUI;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.*;
import javafx.util.Duration;
import backend.Student;
import backend.StudentList;
import backend.StudentProperty;
@SuppressWarnings("restriction")
public class StartApplication extends Application {
	private StackPane content;
	private MenuTabPane tabPane;
	private FadeTransition dimTransition;
	private FadeTransition brightenTransition;
	private BorderPane optionBorderPane;
	private HashMap<String, StudentList> data;
	private HBox contentPane;
	public Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		StudentList studentData =  readStudentDatabase("src/data/SMCS10_noGrades.mer");
		StudentList studentOut = new StudentList();
		StudentList studentIn = new StudentList();
		StudentList studentOutIn = new StudentList();
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

		brightenTransition = new FadeTransition(Duration.millis(250), optionBorderPane);
		brightenTransition.setFromValue(0);
		brightenTransition.setToValue(1.0);
		brightenTransition.setCycleCount(1);

		
		


	}

	public void showOptionsPage(){
		content.getChildren().add(optionBorderPane);
		SettingHBox optionBorderContentHBox = new SettingHBox(this, stage.getWidth() - 102, data);
		contentPane.getChildren().clear();
		contentPane.getChildren().add(optionBorderContentHBox);
		brightenTransition.play();
	}
	
	
	public void hideOptionsPage(){
		content.getChildren().remove(optionBorderPane);

	}
	@SuppressWarnings("resource")
	public StudentList readStudentDatabase(String fileName){
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
			for(String studentField: file.nextLine().split("\",\"")){
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
		
		for(ArrayList<String> student: studentData){
			String id = (student.get(field.get("ID")));
			String name = student.get(field.get("FIRST"))+" "+student.get(field.get("LAST"));
			int grade = Integer.parseInt(student.get(field.get("GR")));
			list.add(new Student (name,grade,id));
		
		} 
		return list;
		
	}


	public void dim(){
		dimTransition.play();
	}

	public void brighten(){
		brightenTransition.play();
	}
	
	


}
