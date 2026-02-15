package basic_programs;
import java.util.Scanner;

public class StudentRecords {

	public static void main(String[] args) {
		String name;
		int roll, m1, m2, m3, total;
		double per;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the roll no. : ");
		roll = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Enter the name of the Student: ");
		name = s.nextLine();
		
		System.out.println("Enter the marks of 3 subjects: ");
		m1= s.nextInt();
		m2= s.nextInt();
		m3 = s.nextInt();
		
		total = m1 + m2 + m3;
		System.out.println("Total Marks are : " + total);
		
		per = (m1 + m2 + m3)/3;
		
		System.out.println("Percentage is: " + per);
		
		s.close();
	}

}
