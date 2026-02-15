package arrays;

public class StringsAssign {

	static void stringReverse(String str) {
		
		StringBuilder s = new StringBuilder(str);
		
		s = s.reverse();
		
		System.out.println("Reversed String is: " + s.toString());
				
	}
	
	
	public static void main(String[] args) {
		
		String str = "My Unreversed String!";
		
		stringReverse(str);
		
		int l = 0, r = str.length() - 1;
		String rev = ""; 
		
		for(; r > 0; r--) {
			rev = rev + str.charAt(r);
		}
		
		System.out.println("Reversed String without rev: " + rev);

		
	}

}
