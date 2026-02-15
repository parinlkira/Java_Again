package basic_programs;

public class StarPattern {

	public static void main(String[] args) {
		
		int r,c;
		
		for(r = 1; r <= 5; r++) {
					
			
			for(c= 1; c<=r ; c++) {
				System.out.print("*");
			}
			
			System.out.println("");
			
		}
		
		/*
		*
		**
		***
		****
		*/
		
		System.out.println("\n-------------------------\n");
		r=5;
		while(r>=1)
		{
			c=1;
			while(c<=r) {
				System.out.print("*");
				c++;
			}
			
			System.out.println();
			r--;
		}
		
		/*
		*****
		****
		***
		**
		*
		*/
		
		System.out.println("\n-------------------------\n");
		
		for(r = 1; r <= 5; r++) {
					
			for(int k = 4; k >= r; k-- ) {
				
				System.out.print(" ");
				
			}
			
			for(c= 1; c<=r ; c++) {
				System.out.print("*");
			}
			
			System.out.println();
			
		}

		
//	     *
//	    **
//	   ***
//	  ****
//	 *****

		System.out.println("\n-------------------------\n");
		
		for(r = 1; r <= 5; r++) {
					
			for(int k = 4; k >= r; k-- ) {
				
				System.out.print(" ");
				
			}
			
			for(c= 1; c<=r ; c++) {
				System.out.print("*");
				System.out.print(" ");
			}
			
			System.out.println();
			
		}
	
		
		/*
		 * 
	    * * 
	   * * * 
	  * * * * 
	 * * * * * 
		*/
	}

}
