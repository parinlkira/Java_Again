package streamsapi;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsHardTwo {

	public static void main(String[] args) {
		
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
		
	    
	    // 84.Find top 3 distinct salaries per department
		
	    Map<String, List<Integer>> top3SalPerDept = employees.stream()
	    	    .collect(Collectors.groupingBy(
	    	        Employee::getDepartment,
	    	        Collectors.collectingAndThen(
	    	            Collectors.mapping(Employee::getSalary, Collectors.toSet()),
	    	            set -> set.stream()
	    	                .sorted(Comparator.reverseOrder())
	    	                .limit(3)
	    	                .collect(Collectors.toList())
	    	        )
	    	    ));
		
		System.out.println("84.Find top 3 distinct salaries per department: \n" 
				+ top3SalPerDept);
		
		// 85.Find the department's highest-paid employee name
		
		Map<String, String> highPerDept = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.collectingAndThen(
						Collectors.maxBy(Comparator
								.comparingDouble(Employee::getSalary)),
						opt -> opt.map(Employee::getName)
							.orElse("None Found")	
						)
						));
		
		System.out.println("85.Find the department's highest-paid employee name: "
				+ highPerDept);
		
		// 86. Find departments where average salary exceeds 100000
		
		List<String> deptsAvgMoreThan10K = employees.stream()
				.collect(Collectors.groupingBy(
					Employee::getDepartment,
					Collectors.averagingDouble(Employee::getSalary)				
						))
				.entrySet()
				.stream()
				// .peek(System.out::print) // Just to peek data
				.filter( b -> b.getValue() > 100000)
				.map(Map.Entry::getKey)
				.toList();
		
		System.out.println("86. Find departments where average salary exceeds 100000: "
				+ deptsAvgMoreThan10K);
		
		
		// 87. Find the employee with the highest salary in the entire company
		
		String hiEmp = employees.stream()
				.max(Comparator.comparing(Employee::getSalary))
				.map(Employee::getName)
				.orElse("None Found");
		
		System.out.println("87. Find the employee with the highest salary "
				+ "in the entire company" + hiEmp);
		
		
		// 88. Find all employees sharing the highest salary
		
		double maxsal = employees.stream()
				.mapToDouble(Employee::getSalary)
				.max()
				.orElse(0);
		
		List<Employee> emplMax = employees.stream()
				.filter( e -> e.getSalary() == maxsal)
				.collect(Collectors.toList());
		
		System.out.println("88. Find all employees sharing the highest salary: " + emplMax);
		
		// 89. Find all employee names appearing more than once 
		
		List<String> namesDup = employees.stream()
				.collect(Collectors.groupingBy(Employee::getName, 
						Collectors.counting()))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() > 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		
