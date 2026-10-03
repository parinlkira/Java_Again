package mustKnowStreamsQuest;

import java.util.stream.IntStream;


public class StreamsNumsPlay {

	public static void main(String[] args) {
	
	
	// Generate Prime Numbers from 1 to 20
	IntStream.rangeClosed(1,20)
		.filter(StreamsNumsPlay::isPrime)
		.forEach(System.out::println);
		
	
	// Generate Armstrong Numbers from 10 to 1000
	
	IntStream.rangeClosed(10, 1000)
	.filter(StreamsNumsPlay::isArmstrong)
	.forEach(System.out::println);
	
	// Main Method Ends Here
		 
	}
	
	// isPrime Method
	
	static boolean isPrime(int n) {
		
		if (n < 2) return false;
		
		return IntStream.rangeClosed(2,
				(int) Math.sqrt(n))
				.noneMatch(i -> n % i == 0);
		
	}

	// isArmstrong Method
	
	static boolean isArmstrong(int n) {
		
		String number = String.valueOf(n);
		int digits = number.length();
		
		int numbers = number.chars()
				.map(c -> c - '0')
				.map(d -> (int) Math.pow(d, digits))
				.sum();
		
		return numbers == n;
	}
	
}


