package arrays;

import java.util.Scanner;

public class Arrays1 {

	public static void main(String[] args) {

		/*
		int[] a = new int[5];
		
		int p=0, max = 0, i;
		
		System.out.println("Enter the 5 numbers");
		
		Scanner sc = new Scanner(System.in);
		
		for(i = 0; i < 5; i++) {
			a[i] = sc.nextInt();
		}

		for(i = 0; i < 5; i++) {
			if(max < a[i]) {
				max = a[i];
				p = i;
			}
		}
		
		System.out.println("Maximum element"+ max +  " is at: " + p );
		
		sc.close();
		
		
		int a1, b, c, ch;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter a and b - ");
		a1 = s.nextInt(); b = s.nextInt();
		
		System.out.println("Select the operation to perform - ");
		
		ch = s.next().charAt(0);
		

		switch(ch) {
		case '+' :
			c = a1 + b;
			System.out.println("Addition is : " + c);
			break;
		case '-' :
			c = a1 - b;
			System.out.println("SUbtraction is : " + c);
			break;
		case '*' :
			c = a1 * b;
			System.out.println("Mult is : " + c);
			break;
		case '/' :
			c = a1 / b;
			System.out.println("Div is : " + c);
			break;
		default :
			System.out.println("Wrong Choice");
			break;
			
			
		}
		*/
			
		// Cmd + Shft + F will format the code nicely
		
		int i = 5, j = 3, k = 4, max;

		max = (i > j ? (i > k ? i : k) : (j > k ? j : k));

		System.out.println("Max of 3 is : " + max);
	}

}
