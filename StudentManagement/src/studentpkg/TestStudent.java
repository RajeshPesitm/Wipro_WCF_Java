package studentpkg;

import java.util.Scanner;

import studentpkg.DB.util.DBconnection;
 
public class TestStudent {
	
	public TestStudent() {
		// TODO Auto-generated constructor stub
	}
 
	public static void main(String[] args) {
        DBconnection.check(args);

		try (Scanner sc = new Scanner(System.in)) {
			Student st = new Student();

			StudentAdmin stAdmin = new StudentAdmin();

			try {

				System.out.println("Enter Student details: Rno/Name/Score");

				int rno = sc.nextInt();

				String name = sc.next();

				int score = sc.nextInt();

				if(score < 0 || score > 100)

					throw new ScoreException("score not in the range of 0 to 100");

				st.setRno(rno);

				st.setName(name);

				st.setScore(score);

				st.setGrade(st.calcGrade());

				boolean sts = stAdmin.addStudent(st);

				if(sts) 

					System.out.println("Details added");

				else

					System.out.println("Details could not be added");

				System.out.println("Current List of Students");

				stAdmin.displayStudents();

			}

			catch(Exception ex) {

				System.out.println(ex.getMessage());

			}

			finally {

				System.out.println("Thank you...!");

			}
		}
		catch(Exception ex) {

			System.out.println(ex.getMessage());

		}

	}
	
	/*import studentpkg.DB.util.DBconnection;

	public class Main {
	    public static void main(String[] args) {
	        DBconnection.check(args);
	    }
	}*/
	
 
}

 