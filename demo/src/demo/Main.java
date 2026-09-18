package demo;
import java.util.Scanner;

import input.ArrayInput;
import operations.ArrayOperations;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read array size
        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        // Read array
        int[] arr = ArrayInput.readArray(sc, n);

        // Perform operations
        int sum = ArrayOperations.calculateSum(arr);
        int evenCount = ArrayOperations.countEven(arr);

        // Display results
        System.out.println("Sum = " + sum);
        System.out.println("Even numbers = " + evenCount);

        sc.close();
    }
}
