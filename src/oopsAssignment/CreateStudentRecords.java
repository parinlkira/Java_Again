package oopsAssignment;

// 3. Write a program to find the records of students having greatest marks
public class CreateStudentRecords {
	public String[] name;
	public int[] marks;
	int num;
	
	public CreateStudentRecords(int num){
		this.num = num;
		marks = new int[num];
		name = new String[num];		
	}
	
	void disp() {
		
		System.out.println("Display all records below!");
		
		for(int i = 0; i < num; i++) {
			System.out.println("Name : " + name[i] + ", Id : " + marks[i]);
		}
	}
	
	
	int compare(int m1, int m2) {
		if(m1 > m2) {
			return m1;
		}
		else return m2;
	}
	
	void greaterMarks() {
		int max = 0, index = 0;
		for(int ind = 0; ind < num ;  ind++) {
			max = compare(max, marks[ind]);
			if(max == marks[ind]) {
				max = marks[ind];
				index = ind;
			}
			
		}
		
		System.out.println("Maximum Marks are : " + max + "of Student : " + name[index]);
	}

	
	
}
