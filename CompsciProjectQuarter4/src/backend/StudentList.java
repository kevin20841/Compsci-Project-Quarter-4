package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	//returns ordered names TODO
	public ArrayList<String> getNameList(){
		ArrayList<String> res = new ArrayList<String>();
		res.addAll(nameMap.keySet());
		sort(res, 0, res.size());
		return res;
	}
	//returns ordered ID's TODO 
	public ArrayList<Integer> getIDList(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.addAll(studentIDMap.keySet());
		sort(res, 0, res.size());
		return res;
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
