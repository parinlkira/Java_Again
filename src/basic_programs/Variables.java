package basic_programs;

public class Variables {

	int a = 1233; // instance variable, inside class outside method
				// Needs object for accesssing it, since it is not 
				// instantiated(read as Created) for it
	
	static double b = 45.334; // Since it is a static variable it is 
							// class variable and doesn't need object 
							// to be created to access it
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Variables v = new Variables();
		
		// Local variable below
		boolean c = true;
		
		
		System.out.println("Value of a: " + v.a);
		System.out.println("Value of b: " + b );
		System.out.println("value of c: " + c);
		
	}

}
