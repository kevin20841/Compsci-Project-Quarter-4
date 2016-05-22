package backend;
/** 
 * Data class that holds information on each student. Holds name, studentID and grade.
 * @author	Kevin
 */
public class Student implements Comparable<Student>{
	
	private String name;
	private int grade;
	private String studentID;
	private String reason;
	private String note;

	/**
	 *  Constructor. name is lower case so that compareTo for strings arranges by alphabetical order. studentID is Integer wrapper class for same reason.
	 * @param n		Name of student.
	 * @param g		Grade of student.
	 * @param sID	ID of student.
	 */
	
	public Student(String n, int g, String sID){
		name = n;
		grade = (Integer) g;
		studentID = sID;
	}
	


	/**
	 * Copy Constructor
	 * @param other	Student to be copied.
	 */
	public Student(Student other) {
		name = other.name;
		grade = other.grade;
		studentID = other.studentID;
	}
	/**
	 * compareTo. Compares by size of studentID.
	 */
	
	/**
	 * Gets name of student
	 * @return name of student.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets grade of student
	 * @return grade of student.
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * Gets studentID of student
	 * @return studentID of student.
	 */
	public String getStudentID() {
		return studentID;
	}
	/**
	 * Gets Arrival reason of student
	 * @return Arrival reason of student.
	 */

	public String toString(){
		return  String.format("%-15s", studentID) + String.format("%-25s", name) +grade;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}





	@Override
	public int compareTo(Student o) {
		return toString().compareTo(o.toString());
	}
	


	
	
}
