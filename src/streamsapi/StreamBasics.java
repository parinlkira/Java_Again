package streamsapi;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamBasics {
    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(10, 5, 20, 5, 30, 40, 10, 15, 2);

        List<String> words =  Arrays.asList("java", "spring", "aws", 
                            "java", "kafka", "spring");

        List<Employee> employees = Arrays.asList( 
    new Employee(1, "Alice", "IT", "Developer", 120000, 30),
    new Employee(2, "Bob", "IT", "Developer", 100000, 28),
    new Employee(3, "Charlie", "HR", "Manager", 90000, 35),
    new Employee(4, "David", "IT", "Architect", 150000, 40),
    new Employee(5, "Eva", "HR", "Recruiter", 70000, 27),
    new Employee(6, "Frank", "Finance", "Analyst", 110000, 32),
    new Employee(7, "Grace", "Finance", "Manager", 140000, 38),
    new Employee(8, "Henry", "IT", "Developer", 100000, 31)
        );

        //System.out.println(employees);

       // Easy Questions 

       //1. Find all even numbers
       List<Integer> evenNumbers = nums.stream()
                                        .filter(n -> n % 2 == 0)
                                        .toList();
        System.out.println("Even Numbers: " + evenNumbers);
                                        
       //2. Find all odd numbers
       List<Integer> oddNumbers = nums.stream()
                                       .filter(n -> n % 2 != 0)
                                       .toList();
        System.out.println("Odd Numbers: " + oddNumbers);
       
        // 3. Find all numbers greater than 20
        
        List<Integer> numsMoreThanTwenty = nums.stream()
        								.filter( n -> n > 20)
        								.collect(Collectors.toList());
        System.out.println("Numbers Greater than 20: " + numsMoreThanTwenty );
        
       // 4. Find numbers divisible by 5
        List<Integer> numsBy5 = nums.stream()
        	    .filter(n -> n % 5 == 0)
        	    .collect(Collectors.toList());
        
       System.out.println("Numbers divisible by 5: " + numsBy5 );
        								
       //5. Square every number
       List<Integer> numsSquared = nums.stream()
    		    .map(n -> n * n)
    		    .collect(Collectors.toList());
       
       System.out.println("Numbers Squared: " + numsSquared );
       
       //6. Convert words to uppercase
       
       List<String> wordstoUpper = words.stream()
    		   		.map(String::toUpperCase)
    		   		.toList();
       
       System.out.println("Words to UpperCase: " + wordstoUpper );
       
       //7. Find words starting with "s"
       
       List<String> wordsS = words.stream()
    		   				.filter(s -> s.startsWith("s"))
    		   				.toList();
       
       System.out.println("Words starting with s: " + wordsS );
       
       // 8. Find words longer than 4 characters
       
       List<String> len = words.stream()
    		   			.filter( s -> (s.length() > 4))
    		   			.toList();
       System.out.println("Words longer than 4: " + len);
       
       // 9. Remove duplicate numbers
       
       List<Integer> dup = nums.stream().distinct().toList();
       System.out.println("Remove duplicate numbers: " + len);
       
       // 12. Find maximum number ( For Min replace with min())
       
       int maxNum = nums.stream()
    		   		.mapToInt(Integer::intValue)
    		   		.max()
    		   		.orElseThrow(NoSuchElementException::new);
       
       System.out.println("Find max Number: " + maxNum);
       
       // Can also write it as below
       // For min replace with min() or max((a,b) -> b - a)) 
       
       int maxNum2 = nums.stream()
    		   		.max((a,b) -> a - b)
    		   		.get();
       
       System.out.println("Find max Number Way two: " + maxNum2);
       
       // 14. Find sum
       
       int total = nums.stream()
    		   .mapToInt(Integer::intValue)
    		   .sum();
       
       System.out.println("Sum: " + total);
       
       // using reduce
       
       int total2 = nums.stream()
    		   		.reduce( 0 , (a, b) -> a + b);
    	
       System.out.println("Sum using reduce: " + total2);
       
       // 15. Find average
       
       double numavg = nums.stream()
    		   		.mapToInt(Integer::intValue)
    		   		.average()
    		   		.orElse(0.0);
       
       System.out.println("Average is: " + numavg);
       
       
       // 21. Find first word starting with "k"
       
       String wordk = words.stream()
    		   			.filter( s -> s.startsWith("k"))
    		   			.findFirst()
    		   			.orElse("No Word Found");
       
       System.out.println("Output of first word with k: " + wordk);
       
       // 22. Convert a list to a Set
       
       Set<Integer> listToSet = nums.stream()
    		   					.collect(Collectors.toSet());
       
       System.out.println("Converting list to set: " + listToSet);
       
       //  If encounter order matters
       Set<Integer> listToSet2 = nums.stream()
    		   				.collect(Collectors.toCollection(LinkedHashSet::new));
       
       System.out.println("Converting list to set: " + listToSet2);
       
       // 23. Join words with commas
       
       String s1 = words.stream()
    		   	.collect(Collectors.joining(","));
       
       System.out.println("Join words with commas: " + s1);
       
       // 24. Find longest word 
       
       String longestWord = words.stream()
    		   	.max(Comparator.comparingInt(String::length))
    		   	.orElse("Empty String");
       
       System.out.println("Find longest word: " + longestWord);
       
       // 25. Find Shortest word 
      String shortWord = words.stream()
    		  		.min(Comparator.comparingInt(String::length))
    		  		.orElse("No String Found");
      
      System.out.println("Finding shortest word: " + shortWord);
      
      // 27. Count words equal to "java"
      
      long c1 = words.stream()
    		  	.filter("java"::equals)
    		  	.count();
      
      System.out.println("Counting words equal to java: " + c1);
   
      // 28. Find duplicate numbers
      
      // This below one is a difficult way using streams

      // 1. Group numbers by their identity and count occurrences
      // 2. Convert the resulting map to a set of entries
      // 3. Filter entries with count greater than 1
      // 4. Extract the keys (duplicate numbers) and collect them into a set
      Set<Integer> duplicates = nums.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter( e ->  e.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());

      System.out.println("Duplicate numbers: " + duplicates);

      // Easier way to find duplicate numbers

      Set<Integer> seen = new HashSet<>();

      Set<Integer> duplicatesEasier = nums.stream()
        .filter( n -> !seen.add(n))
        .collect(Collectors.toSet());
      
      System.out.println("Duplicate numbers (easier way): " + duplicatesEasier);
      
      // 30. Find numbers appearing exactly once

    }  
    
}
