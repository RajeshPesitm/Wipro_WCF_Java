package operations;

public class ArrayOperations {

    // Calculate sum of all numbers
    public static int calculateSum(int[] arr) {

        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        return sum;
    }

    // Count even numbers
    public static int countEven(int[] arr) {

        int count = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}
