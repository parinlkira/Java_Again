package mustKnowStreamsQuest;
import java.util.*;
import java.util.stream.Collectors;

public class SentenceTransformer {

	public static void main(String[] args) {
	
	String sentence = "hello, world. this is java!";
	
	String result = Arrays.stream(sentence.split("\\s+"))
			.map(word -> word.substring(0,1).toUpperCase() 
					+ word.substring(1))
			.collect(Collectors.joining(" "));
					
	result = "#" + result;			
					
	System.out.println(result);
	
	// General Case when sentence is as below 
	
	String sentence1 = "hello, ...world. this is java!";	
	
	String result1 = Arrays.stream(sentence.split("\\s+"))
             .map(SentenceTransformer::capitalizeFirstLetter)
             .collect(Collectors.joining(" "));

     result1 = "#" + result1;

     System.out.println(result1);
	
	// Main Method ends here 
	}
	
	static String capitalizeFirstLetter(String word) {
		
		for(int i = 0; i < word.length(); i++) {
			
			if( Character.isLetter(word.charAt(i))) {
				
				return word.substring(0,i)
						+ Character.toUpperCase(word.charAt(i))
						+ word.substring(i + 1);
			}
				
		}
		return word;	
	}

}
