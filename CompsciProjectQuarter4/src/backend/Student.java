package backend;

public class Student{
	// fields
	private String name;
	private int grade;
	private int studentID;
	
	// Constructor. name is lower case so that compareTo for strings arranges 
	// by alphabetical order. studentID is Integer wrapper class for same reason.
	public Student(String n, int g, int sID){
		name = n.toLowerCase();
		grade = (Integer) g;
		studentID = sID;
	}
	
	//getters
	public String getName() {
		return name;
	}

	public int getGrade() {
		return grade;
	}

	public int getStudentID() {
		return studentID;
	}
	
	
}
