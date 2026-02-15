package assignments;

// Input array {1, 0, 2, 0, 3, 0, 4, 0, 0 , 0}
// Output array {1, 2, 3, 4, 0, 0, 0, 0, 0, 0}
// Input array {1, 2, 3}
// Output array {1, 2, 3}
// Input array {0, 0, 0}
// Output array {0, 0, 0}
// Write java function to move all zeros to the end of the array
// using multiple solutions and also using two pointers approach


public class MoveZeros {
    
    // Solution 1: Two Pointers Approach (Most Efficient)
    // Time Complexity: O(n), Space Complexity: O(1)
    public static void moveZerosV1(int[] arr) {
        int j = 0;  // Position to place next non-zero element
        
        // First pass: Move all non-zero elements to the front
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            }
        }
        
        // Second pass: Fill remaining positions with zeros
        while (j < arr.length) {
            arr[j] = 0;
            j++;
        }
    }
    
    // Solution 2: Two Pointers with Swap Approach
    // Time Complexity: O(n), Space Complexity: O(1)
    // This approach swaps elements instead of overwriting
    public static void moveZerosV2(int[] arr) {
        int j = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // Swap only if positions are different
                if (i != j) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
                j++;
            }
        }
    }
    
    // Solution 3: Using Extra Array
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int[] moveZerosV3(int[] arr) {
        int[] result = new int[arr.length];
        int index = 0;
        
        // Copy all non-zero elements
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                result[index++] = arr[i];
            }
        }
        
        // Rest will be zeros (default initialization)
        return result;
    }
    
    // Solution 4: Using Collections and Stream (Functional Approach)
    // Time Complexity: O(n), Space Complexity: O(n)
    public static Integer[] moveZerosV4(Integer[] arr) {
        Integer[] nonZeros = java.util.Arrays.stream(arr)
                .filter(x -> x != 0)
                .toArray(Integer[]::new);
        
        Integer[] result = new Integer[arr.length];
        System.arraycopy(nonZeros, 0, result, 0, nonZeros.length);
        return result;
    }

    // Solution 5: Bubble Sort-like Approach
    // Time Complexity: O(n²), Space Complexity: O(1)
    public static void moveZerosV5(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                // Swap if current is zero and next is non-zero
                if (arr[j] == 0 && arr[j + 1] != 0) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Begin - Correct Two Pointer Approach
    public static void mv(int[] arr){

        int i = 0;  // pointer to place non-zero elements

        // Move all non-zero elements to the front
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != 0) {
                arr[i] = arr[j];
                i++;
            }
        }

        // Fill remaining positions with zeros
        while (i < arr.length) {
            arr[i] = 0;
            i++;
        }
    }
    // End
    
    // Utility method to print array
    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("}");
    }
    
    // Utility method to print Integer array
    public static void printArray(Integer[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("}");
    }
    
    public static void main(String[] args) {
        // Test cases
        int[] test1 = {1, 0, 2, 0, 3, 0, 4, 0, 0, 0};
        int[] test2 = {1, 2, 3};
        int[] test3 = {0, 0, 0};
        int[] test4 = {0, 1, 0, 2, 0, 3};
        
        System.out.println("=== Solution 1: Two Pointers Approach ===");
        System.out.print("Input: ");
        printArray(test1);
        moveZerosV1(test1);
        System.out.print("Output: ");
        printArray(test1);
        
        System.out.println("\n=== Solution 2: Two Pointers with Swap ===");
        int[] test1Copy = {1, 0, 2, 0, 3, 0, 4, 0, 0, 0};
        System.out.print("Input: ");
        printArray(test1Copy);
        moveZerosV2(test1Copy);
        System.out.print("Output: ");
        printArray(test1Copy);
        
        System.out.println("\n=== Solution 3: Using Extra Array ===");
        System.out.print("Input: ");
        printArray(test4);
        int[] result3 = moveZerosV3(test4);
        System.out.print("Output: ");
        printArray(result3);
        
        System.out.println("\n=== Solution 4: Functional Approach (Stream) ===");
        Integer[] test4Obj = {0, 1, 0, 2, 0, 3};
        System.out.print("Input: ");
        printArray(test4Obj);
        Integer[] result4 = moveZerosV4(test4Obj);
        System.out.print("Output: ");
        printArray(result4);
        
        System.out.println("\n=== Solution 5: Bubble Sort-like Approach ===");
        int[] test5 = {1, 0, 2, 0, 3, 0, 4};
        System.out.print("Input: ");
        printArray(test5);
        moveZerosV5(test5);
        System.out.print("Output: ");
        printArray(test5);
        
        System.out.println("\n=== Test Cases from Problem ===");
        test1 = new int[]{1, 0, 2, 0, 3, 0, 4, 0, 0, 0};
        test2 = new int[]{1, 2, 3};
        test3 = new int[]{0, 0, 0};
        
        System.out.print("Test 1 - Input: ");
        printArray(test1);
        moveZerosV1(test1);
        System.out.print("Output: ");
        printArray(test1);
        
        System.out.print("\nTest 2 - Input: ");
        printArray(test2);
        moveZerosV1(test2);
        System.out.print("Output: ");
        printArray(test2);
        
        System.out.print("\nTest 3 - Input: ");
        printArray(test3);
        moveZerosV1(test3);
        System.out.print("Output: ");
        printArray(test3);
        
        System.out.println("\n=== Testing mv Function ===");
        int[] mvTest = {1, 0, 2, 0, 3, 0, 4, 0, 0, 0};
        System.out.print("Input: ");
        printArray(mvTest);
        mv(mvTest);
        System.out.print("Output: ");
        printArray(mvTest);
    }
}
