

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
	private Map <Integer, Student> studentIDMap;
	
	
	/**
	 * No args- constructor. Creates empty StudentList
	 */
	public StudentList(){
		studentIDMap = new HashMap <Integer, Student>();
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
	public Student getStudent(int sID){
		return new Student(studentIDMap.get(sID));
	}
	
	/**
	 * Searches for Student in the StudentList by name.
	 * @param 	name	The name to be searched.
	 * @return	Returns the Student with that name.
	 */
	public Student getStudent(String name){
		return new Student(nameMap.get(name));
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
	public ArrayList<Integer> getIDList(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.addAll(studentIDMap.keySet());
		// sort(res, 0, res.size());
		return res;
	}
	public ArrayList<Student> getStudentList(){
		return (ArrayList<Student>) nameMap.values();
		
	}
	/**
	 * Generic quicksort. 
	 * @param	data	ArrayList of Comparable objects
	 * @param 	a		Started pointer in quicksort
	 * @param	b		End pointer in quicksort
	 */
	private <T extends Comparable<T>> void sort(ArrayList<T> data, int a, int b) {
        if (a < b) {
            int i = a, j = b;
            T x = data.get((i + j) / 2);

            do {
                while (data.get(i).compareTo(x) < 0) {
                	i++;
                }
                while (x.compareTo(data.get(j)) < 0) {
                	j--;
                }

                if ( i <= j) {
                	T tmp = data.get(i);
                	data.set(i, data.get(j));
                	data.set(j,tmp);
                    i++;
                    j--;
                }

            } while (i <= j);

            sort(data, a, j);
            sort(data, i, b);
        }
    }
}
