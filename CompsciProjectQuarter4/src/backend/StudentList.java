package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//TODO Implement Binary Search, and Quicksort.  
public class StudentList {
	private Map <String, Student> nameMap;
	private Map <Integer, Student> studentIDMap;
	
	//No-args constructor
	public StudentList(){
		studentIDMap = new HashMap <Integer, Student>();
		nameMap = new HashMap <String, Student>();
	}
	//Add to StudentList
	public void add(Student student){
		studentIDMap.put(student.getStudentID(), student);
		nameMap.put(student.getName(), student);
	}
	

	//Search functions
	public Student getStudentData(int sID){
		return new Student(studentIDMap.get(sID));
	}
	
	public Student getStudentData(String name){
		return new Student(nameMap.get(name));
	}
	
	//Quicksorts the studentList
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
