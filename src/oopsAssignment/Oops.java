package oopsAssignment;

import java.util.Scanner;

// 1. Write a Java program using class which contains two variables
// of type integer. Create and initialize the object 
// using parameterized constructor. Write
// a method to display maximum from given two numbers for all objects.


class MaxOf2Nums{
	
	int a, b;
	
	MaxOf2Nums(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	int max() {
		return Math.max(a, b);
	}

}

// 2. Write a program to perform all the arithmetic operations between two numbers.

class Arith extends MaxOf2Nums{
	
	Arith(int a, int b){
		super(a, b);
	}
	
	int add() {
		int sum = a+b;
		
		return sum;
	}
	
	int sub() {
		int diff = a - b;
		return diff;
	}
	
    int mul() {
    	int prod = a * b;
    	return prod;
    }

    int div() {
    	int div = a / b;
    	return div;
    }
}

// 3. Write a program to find the records of students having greatest marks

class Student {
	String name;
	int marks;
	
	// Parameterized constructor with name and marks
	Student(String name, int marks) {
		this.name = name;
		this.marks = marks;
	}
	
	// Method to display student record
	void display() {
		System.out.println("Name: " + name + ", Marks: " + marks);
	}
}

class StudentRecords {
	Student[] students;
	int numStudents;
	
	// Constructor to initialize array size
	StudentRecords(int numStudents) {
		this.numStudents = numStudents;
		this.students = new Student[numStudents];
	}
	
	// Method to add student record
	void addStudent(int index, String name, int marks) {
		students[index] = new Student(name, marks);
	}
	
	// Method to display all student records
	void displayAllRecords() {
		System.out.println("\nAll Student Records:");
		for(int i = 0; i < numStudents; i++) {
			students[i].display();
		}
	}
	
	// Method to find and display student with greatest marks
	void findStudentWithGreatestMarks() {
		int maxMarks = students[0].marks;
		int maxIndex = 0;
		
		// Find the student with maximum marks
		for(int i = 1; i < numStudents; i++) {
			if(students[i].marks > maxMarks) {
				maxMarks = students[i].marks;
				maxIndex = i;
			}
		}
		
		// Display the student with greatest marks
		System.out.println("\nStudent with greatest marks:");
		students[maxIndex].display();
	}
}

public class Oops {
	
	public static void main(String[] args) {
		
		Arith m = new Arith(32,30);
		
		System.out.println("Max of Nums is : " + m.max());
		
		System.out.println("Sum of Nums is : " + m.add());
		
		System.out.println("Diff of Nums is : " + m.sub());
		
		System.out.println("Mult of Nums is : " + m.mul());
		
		System.out.println("Div of Nums is : " + (double)m.div());
		
		Scanner sc = new Scanner(System.in);
		
	
		// Student records program - Approach 1: StudentRecords class
		System.out.println("\n--- Student Records (StudentRecords Class) ---");
		
		System.out.print("Enter number of students: ");
		int numStudents = sc.nextInt();
		sc.nextLine(); // consume newline
		
		StudentRecords sr = new StudentRecords(numStudents);
		
		// Input student data using parameterized constructor
		for(int i = 0; i < numStudents; i++) {
			System.out.print("Enter name of student " + (i + 1) + ": ");
			String name = sc.nextLine();
			System.out.print("Enter marks of " + name + ": ");
			int marks = sc.nextInt();
			sc.nextLine(); // consume newline
			sr.addStudent(i, name, marks);
		}
		
		// Display all entered records
		sr.displayAllRecords();
		
		// Find and display student with greatest marks
		sr.findStudentWithGreatestMarks();
		
		
		// Student records program - Approach 2: CreateStudentRecords class
		System.out.println("\n--- Student Records (CreateStudentRecords Class) ---");
		
		System.out.print("Enter number of students: ");		
		int num = sc.nextInt();
		sc.nextLine();
		
		CreateStudentRecords st = new CreateStudentRecords(num);
		
		for(int i = 0; i < num ; i++) {
			System.out.print("Enter name of student " + (i + 1) + ": ");
			st.name[i] = sc.nextLine();
			System.out.print("Enter marks of " + st.name[i] + ": ");
			st.marks[i] = sc.nextInt();
			sc.nextLine(); // consume newline
		}
		
		st.disp();
		st.greaterMarks();

		System.out.print("\nEnd Code!");		
		sc.close();
		
	}

}
