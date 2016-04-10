package backend;

import java.util.ArrayList;
//TODO Implement Binary Search, and Quicksort.  
public class studentList {
	private ArrayList <Student> data = new ArrayList <Student>();
	
	public Student getStudentData(int studentID){
		return null;
	}
	
	public Student getStudentData(String name){
		return null;
	}
	private Student BinarySearch(Object student){
		return null;
	}
	
	//Quicksorts the studentList
	public void sort(int a, int b) {
        if (a < b) {
            int i = a, j = b;
            Student x = data.get((i + j) / 2);

            do {
                while (data.get(i).compareTo(x) < 0) i++;
                while (x.compareTo(data.get(j)) < 0) j--;

                if ( i <= j) {
                	Student tmp = data.get(i);
                	data.set(i, data.get(j));
                	data.set(j,tmp);
                    i++;
                    j--;
                }

            } while (i <= j);

            sort( a, j);
            sort( i, b);
        }
    }
}
