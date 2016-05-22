package JavaFXGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import backend.Student;
import backend.StudentList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class StartTab extends Tab {
	
	private MenuTabPane parent;
	private HashMap<String, StudentList> data = new HashMap<String, StudentList>();
	private AnimatedAlertBox alert;
	
	public StartTab(MenuTabPane p, String title){
		StudentList studentData =  readStudentDatabase();
		StudentList studentOut = new StudentList();
		StudentList studentIn = new StudentList();
		StudentList studentOutIn = new StudentList();
		
		data.put("database", studentData);
		data.put("out", studentOut);
		data.put("in", studentIn);
		data.put("outin", studentOutIn);
		
		parent = p;
		setText(title);
		BorderPane content = new BorderPane();

		HBox buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		buttonHBox.setPadding(new Insets(15, 12, 15, 12));
		buttonHBox.setSpacing(10);
		
		HBox viewButtonHBox = new HBox();
		viewButtonHBox.setPadding(new Insets(15, 12, 15, 12));
		viewButtonHBox.setSpacing(10);
		
		
		Button buttonSignIn = new Button("Sign In");
		buttonSignIn.setPrefSize(200, 40);
		buttonSignIn.setOnAction(e -> moveOn(true));

		
		Button buttonSignOut = new Button("Sign Out");
		buttonSignOut.setPrefSize(200, 40);
		buttonSignOut.setOnAction(e -> moveOn(false));
		
		Button viewButton = new Button("View Records");
		viewButton.setPrefSize(150, 20);
		
		buttonHBox.getChildren().addAll(buttonSignIn, buttonSignOut);
		viewButtonHBox.getChildren().add(viewButton);
		
		alert = new AnimatedAlertBox("Submission Sucessful!", false);
		
		content.setTop(alert);
		
		content.setCenter(buttonHBox);
		content.setBottom(viewButtonHBox);
		setContent(content);
		
	}
	
	private void moveOn(boolean signIn){
		
		EnterStudentTab tab2 = new EnterStudentTab(parent, this, "Enter ID",  data, signIn);
		setDisable(true);
		parent.getTabs().add(tab2);
		parent.getSelectionModel().select(tab2);
		
	}
	private StudentList readStudentDatabase(){
		StudentList stData = new StudentList();
	
		for (int i = 0; i < 1200; i++){

			
			stData.add(new Student("test" + i, i % 10 , "" + i));
		}
		return stData;
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
			System.out.println(name+" "+id+" "+grade);
		
		} 
		return list;
		
	}
	public void displaySucess(){
		alert.play();
	}
	
	
	
	

}
