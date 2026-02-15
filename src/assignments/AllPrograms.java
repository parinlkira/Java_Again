package assignments;

import java.util.Scanner;

public class AllPrograms {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // 1. Print entered name n number of times
            System.out.print("Enter name: ");
            String nameInput = sc.nextLine();
            System.out.print("Enter n: ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline
            String repeatedName = nameInput;
            for(int i = 0; i < n; i++) {
                System.out.println(repeatedName);
            }

            // 2. Print the table of 5
            String tableOfFive = "";
            System.out.println("Table of 5:");
            for(int i = 1; i <= 10; i++) {
                tableOfFive = "5 * " + i + " = " + (5 * i);
                System.out.println(tableOfFive);
            }

            // 3. Print the table of entered number
            System.out.print("Enter number for table: ");
            int tableNum = sc.nextInt();
            String tableOfNum = "";
            for(int i = 1; i <= 10; i++) {
                tableOfNum = tableNum + " * " + i + " = " + (tableNum * i);
                System.out.println(tableOfNum);
            }

            // 4. Print values between two entered numbers
            System.out.print("Enter start: ");
            int start = sc.nextInt();
            System.out.print("Enter end: ");
            int end = sc.nextInt();
            StringBuilder rangeValues = new StringBuilder();
            for(int i = start; i <= end; i++) {
                rangeValues.append(i).append(" ");
            }
            System.out.println(rangeValues.toString());

            // 5. Factorial
            System.out.print("Enter number for factorial: ");
            int factNum = sc.nextInt();
            long fact = 1;
            for(int i = 1; i <= factNum; i++) {
                fact *= i;
            }
            String factorialResult = String.valueOf(fact);
            System.out.println("Factorial: " + factorialResult);

            // 6. Sum of first five positive integers
            int sum = 0;
            for(int i = 1; i <= 5; i++) {
                sum += i;
            }
            String sumFirstFive = String.valueOf(sum);
            System.out.println("Sum of first 5: " + sumFirstFive);

            // 7. Prime or not
            System.out.print("Enter number to check prime: ");
            int primeNum = sc.nextInt();
            boolean isPrime = true;
            if(primeNum <= 1) isPrime = false;
            else {
                for(int i = 2; i <= Math.sqrt(primeNum); i++) {
                    if(primeNum % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            String primeResult = isPrime ? "Prime" : "Not Prime";
            System.out.println(primeResult);

            // 8. Even numbers 1 to 20
            StringBuilder evenNumbers = new StringBuilder();
            System.out.println("Even numbers 1 to 20:");
            for(int i = 2; i <= 20; i += 2) {
                evenNumbers.append(i).append(" ");
            }
            System.out.println(evenNumbers.toString());

            // Reverse name
            System.out.print("Enter name to reverse: ");
            sc.nextLine(); // consume
            String revNameInput = sc.nextLine();
            StringBuilder reversedName = new StringBuilder();
            for(int i = revNameInput.length() - 1; i >= 0; i--) {
                reversedName.append(revNameInput.charAt(i));
            }
            String reversed = reversedName.toString();
            System.out.println("Reversed: " + reversed);

            // Every third character
            System.out.print("Enter string for every third char: ");
            String everyThirdInput = sc.nextLine();
            StringBuilder everyThirdChar = new StringBuilder();
            for(int i = 2; i < everyThirdInput.length(); i += 3) {
                everyThirdChar.append(everyThirdInput.charAt(i)).append(" ");
            }
            System.out.println(everyThirdChar.toString());

            // Number of characters
            System.out.print("Enter string for char count: ");
            String charCountInput = sc.nextLine();
            int count = 0;
            char[] charArray = charCountInput.toCharArray();
            for(char c : charArray) {
                count++;
            }
            String charCountResult = String.valueOf(count);
            System.out.println("Characters: " + charCountResult);

            // Vowels
            System.out.print("Enter string for vowels: ");
            String vowelInput = sc.nextLine();
            int vowels = 0;
            for(char c : vowelInput.toLowerCase().toCharArray()) {
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') vowels++;
            }
            String vowelCount = String.valueOf(vowels);
            System.out.println("Vowels: " + vowelCount);

            // Find character
            System.out.print("Enter string: ");
            String findInput = sc.nextLine();
            System.out.print("Enter char to find: ");
            char findChar = sc.next().charAt(0);
            int index = -1;
            for(int i = 0; i < findInput.length(); i++) {
                if(findInput.charAt(i) == findChar) {
                    index = i;
                    break;
                }
            }
            String findResult = (index != -1) ? ("Found at index: " + index) : "Not found";
            System.out.println(findResult);

            // Compare two strings without equals
            System.out.print("Enter first string: ");
            sc.nextLine(); // consume
            String compareStr1 = sc.nextLine();
            System.out.print("Enter second string: ");
            String compareStr2 = sc.nextLine();
            boolean equal = true;
            if(compareStr1.length() != compareStr2.length()) equal = false;
            else {
                for(int i = 0; i < compareStr1.length(); i++) {
                    if(compareStr1.charAt(i) != compareStr2.charAt(i)) {
                        equal = false;
                        break;
                    }
                }
            }
            String compareResult = equal ? "Equal" : "Not equal";
            System.out.println(compareResult);

            // Compress string
            System.out.print("Enter string to compress: ");
            String compressInput = sc.nextLine();
            StringBuilder compressed = new StringBuilder();
            int cnt = 1;
            for(int i = 1; i < compressInput.length(); i++) {
                if(compressInput.charAt(i) == compressInput.charAt(i-1)) {
                    cnt++;
                } else {
                    compressed.append(compressInput.charAt(i-1)).append(cnt);
                    cnt = 1;
                }
            }
            if(compressInput.length() > 0)
                compressed.append(compressInput.charAt(compressInput.length()-1)).append(cnt);
            String compressedResult = compressed.toString();
            System.out.println("Compressed: " + compressedResult);

            // Palindrome
            System.out.print("Enter string for palindrome: ");
            String palindromeInput = sc.nextLine();
            boolean pal = true;
            for(int i = 0; i < palindromeInput.length()/2; i++) {
                if(palindromeInput.charAt(i) != palindromeInput.charAt(palindromeInput.length()-1-i)) {
                    pal = false;
                    break;
                }
            }
            String palindromeResult = pal ? "Palindrome" : "Not palindrome";
            System.out.println(palindromeResult);
        }
    }
}