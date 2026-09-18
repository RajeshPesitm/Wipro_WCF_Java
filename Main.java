//https://chatgpt.com/s/t_6aa3cda4b7d08191908cfacacccfdc92

package SortingCreative;

import java.util.ArrayList;
import java.util.Comparator;


// ============================================================
// 1. BUBBLE SORT
// ============================================================

class BubbleSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        int n = size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                // Comparator decides the ordering
                if (comparator.compare(get(j), get(j + 1)) > 0) {

                    T temp = get(j);
                    set(j, get(j + 1));
                    set(j + 1, temp);
                }
            }
        }
    }
}


// ============================================================
// 2. INSERTION SORT
// ============================================================

class InsertionSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        for (int i = 1; i < size(); i++) {

            T current = get(i);

            int j = i - 1;

            while (j >= 0 &&
                    comparator.compare(get(j), current) > 0) {

                set(j + 1, get(j));
                j--;
            }

            set(j + 1, current);
        }
    }
}


// ============================================================
// 3. QUICK SORT
// ============================================================

class QuickSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        quickSort(0, size() - 1, comparator);
    }

    private void quickSort(
            int low,
            int high,
            Comparator<? super T> comparator) {

        if (low < high) {

            int pivotIndex = partition(
                    low,
                    high,
                    comparator
            );

            quickSort(low, pivotIndex - 1, comparator);

            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(
            int low,
            int high,
            Comparator<? super T> comparator) {

        T pivot = get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (comparator.compare(get(j), pivot) <= 0) {

                i++;

                T temp = get(i);
                set(i, get(j));
                set(j, temp);
            }
        }

        T temp = get(i + 1);
        set(i + 1, get(high));
        set(high, temp);

        return i + 1;
    }
}


// ============================================================
// 4. MERGE SORT
// ============================================================

class MergeSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        if (size() > 1) {
            mergeSort(0, size() - 1, comparator);
        }
    }

    private void mergeSort(
            int left,
            int right,
            Comparator<? super T> comparator) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(left, middle, comparator);

        mergeSort(middle + 1, right, comparator);

        merge(left, middle, right, comparator);
    }

    private void merge(
            int left,
            int middle,
            int right,
            Comparator<? super T> comparator) {

        ArrayList<T> temp = new ArrayList<>();

        int i = left;
        int j = middle + 1;

        while (i <= middle && j <= right) {

            if (comparator.compare(get(i), get(j)) <= 0) {
                temp.add(get(i));
                i++;
            } else {
                temp.add(get(j));
                j++;
            }
        }

        while (i <= middle) {
            temp.add(get(i));
            i++;
        }

        while (j <= right) {
            temp.add(get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            set(left + k, temp.get(k));
        }
    }
}


// ============================================================
// STUDENT CLASS
// ============================================================

class Student {

    private int rno;
    private String name;
    private double score;

    public Student(int rno, String name, double score) {
        this.rno = rno;
        this.name = name;
        this.score = score;
    }

    public int getRno() {
        return rno;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rno=" + rno +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}


// ============================================================
// PRODUCT CLASS
// ============================================================

class Product {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}


// ============================================================
// MAIN
// ============================================================

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
