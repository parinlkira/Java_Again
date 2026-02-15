package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArrayAssign {

	public static int sumArr(int[] arr) {
		
		int sum = 0;
		
		for (int it : arr) {
			sum = sum + it;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1,3,4,3,2,4};
		
		System.out.println("Sum is : "+ sumArr(arr));

//		List<String> a = new ArrayList<>();
// 
//		System.out.println("Enter list elements one by one.");
//        System.out.println("Type 'd' and press Enter when you are finished.");
//		Scanner s = new Scanner(System.in);
//				
//		while(true) {
//			
//			String inp = s.nextLine();
//			if(inp.equals("d")) {
//				break;
//			}
//			a.add(inp);
//		}
//		
//		System.out.println(a);
//		
//		for(int b = 0; b < a.size(); b=b+2) {
//			System.out.println(a.get(b) + "\t");
//		}
//	}

		int[] a = {1,3,4,5,6,4,5,3,2,4,4}, b = {2,4,5,3,3,4,4,4}, c;
		
		int n = a.length, m = b.length;
		c = new int[n+m];
		
		for(int i = 0; i < n ; i++) {
			c[i] = a[i];
		}
		for(int d : c) {
			System.out.print(d + "  ");
		}
		
		System.out.println();
		for(int i = 0; i < m ; i++) {
			c[i+n] = b[i];
		}
		for(int d : c) {
			System.out.print(d + "  ");
		}
		
		System.out.println();
	
		int count = 0, val = 4;
		
		for(int j = 0; j < c.length; j++) {
			if ( c[j] == val) {
				System.out.println(val + " is at " + j + " position");
				count++;
			}
		}
		System.out.println("and it occurs " +count+ " times");
		
	}
}

