package streamsapi;
import java.util.*;
import java.util.stream.Collectors;


public class StreamsHard {

	public static void main(String[] args) {
		
	    List<Integer> nums = Arrays.asList(10, 5, 20, 5, 30, 40, 10, 15, 2);

	    List<String> words =  Arrays.asList("java", "spring", "aws", 
	                            "java", "kafka", "spring", "level", "madam");

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
	    
	    List<List<Integer>> nested = Arrays.asList(
	    		Arrays.asList(1,2),
	    		Arrays.asList(3,4),
	    		Arrays.asList(5,6),
	    		Arrays.asList(4,3),
	    		Arrays.asList(6,6)
	    		);
	    
	    
	    
	  // 64. Sort a Map by value
	    
	    HashMap<String, Integer> map = new HashMap<>();
	    map.put("A", 30);
	    map.put("B", 10);
	    map.put("C", 20);
	    
	    Map<String, Integer> sortByVal = map.entrySet()
	    		.stream()
	    		.sorted(Map.Entry.comparingByValue())
	    		.collect(Collectors.toMap(
	    				Map.Entry::getKey,
	    				Map.Entry::getValue,
	    				(a,b) -> a,
	    				LinkedHashMap::new
	    				));
	    
		System.out.println("SortedMap by Value: " + sortByVal);
		
	  //65. Sort a Map by key descending
		
		Map<String, Integer> sortByKey = map.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(a, b) -> a,
						LinkedHashMap::new
						));
		System.out.println("SortedMap by Key: " + sortByKey);
		
	  //66. Flatten List<List<Integer>>	
		
		List<Integer> flat = nested.stream()
				.flatMap(Collection::stream)
				.toList();
		
		System.out.println("Flattened List: " + flat);
		
	 //	67. Find unique numbers in nested lists	
		
		List<Integer> flatUniq = nested.stream()
				.flatMap(Collection::stream)
				.distinct()
				.collect(Collectors.toList());
		
		System.out.println("Unique Nums in Flattened List: " + flatUniq);
		
		// 68. Sum all numbers in nested lists
		
		int sumNest = nested.stream()
				.flatMapToInt( l -> l.stream().mapToInt(Integer::intValue))
				// .peek(e -> System.out.print(e))
				.sum();
		
		System.out.println("Summing all Nested list nums: " + sumNest);
		
		// 69. Find maximum number in nested lists
		
		int maxNested = nested.stream()
				.flatMapToInt( list -> list.stream().mapToInt(Integer::intValue))
				.max()
				.orElse(0);
		
		System.out.println("Max of all Nested list nums: " + maxNested);
		
		// 70. Convert List<Employee> to Map<Department,List<String>>
		
		Map<String, List<String>> dataListToMap = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.mapping(Employee::getName, Collectors.toList())
						));
		
		System.out.println("Convert List<Employee> to Map<Department,List<String>>: "
		+ dataListToMap);
		
		// 71. Find employees whose salary is above average
		
		double avg = employees.stream()
				.mapToDouble(Employee::getSalary)
				.average()
				.orElse(0);
		
		List<String> nameAboveAvg = employees.stream()
				.filter( c -> c.getSalary() > avg)
				.map(Employee::getName)
				.collect(Collectors.toList());
		
		System.out.println("employees whose salary is above average: " + nameAboveAvg);
		
		// 72. Find oldest employee
		
		Employee oldest = employees.stream()
				.max(Comparator.comparing(Employee::getAge))
				.orElse(null);
		
		System.out.println("oldest employee :" + oldest);
		
		// 73. Find youngest employee in each department
		
		Map<String, Optional<Employee>> youngPerDept = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.minBy(
						Comparator.comparing(Employee::getAge)
								)
						));
		
		youngPerDept.forEach((dept, empList) -> {
			
		 System.out.print("Department: " + dept + "-> [[");
		 
		 String empNames = empList.stream()
		 				.map(Employee::getName)
		 				.collect(Collectors.joining(", "));
		 System.out.println(empNames + " ]]");
		});
		
		// 74. Group employees by age and give age ->  name and no for each loop
		// using streams only
		
		// First Way to Do It 
		
		/* Note : only caveat is in case of duplicate key -
		in this case the age, i.e. Emp A -> 32, Emp B -> 32
		It will overwrite one of the age and give only 
		Emp A or B Value and not 32 -> {Emp A, Emp B}
		Due to the definition/return type - Map<Int, String> 
		*/
		
		Map<Integer, String> agePerName = employees.stream()
				.collect(Collectors.toMap(
						Employee::getAge,
						Employee::getName,
						(dup, exist) -> dup
						));
				
		System.out.println("Group employees by age and give age -> name: "
		+ agePerName);
		
		// Second Way to Do it
		
		Map<Integer, List<String>> agePerName2 = employees.stream()
					.collect(Collectors.groupingBy(
							Employee::getAge,
							Collectors.mapping(Employee::getName, Collectors.toList())
							));
		
		System.out.println("Group employees by age and "
				+ "give age -> name by Method 2: "
				+ agePerName2);			
					
					
					
		// Method Ends Here

	}

}
