package streamsapi;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamsPractice {

	public static void main(String[] args) {
		
		List<Integer> nums = Arrays.asList(10, 5, 20, 5, 30, 40, 10, 15, 2);

		List<String> words =
		    Arrays.asList("java", "spring", "aws", "java", "kafka", "spring");

		List<Employee> employee = Arrays.asList(
		    new Employee(1, "Alice", "IT", "Developer", 120000, 30),
		    new Employee(2, "Bob", "IT", "Developer", 100000, 28),
		    new Employee(3, "Charlie", "HR", "Manager", 90000, 35),
		    new Employee(4, "David", "IT", "Architect", 150000, 40),
		    new Employee(5, "Eva", "HR", "Recruiter", 70000, 27),
		    new Employee(6, "Frank", "Finance", "Analyst", 110000, 32),
		    new Employee(7, "Grace", "Finance", "Manager", 140000, 38),
		    new Employee(8, "Henry", "IT", "Developer", 100000, 31)
		);
		
		
		// Find Employees by IT Department
		
		List<String> ITDept = employee.stream()
				.filter( a -> a.getDepartment() == "IT")
				.map(Employee::getName)
				.toList();
		
		System.out.println("Find Employees by IT Department: " + ITDept);
		
		// Find Employees Grouped By Age
		
		Map<Integer, List<Employee>> groupAge = employee.stream()
				.collect(Collectors.groupingBy(
						Employee::getAge,
						Collectors.toList()
						));
		
		System.out.println("Employees Grouped By Age: " + groupAge);
		
		// Find department having more than 5 employees
		
		List<String> moreThan5 = employee.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.counting()
						))
				.entrySet()
				.stream()
				.filter(e -> e.getValue() > 5)
				.map(Map.Entry::getKey)
				.toList();
		
		System.out.println("department having more than 5 employees: " + moreThan5);
		
		// Group numbers by parity using GroupingBy and PartitioningBy
		
		Map<String, List<Integer>> numByPar = nums.stream()
				.collect(Collectors.groupingBy(
						n -> n % 2 == 0 ? "Even" : "Odd"
						));
 
		System.out.println(" Group numbers by parity: " + numByPar);
		
		Map<Boolean, List<Integer>> numByParity = nums.stream()
				.collect(Collectors.partitioningBy( n -> n % 2 == 0));
		
		System.out.println(" Even -> " + numByParity.get(true));
		System.out.println(" Odd -> " + numByParity.get(false));
		
		
		// Grouping Strings by Length
		
		Map<Integer, List<String>> strByLen = words.stream()
				.collect(Collectors.groupingBy(String::length));
		
		System.out.println("Grouping Strings by Length: " + strByLen);
		
		// Group employees by department and count
		/* Hard Question 
		 
		 IT -> 3 -> [Alice, Charlie, David]
		 HR -> 2 -> [Bob, Eve]
		 */
		
		Map<String, Map<Long, List<String>>> byDeptAndCount =
			    employee.stream()
			        .collect(Collectors.groupingBy(
			            Employee::getDepartment,
			            Collectors.collectingAndThen(
			                Collectors.toList(),
			                list -> Map.of(
			                    (long) list.size(),
			                    list.stream()
			                        .map(Employee::getName)
			                        .toList()
			                )
			            )
			        ));
		
		System.out.println(" Group employees by department and count: \n"
		+ byDeptAndCount);
		
		// Group employees by department and salary
		
		
		Map<String, Map<Integer, List<String>>> byDeptAndSal = employee.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.groupingBy(
								Employee::getSalary,
								Collectors.mapping(Employee::getName, 
										Collectors.toList())
								)	
						));
		
		System.out.println(" Group employees by department and salary: \n"
				+ byDeptAndSal);
		
		// Find maximum value in Map
		
		Map<String, Integer> map = new HashMap<>();
		map.put("Apple", 10);
        map.put("Banana", 50);
        map.put("Cherry", 30);
        map.put("Date", 40);
        map.put("Elderberry", 20);
		
        Optional<Integer> maxMap = map.entrySet().stream()
        		.max(Map.Entry.comparingByValue())
        		.map(Map.Entry::getValue);
        
        maxMap.ifPresent( a -> System.out.println("Mxx Value in a Map: " + a));
        
        
        // Find top 3 entries in Map
        
        List<Entry<String, Integer>> top3 = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
        
        System.out.println("Find top 3 entries in Map: ");
        // Print results using forEach
        
        top3.forEach(entry -> System.out.println(entry.getKey() + 
        		 " : " + entry.getValue()));
        
		// Method Ends Here
	}

}
