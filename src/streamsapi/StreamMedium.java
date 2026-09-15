package streamsapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamMedium {

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
		
		
        //37. Find Total Salary from employees
        
        double tot = employees.stream()
        		.mapToDouble(Employee::getSalary)
        		.sum();
        
        System.out.println("Total of Employees Salary: " + tot);
        
        //38. Find Highest Salary from employees
        
        double maxSal = employees.stream()
        		.mapToDouble(Employee::getSalary)
        		.max()
        		.orElse(0);
        
        System.out.println("Max Salary: " + maxSal);
        
        // 39. Get names of all employees
        
        List<String> namesOfAll = employees.stream()
        		.map(Employee::getName)
        		.toList();
        
        System.out.println("Names : " + namesOfAll);
        
        
        //40. Get unique departments 
        
        List<String> uniqDept = employees.stream()
        		.map(e -> e.getDepartment())
        		.distinct()
        		.collect(Collectors.toList());
        
        System.out.println("Unique Departments : " + uniqDept);
        
        // 41. Sort Employees by ascending salary and return names
        List<String> namesAscSal = employees.stream()
        		.sorted(Comparator.comparingDouble(Employee::getSalary))
        		.map(Employee::getName)
        		.collect(Collectors.toList());
        
    System.out.println("Sort Employees by ascending salaryand return names : "
     + namesAscSal);
        			  				
        // 42. Sort Employees by descending salary and return names
        
        List<String> namesDescSal = employees.stream()
        		.sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(e -> e.getName())
                .collect(Collectors.toList());

        System.out.println("Sort Employees by descending salary and return names : " + namesDescSal);
         
        // 43. Sort Employees by names

        List<String> namesSorted = employees.stream()
                  .sorted(Comparator.comparing(Employee::getName))
                  .map(Employee::getName)
                  .collect(Collectors.toList());
        System.out.println("Sort Employees by names : " + namesSorted);

        // 44. Find employee name with the max salary
        String nameMaxSal = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .map(e -> e.getName())
                .orElse("No Employee Found.");
        System.out.println("Employee with max salary: " + nameMaxSal);
     
        // 45. Find employee name with the min salary

        String nameMinSal = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .map(e -> e.getName())
                .orElse("No Employee Found.");

        System.out.println("Employee with min salary: " + nameMinSal);


        // 46. Find second-highest distinct salary
        
        double sechighestsal = employees.stream()
        		.map(Employee::getSalary)
        		.sorted(Comparator.reverseOrder())
        		.distinct()
        		.skip(1)
        		.findFirst()
        		.orElse(0);
        
       System.out.println("Employee with Second Highest distinct salary: " 
       + sechighestsal);
        
       // 48. Find top 3 highest distinct salaries
       
       List<Integer> top3sal = employees.stream()
    		   .map(Employee::getSalary)
    		   .sorted(Comparator.reverseOrder())
    		   .distinct().limit(3)
    		   .collect(Collectors.toList());
       
       System.out.println("top 3 distinct salary: " + top3sal);
       
       
       // 49. Count Employees by Department 
       
       Map<String, Long> countByDept = employees.stream()
    		   .collect(Collectors.groupingBy(
    				   Employee::getDepartment,
    				   Collectors.counting()
    				   ));
    
       System.out.println("Counting Employees by Dept: " + countByDept);
       
       // 50. Group employees by department
       
       Map<String, List<Employee>> empByDept = employees.stream()
    		   .collect(Collectors.groupingBy(
    				   e -> e.getDepartment()
                                ));

      /*    

      System.out.println("Employees grouped by department: " + empByDept);   

      // another way to print the grouped employee  by department
                empByDept.forEach((dept, empList) -> {
                System.out.println("Department: " + dept);
                empList.forEach(e -> System.out.println("Employee: " + e));
                });

                */
      
      // Another simple way using streams and forEach
      // to print employee name per dept like Dept -> {Emp1, Emp2, Emp3}
      empByDept.forEach((dept, empList) -> {
          System.out.print("Department: " + dept + " -> {");
          String empNames = empList.stream()
                  .map(Employee::getName)
                  .collect(Collectors.joining(", "));
          System.out.println(empNames + "}");
      });

      // 51. Find the average salary by department

      Map<String, Double> avgSalaryByDept = employees.stream()
              .collect(Collectors.groupingBy(
                      Employee::getDepartment,
                      Collectors.averagingDouble(Employee::getSalary)
              ));

      System.out.println("Average salary by department: " + avgSalaryByDept);

      
      // 52. Find total salary per department
      
      Map<String, Double> totSalaryByDept = employees.stream()
    		  .collect(Collectors.groupingBy(
    				  Employee::getDepartment,
    				  Collectors.summingDouble(Employee::getSalary)
    				  ));
      System.out.println("Total salary by department: " + totSalaryByDept);
      
      
      // 53. Find Highest Paid employee in each department
      
      Map<String, Optional<Employee>> hiEmpPerDept = employees.stream()
    		  		.collect(Collectors.groupingBy(
    		  				Employee::getDepartment,
    		  				Collectors.maxBy(
    		  						Comparator.comparingDouble(Employee::getSalary)
    		  						)
    		  				));
    		  
     // Printing this in dept -> {Emp Name} manner
      
      hiEmpPerDept.forEach((dept, empList) -> {
    	 System.out.print("Department : " + dept + "-> { ");
    	 
    	 String name = empList.stream()
    			 .map(Employee::getName)
    			 .collect(Collectors.joining(", "));
    	 
    	 System.out.println(name + " }");
    	 
     } );
     
     
     // 54. Lowest-paid employee in each department
      
     Map<String, Optional<Employee>> lowEmpPerDep = employees.stream()
    		 .collect(Collectors.groupingBy(
    				 Employee::getDepartment,
    				 Collectors.minBy(
    						Comparator.comparing(Employee::getSalary)
    						 )
    				 ));
   
     lowEmpPerDep.forEach((dept, empList) ->{
    	 
    	 System.out.print("Dept: " + dept + " -> {");
    	 String name1 = empList.stream()
    			 .map(Employee::getName)
    			 .collect(Collectors.joining(", "));
    	 
    	 System.out.println(name1 + " }");
     } );
    		  
     // 55. Employee names grouped by department
     
     Map<String, List<String>> empNamePerDept = employees.stream()
    		 .collect(Collectors.groupingBy(
      				Employee::getDepartment,
    				Collectors.mapping(Employee::getName, Collectors.toList()) 
    				 ));
     
    System.out.println("Employee names grouped by department: " + empNamePerDept );	  
    
    // 56. Partition employees by salary > 100000
    		  
    Map<Boolean, List<Employee>> partBySal = employees.stream()
    					.collect(Collectors.partitioningBy(
    							e -> e.getSalary() > 100000
    							));
    
    System.out.println("Partition employees by salary > 100000: " + partBySal );
    
    // 57. Count employees by salary > 100000
    
    Map<Boolean, Long> countEmpGThanSal = employees.stream()
    		.collect(Collectors.partitioningBy(
    				e -> e.getSalary() > 100000,
    				Collectors.counting()
    				));
    System.out.println("Count employees by salary > 100000: " + countEmpGThanSal);
    
    
    // 58. Find department with highest average salary
    
    String DeptWithHighAvgSal = employees.stream()
    		.collect(Collectors.groupingBy(
    				Employee::getDepartment,
    				Collectors.averagingDouble(Employee::getSalary)
    				))
    		.entrySet()
    		.stream()
    		//.peek(e -> System.out.println("Peeking Data: " + e))
    	// Peek is not part of the solution, its just for our data visualisation
    	// purposes and also to see how peek can be invoked as intermediate operation
    		.max(Map.Entry.comparingByValue())
    		.map(Map.Entry::getKey)
    		.orElse("Nothing is found");
    				
    System.out.println("Dept With Highest Avg Sal: " + DeptWithHighAvgSal);
    		
    // 59. Find department with highest total salary		
    		
    String DeptWithHighTotSal = employees.stream()
    		.collect(Collectors.groupingBy(
    				Employee::getDepartment,
    				Collectors.summingDouble(Employee::getSalary)
    				))
    		.entrySet()
    		.stream()
    		.max(Map.Entry.comparingByValue())
    		.map(Map.Entry::getKey)
    		.orElse("None Found");
   
    System.out.println("Dept With Highest Tot Sal: " + DeptWithHighTotSal);
    
    
    // 60. Find departments having more than 2 employees
    
    List<String> deptMoreThan2Emps = employees.stream()
    		.collect(Collectors.groupingBy(
    				Employee::getDepartment,
    				Collectors.counting()
    				))
    		.entrySet()
    		.stream()
    		.filter( e -> e.getValue() > 2)
    		.map(Map.Entry::getKey)
    		.collect(Collectors.toList());
    
    System.out.println("departments having more than 2 employees: "
    + deptMoreThan2Emps);
    
    
    // 61. Convert employees to Map<id, employee>
    
    Map<Integer, Employee> idToEmp = employees.stream()
    		.collect(Collectors.toMap(
    				Employee::getId, 
    				Function.identity()
    				));
    
    System.out.println("Convert employees to Map<id, employee>: "
    	    + idToEmp);
      
    
    // 62. Convert employees to Map<id, name>
    
    Map<Integer, String> idToName = employees.stream()
    		.collect(Collectors.toMap(
    				Employee::getId,
    				Employee::getName
    				));
   
    System.out.println("Convert employees to Map<id, name>: "
    	    + idToName);
    
    // 63. Handle duplicate keys with toMap()
    
    Map<String, String> deptToNameNoDups = employees.stream()
    		.collect(Collectors.toMap(
    				Employee::getDepartment,
    				Employee::getName,
    				(existing, replacement) -> existing	// This is the merging operation
    				));
    
    System.out.println("Handle duplicate keys with toMap(): "
    	    + deptToNameNoDups);
    
    // Bonus Teaser : Most Frequent Element in a list
    
    List<String> list1 = Arrays.asList("Pen", "Eraser", "Note Book", "Pen",
    		"Pencil", "Pen", "Note Book", "Pencil");
    
    String mostFreq = list1.stream()
    		.collect(Collectors.groupingBy(
    				Function.identity(),
    				Collectors.counting()
    				))
    		.entrySet()
    		.stream()
    		.max(Map.Entry.comparingByValue())
    		.map(Map.Entry::getKey)
    		.orElse("No Element Found");
    
    System.out.println("Most Frequent Element is : " + mostFreq);
    		
    		
    		
    		
    
      // Method ends here
	}

}
