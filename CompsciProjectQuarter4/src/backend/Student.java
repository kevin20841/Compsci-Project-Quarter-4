package backend;
/** 
 * Data class that holds information on each student. Holds name, studentID and grade. Is comparable, compares by studentID.
 * @author	Kevin
 */
public class Student implements Comparable{
	
	private String name;
	private int grade;
	private int studentID;
	private String arrivalReason;
	private String arrivalNote;
	private String earlyDepartureReason;
	private String EarlyDepartureNote;
	
	/**
	 *  Constructor. name is lower case so that compareTo for strings arranges by alphabetical order. studentID is Integer wrapper class for same reason.
	 * @param n		Name of student.
	 * @param g		Grade of student.
	 * @param sID	ID of student.
	 */
	
	public Student(String n, int g, int sID){
		name = n.toLowerCase();
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
	@Override
	public int compareTo(Object o) {
		return ((Student) o).getStudentID() - studentID;
	}
	
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
	public int getStudentID() {
		return studentID;
	}
	/**
	 * Gets Arrival reason of student
	 * @return Arrival reason of student.
	 */
	public String getArrivalReason() {
		return arrivalReason;
	}
	
	/**
	 * Gets arrival note location of student
	 * @return Arrival note location of student.
	 */

	public String getArrivalNote() {
		return arrivalNote;
	}

	/**
	 * Gets Reason for early departure of student
	 * @return Gets reason for early departure of student.
	 */
	
	public String getEarlyDepartureReason() {
		return earlyDepartureReason;
	}
	
	/**
	 * Gets early departure note location of student
	 * @return Early departure note location of student.
	 */
	public String getEarlyDepartureNote() {
		return EarlyDepartureNote;
	}


	
	
}
