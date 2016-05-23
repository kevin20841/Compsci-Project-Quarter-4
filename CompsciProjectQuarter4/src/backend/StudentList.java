

//TODO Start writing Javadoc
package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Container class for Student. 
 * Holds Students in two Hashmaps, one in &lt;name, Student&gt;, one in &lt;studentID, Student&gt;
 * @author	Kevin
 */
public class StudentList {

	private Map <String, Student> nameMap;
	private Map <String, Student> studentIDMap;
	
	
	/**
	 * No args- constructor. Creates empty StudentList
	 */
	public StudentList(){
		studentIDMap = new HashMap <String, Student>();
		nameMap = new HashMap <String, Student>();
	}
	/**
	 * Adds a student to the StudentList.
	 * @param	student The Student to add.
	 */
	public void add(Student student){
		studentIDMap.put(student.getStudentID(), student);
		nameMap.put(student.getName(), student);
	}
	

	/**
	 * Searches for Student in the StudentList by ID.
	 * @param	sID		The Student ID to be searched.
	 * @return	Returns the Student with that studentID
	 */
	public Student getStudentByID(String sID){
		return new Student(studentIDMap.get(sID));
	}
	
	/**
	 * Searches for Student in the StudentList by name.
	 * @param 	name	The name to be searched.
	 * @return	Returns the Student with that name.
	 */
	public Student getStudentByName(String name){
		return new Student(nameMap.get(name));
	}
	/**
	 * Searches for Student in the StudentList by toString value.
	 * @param 	name	The name to be searched.
	 * @return	Returns the Student with that name.
	 */
	public Student getStudentByToString(String s){
		return new Student(getStudentByID(s.split("\\s+")[0]));
	}
	/**
	 * Gets list of names.
	 * @return	Returns <code> ArrayList &lt;String&gt; </code> in lexigraphic order, all lowercase. 
	 */
	public ArrayList<String> getNameList(){
		ArrayList<String> res = new ArrayList<String>();
		res.addAll(nameMap.keySet());
		// sort(res, 0, res.size());
		return res;
	}
	

	/**
	 * Gets list of ID + name + grade. 
	 * @return	Returns <code> ArrayList &lt;String&gt;	 </code>  in numerical order,
	 */
	public ArrayList<String> getInfoList(){
		ArrayList<String> res = new ArrayList<String>();
		for (Student student: nameMap.values()){
			res.add(student.toString());
		}
		// sort(res, 0, res.size());
		return res;
	}
	/**
	 * Gets ordered list of IDs. 
	 * @return	Returns <code> ArrayList &lt;Integer&gt;	 </code>  in numerical order,
	 */
	public ArrayList<String> getIDList(){
		ArrayList<String> res = new ArrayList<String>();
		res.addAll(studentIDMap.keySet());
		// sort(res, 0, res.size());
		return res;
	}
	public ArrayList<Student> getStudentList(){
		return new ArrayList<Student>(nameMap.values());
		
	}
	
	public boolean contains(Student other){
		if (nameMap.values().contains(other)){
			return true;
		}
		return false;
	}
	public int size(){
		return nameMap.values().size();
	}
}
