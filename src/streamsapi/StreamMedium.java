package streamsapi;

import java.util.*;
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
     
     
    		  
    		  
    		  
    		  
    		  
    		  
    		  
      
      // Method ends here
	}

}
