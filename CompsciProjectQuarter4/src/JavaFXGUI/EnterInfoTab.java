package JavaFXGUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import backend.Student;
import backend.StudentList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
@SuppressWarnings("restriction")
public class EnterInfoTab extends Tab{

	private boolean goingIn;
	private MenuTabPane parent;
	private EnterStudentTab previous;
	private Student student;
	private HashMap<String, StudentList> data;
	private AnimatedAlertBox alert;

	public EnterInfoTab(MenuTabPane par, EnterStudentTab prev, String title,
			HashMap<String, StudentList> d, boolean gIn, Student st){
		setText(title);
		goingIn = gIn;
		previous = prev;
		parent = par;
		student = st;

		OptionSelect infoOptionSelect = new OptionSelect(700, 500, this);
		data = d;

		BorderPane content = new BorderPane();

		alert = new AnimatedAlertBox("Please select all options.", true);
		content.setTop(alert);
		int version = 0;
		if (goingIn){
			version = 0;
			for (int i =0; i < data.get("outin").getStudentList().size(); i++){
				if (student.equals(data.get("outin").getStudentList().get(i))){
					version = 2;
				}

			}
		}
		else{
			version = 1;
		}



		Scanner file = null;
		try {
			file = new Scanner(new File("src/data/options.sip"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		int v = -1;
		int page = -1; 
		String stringData = "";
		while(file.hasNext()){

			stringData= file.nextLine();
			if (stringData.equals("+++")){
				v ++;
			}
			else if (stringData.equals("++") && version == v){
				page++;
				stringData = file.nextLine();
				infoOptionSelect.addPage(stringData);
			}
			else if (version == v){
				infoOptionSelect.addButton(page, stringData, stringData);
			}
		}
		file.close();


		// TO HERE: 
		infoOptionSelect.setAlignment(Pos.CENTER);


		Button backButton = new Button("Back");
		backButton.setOnAction(e -> goBack());
		backButton.setPrefSize(150, 20);

		HBox navHBox = new HBox();
		navHBox.setPadding(new Insets(15, 12, 15, 12));
		navHBox.setSpacing(10);

		navHBox.getChildren().add(backButton);


		content.setCenter(infoOptionSelect);
		content.setBottom(navHBox);
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
	public ArrayList<String> readOptionList(){

		return null;
	}
	public void addData(ArrayList<String> option){
		if (option.get(0).isEmpty() || option.get(1).isEmpty()){
			alert.play();
		}


		if (!goingIn){

			student.setReason(option.get(0));
			student.setExcused(option.get(1));
			student.setNote(option.get(2));
			data.get("outin").add(student);
		}
		else{
			student.setReason(option.get(0));
			student.setNote(option.get(1));
			data.get("in").add(student);
		}
		goBack();
		previous.goBack(true);
	}

}
