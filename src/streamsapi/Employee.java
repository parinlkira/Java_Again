package streamsapi;

public class Employee {

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
    
    @Override
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