System.out.println(" 89. Find all employee names appearing more than once: " + namesDup);
		
   // 90. Find the first non-repeated character in a String

	String s = "swiss";
	
	String firstNonRepChar = s.chars()
			.mapToObj( c -> (char) c)
			//.peek(System.out::print)
			.collect(Collectors.groupingBy(
					Function.identity(),
					LinkedHashMap::new,    // preserves seen order
					Collectors.counting()
					))
			.entrySet()
			.stream()
			//.peek(System.out::println)
			.filter(a -> a.getValue() == 1)
			.map(Map.Entry::getKey)
			.findFirst()
			.map(String::valueOf)
			.orElse("None Found");
	
	System.out.println("90. Find the first non-repeated character: " + firstNonRepChar);


  // 91. Find the first repeated character

	Set<Character> seen = new HashSet<>();
	
	Character firstRepChar = s.chars()
			.mapToObj(c -> (char) c)
			.filter(a -> !(seen).add(a))
			.findFirst()
			.orElse(null);
	
	System.out.println("91. Find the first repeated character: " + firstRepChar);

 // 92. Find the most frequent character
	
	String s1 = "programming";
	
	Optional<Map.Entry<Character, Long>> mostFreqChar = 
			s1.chars()
			.mapToObj(c -> (char) c)
			.collect(Collectors.groupingBy(
					Function.identity(),
					LinkedHashMap::new,
					Collectors.counting()
					))
			.entrySet()
			.stream()
			.max(Map.Entry.comparingByValue());
	
	System.out.println("92. Find the most frequent character: " + mostFreqChar);
	
 //93. Find the longest word in a sentence
	
   String s2 = "Java Spring Boot makes backend development productive";
   
   String longest = Arrays.stream(s2.split("\\s+"))
		   //.peek(System.out::print)
		   .max(Comparator.comparing(String::length))
		   .orElse("None");
   
   System.out.println("93. Find the longest word in a sentence: " + longest);
   
 //94. Count words in a sentence
	
 String s3 = "Java Spring Java AWS Spring";
 
 long countWordSent = Arrays.stream(s3.trim().split("\\s+"))
		 .filter(a -> !a.isEmpty())
		 .count();
 System.out.println("94. Count words in a sentence: " + countWordSent);
 
 // 95.Find duplicate words in a sentence
 
 List<String> dupWords = Arrays.stream(s3.split("\\s+"))
		 .collect(Collectors.groupingBy(
				 Function.identity(),
				 Collectors.counting()
				 ))
		 .entrySet()
		 .stream()
		 .filter( a -> a.getValue() > 1)
		 .map(Map.Entry::getKey)
		 .collect(Collectors.toList());
 
 System.out.println("95.Find duplicate words in a sentence: " + dupWords);
 
 
  //96.Find the highest-paid employee per department and return only names
 
  Map<String, String> hiPerDept = employees.stream()
		  .collect(Collectors.groupingBy(
				  Employee::getDepartment,
				  Collectors.collectingAndThen(
				  Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
				  		e -> e.map(Employee::getName).orElse("")
				  )
				  ));

  System.out.println("96.Find the highest-paid employee "
  		+ "per department and return only names: " + hiPerDept);
 
 
 //97. Find the department with the youngest average employee age
  
  String deptYoungAgeAverage = employees.stream()
		  .collect(Collectors.groupingBy(
				  Employee::getDepartment,
				  Collectors.averagingInt(Employee::getAge)
				  ))
		  .entrySet()
		  .stream()
		  .min(Map.Entry.comparingByValue())
		  .map(Map.Entry::getKey)
		  .orElse(null);
		  
  System.out.println("97.Find the department with "
  		+ "the youngest average employee age: " + deptYoungAgeAverage);
 
  //98. Find employees whose salary is greater than their department average
  
  Map<String, Double> avgSalary = employees.stream()
		  .collect(Collectors.groupingBy(
				  Employee::getDepartment,
				  Collectors.averagingDouble(Employee::getSalary)
				  ));
  
  List<String> avgSalGreatThanDept = employees.stream()
		  .filter( e -> e.getSalary() > avgSalary.get(e.getDepartment()))
		  .map(Employee::getName)
		  .collect(Collectors.toList());
 
  System.out.println("98. Find employees whose salary is "
  		+ "greater than their department average: " + avgSalGreatThanDept);
  
  // 99. Find the second-highest employee salary and all employees having it
  
  Optional<Integer> secHi = employees.stream()
		 .map(Employee::getSalary)
		 .sorted(Comparator.reverseOrder())
		 .distinct()
		 .skip(1)
		 .findFirst();
	
  List<String> empNamesWithSecHiSal = secHi
		  .map(
				a -> employees.stream()
				.filter( e -> e.getSalary() == a)
				.map(Employee::getName)
				.collect(Collectors.toList())
			)
		  .orElse(Collections.emptyList());
  
  System.out.println("99. Find the second-highest employee"
  		+ " salary and all employees having it " + empNamesWithSecHiSal);
 

	// 100. Move Zeroes to the end of the list while maintaining 
	// the order of non-zero elements

	List<Integer> numbers = Arrays.asList(1, 0, 2, 0, 3, 0, 4);
	List<Integer> movedZeroes = Stream.concat(
			numbers.stream().filter(n -> n != 0),
			numbers.stream().filter(n -> n == 0)
	).collect(Collectors.toList());

	System.out.println("100. Move Zeroes to the end: " + movedZeroes);

	// Another approach for the same problem using partitioningBy

	Map<Boolean, List<Integer>> partitioned = numbers.stream()
			.collect(Collectors.partitioningBy(n -> n != 0));

	List<Integer> movedZeroesAlt = Stream.concat(
			partitioned.get(true).stream(),
			partitioned.get(false).stream()
	).collect(Collectors.toList());

	System.out.println("100. Move Zeroes to the end (Alternative): " + movedZeroesAlt);

	// What if the input was array of numbers instead of a list?
	int[] numArray = {0,0,0,0};
	List<Integer> movedZeroesFromArray = Stream.concat(
			Arrays.stream(numArray).boxed().filter(n -> n != 0),
			Arrays.stream(numArray).boxed().filter(n -> n == 0)
	).collect(Collectors.toList());

	System.out.println("100. Move Zeroes to the end (from array): " + movedZeroesFromArray);
	
	// What if I wanted to return the output as an Array as well
	int[] movedZeroesArray = Stream.concat(
			Arrays.stream(numArray).boxed().filter(n -> n != 0),
			Arrays.stream(numArray).boxed().filter(n -> n == 0)
	).mapToInt(Integer::intValue).toArray();

	System.out.println("100. Move Zeroes to the end (as array): " + Arrays.toString(movedZeroesArray));
				
	// Method Ends here
	}

}
