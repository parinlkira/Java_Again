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
	    
	    List<Employee> employees2 = Arrays.asList(
	    new Employee(1, "Alice", "IT", "Developer", 120000, 30, "Developer"),
	    new Employee(2, "Bob", "IT", "Developer", 100000, 28, "Developer"),
	    new Employee(3, "Charlie", "HR", "Manager", 90000, 35, "Manager"),
	    new Employee(4, "David", "IT", "Architect", 150000, 40, "Architect"),
	    new Employee(5, "Eva", "HR", "Recruiter", 70000, 27, "Recruiter"),
	    new Employee(6, "Frank", "Finance", "Analyst", 110000, 32, "Analyst"),
	    new Employee(7, "Grace", "Finance", "Manager", 140000, 38, "Manager"),
	    new Employee(8, "Henry", "IT", "Developer", 100000, 31, "Developer")
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
					

		// 75. Group employees by department and designation

		Map<String, Map<String, List<Employee>>> deptDesigMap = employees2.stream()
						.collect(Collectors.groupingBy(
							Employee::getDepartment,
							Collectors.groupingBy(Employee::getDesignation)
						));
		
		deptDesigMap.forEach((dept, desigMap) -> {
			System.out.print("Department: " + dept + " -> ");			
			desigMap.forEach((desig, empList) -> {
				System.out.print( desig + ", ");
			});
			System.out.println(); // Move to the next line after each department
		});

		
		// 76. Count employees by department and designation
		
		Map<String, Map<String, Long>> countDeptDesig = employees2.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.groupingBy(
								Employee::getDesignation,
								Collectors.counting())
						));
		
		System.out.println("Count employees by department \n"
				+ "and designation: " + countDeptDesig);
		
		// 77. Find employees with names starting with A
		
		List<Employee> nameWithA = employees.stream()
				.filter( a -> a.getName().startsWith("A"))
				.toList();
		
		System.out.println("Name Starts with A: " + nameWithA);
		
		// 78. Get comma-separated employee names and enclosed by [ ]
		
		String comma = employees.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(", ", "[", "]"));
		
		System.out.println("Comma and [] employees: " + comma);
		
		// 79. Find average age by department
		
		Map<String, Double> avgAgePerDept = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.averagingInt(Employee::getAge)
						));
		
		System.out.println("Average age per Dept: " + avgAgePerDept);
		
		
		// 80. Find all employees earning exactly the second-highest salary
		
		Optional<Integer> secHigh = employees.stream()
				.map(Employee::getSalary)
				.distinct()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst();
		
		System.out.println("Checking Optional Datatype: " + secHigh);
		
		List<String> namSec = employees.stream()
				.filter( a -> secHigh.isPresent() && 
						a.getSalary() == secHigh.get())
				.map(Employee::getName)
				.collect(Collectors.toList());
		
		System.out.println("all employees earning exactly the second-highest salary: "
		+ namSec);
		
		
		// 81. Find second-highest salary in each department
		
		Map<String, Optional<Integer>> secHighEachDept = employees.stream()
				.collect(Collectors.groupingBy(
					Employee::getDepartment,
					Collectors.mapping(
						Employee::getSalary,
						Collectors.collectingAndThen(
							Collectors.toList(),
							list -> list.stream()
							.distinct()
							.sorted(Comparator.reverseOrder())
							.skip(1)
							.findFirst()
								)
							)
						));
						
		System.out.println("second-highest salary per Department: "
				+ secHighEachDept);
		
		// 82. Find employee(s) with second-highest
		// salary in each department
		
		Map<String, List<Employee>> fullEmpSecHigh = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment))
				.entrySet()
				.stream()
				.collect(Collectors.toMap(
					Map.Entry::getKey,
					e -> {
				Optional<Integer> sec = e.getValue().stream()
						.map(Employee::getSalary)
						.distinct()
						.sorted(Comparator.reverseOrder())
						.skip(1)
						.findFirst();
				
				return e.getValue().stream()
					.filter( a -> sec.isPresent() &&
							a.getSalary() == sec.get())
					.collect(Collectors.toList());
			
					}	
						));
		
		
		System.out.println("employees with full data with the "
				+ "second-highest salary per Department: "
				+ fullEmpSecHigh);
		
		
		// 83. Find top 3 employees in each department
		
		Map<String, List<String>> top3PerDept = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.collectingAndThen(
				Collectors.toList(),
				list -> list.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
				.limit(3)
				.map(Employee::getName)
				.collect(Collectors.toList())				
				)
						));
		
		System.out.println("top 3 salary employee names per Department: "
				+ top3PerDept);
		
		
		
					
		// Method Ends Here

	}

}
