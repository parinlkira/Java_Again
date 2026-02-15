package assignments;

class MaxOf2NumsTest {
    int a, b;  

    MaxOf2NumsTest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int max() {
        return (a > b ? a : b);
    }
}

class ArithTest extends MaxOf2NumsTest {
    // Parameterized constructor to initialize values
    ArithTest(int a, int b) {
        super(a, b);
    }
    
    int add() {
        int sum = a + b;
        return sum;
    }
    
    int sub() {
        int diff = a - b;
        return diff;
    }
    
    int mul() {
        int prod = a * b;
        return prod;
    }
    
    int div() {
        if(b != 0) {
            return a / b;
        } else {
            System.out.println("Cannot divide by zero!");
            return 0;
        }
    }
}

class FunctionsTest {
    public static void main(String[] args) {
        // Test with parameterized constructor
        ArithTest ar = new ArithTest(10, 5);
        
        System.out.println("=== Arithmetic Operations ===");
        System.out.println("Max of 10 and 5 is : " + ar.max());
        System.out.println("Addition is : " + ar.add());
        System.out.println("Subtraction is : " + ar.sub());
        System.out.println("Multiplication is : " + ar.mul());
        System.out.println("Division is : " + ar.div());
        
        // Test with different values
        ArithTest ar2 = new ArithTest(20, 8);
        System.out.println("\n=== With different values (20, 8) ===");
        System.out.println("Max of 20 and 8 is : " + ar2.max());
        System.out.println("Addition is : " + ar2.add());
        System.out.println("Subtraction is : " + ar2.sub());
        System.out.println("Multiplication is : " + ar2.mul());
        System.out.println("Division is : " + ar2.div());
    }
}