package input;

import java.util.Scanner;

public class ArrayInput {

    public static int[] readArray(Scanner sc, int n) {

        int[] arr = new int[n];

        System.out.println("Enter " + n + " numbers:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        return arr;
    }
}

