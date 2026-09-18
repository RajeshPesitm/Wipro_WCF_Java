package Studentlistpkg;

import java.util.ArrayList;
import java.util.Comparator;


// Custom Exception
class InvalidStudentException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	
    public InvalidStudentException(String message) {
        super(message);
    }
}

// Student class
class Student {
    private int rno;
    private String name;
    private double score;
    private String grade;

    public Student(int rno, String name, double score) {
        this.rno = rno;
        this.name = name;
        this.score = score;
        this.grade = calculateGrade(score);
    }

    private String calculateGrade(double score) {
        if (score >= 90 && score <= 100)
            return "A";
        else if (score >= 80)
            return "B";
        else if (score >= 70)
            return "C";
        else
            return "D";
    }

    public double getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }

    public int getRno() {
        return rno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rno=" + rno +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                '}';
    }
}

// Custom List extending ArrayList
class StudentList extends ArrayList<Student> {

	 private static final long serialVersionUID = 1L;
	
    @Override
    public boolean add(Student student) throws InvalidStudentException {

        // Validate before adding
        if (student == null) {
            throw new InvalidStudentException("Student object cannot be null");
        }

        if (student.getScore() < 0 || student.getScore() > 100) {
            throw new InvalidStudentException(
                    "Invalid score: " + student.getScore() +
                    ". Score must be between 0 and 100."
            );
        }

        return super.add(student);
    }
}

public class Main {

    public static void main(String[] args) {

        StudentList students = new StudentList();

        try {
            students.add(new Student(101, "Rahul", 85));
            students.add(new Student(102, "Anita", 95));
            students.add(new Student(103, "Kiran", 60));
            students.add(new Student(104, "Manoj", 75));

            // This will throw custom exception
            students.add(new Student(105, "Ravi", 105));

        } catch (InvalidStudentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }

        System.out.println("\nStudents:");

        // Lambda expression to display students
        students.forEach(student -> System.out.println(student));

        // Lambda + Comparator: sort students by score descending
        students.sort(
                Comparator.comparingDouble(Student::getScore).reversed()
        );

        System.out.println("\nStudents sorted by score:");

        students.forEach(System.out::println);
        
		// Lambda + Comparator: sort students by Name descending
		students.sort(
				Comparator.comparing(Student::getName).reversed()
				);

		System.out.println("\nStudents sorted by score:");

		students.forEach(System.out::println);
        
    }
}

