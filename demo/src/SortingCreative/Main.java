//https://chatgpt.com/s/t_6aa3cda4b7d08191908cfacacccfdc92

package SortingCreative;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        // ========================================================
        // STUDENT LIST
        // Using QUICK SORT
        // ========================================================

        QuickSortList<Student> students = new QuickSortList<>();

        students.add(new Student(101, "Rahul", 85));
        students.add(new Student(102, "Anita", 95));
        students.add(new Student(103, "Kiran", 72));
        students.add(new Student(104, "Manoj", 65));
        students.add(new Student(105, "Priya", 90));

        // Comparator rule:
        // Sort Student based on score - highest to lowest
        System.out.println("Students sorted by score:");

        students.sort(
                Comparator.comparingDouble(Student::getScore).reversed()
        );
        students.forEach(System.out::println);

        // Sort Student based on Name - highest to lowest
        System.out.println("Students sorted by Name:");

        students.sort(
                Comparator.comparing(Student::getName).reversed()
        );

        students.forEach(System.out::println);
        

        // ========================================================
        // PRODUCT LIST
        // Using MERGE SORT
        // ========================================================

        MergeSortList<Product> products = new MergeSortList<>();

        products.add(new Product(1, "Laptop", 75000));
        products.add(new Product(2, "Mobile", 35000));
        products.add(new Product(3, "Keyboard", 2500));
        products.add(new Product(4, "Monitor", 15000));
        products.add(new Product(5, "Mouse", 1200));

        // Comparator rule:
        // Sort Product based on price - lowest to highest
        
        products.sort(
                Comparator.comparingDouble(Product::getPrice).reversed()
        );

        System.out.println("\nProducts sorted by price:");

        products.forEach(System.out::println);


        // ========================================================
        // BUBBLE SORT EXAMPLE
        // ========================================================

        BubbleSortList<Student> bubbleStudents =
                new BubbleSortList<>();

        bubbleStudents.add(new Student(201, "A", 70));
        bubbleStudents.add(new Student(202, "B", 90));
        bubbleStudents.add(new Student(203, "C", 80));

        bubbleStudents.sort(Comparator.comparingDouble(Student::getScore).reversed());

        System.out.println("\nBubble Sort - Students:");

        bubbleStudents.forEach(System.out::println);


        // ========================================================
        // INSERTION SORT EXAMPLE
        // ========================================================

        InsertionSortList<Product> insertionProducts =
                new InsertionSortList<>();

        insertionProducts.add(new Product(11, "A", 5000));
        insertionProducts.add(new Product(12, "B", 1000));
        insertionProducts.add(new Product(13, "C", 3000));

        insertionProducts.sort(Comparator.comparingDouble(Product::getPrice).reversed());

        System.out.println("\nInsertion Sort - Products:");

        insertionProducts.forEach(System.out::println);
    }
}
