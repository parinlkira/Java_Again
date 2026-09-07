package streamsapi;
import java.util.*;

class Employee {
    int id;
    String name;
    String department;
    String role;
    int salary;
    int age;

    public Employee(int id, String name, String department, String role, int salary, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }
    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
    public int getSalary() {
        return salary;
    }

    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

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
    }  
}
