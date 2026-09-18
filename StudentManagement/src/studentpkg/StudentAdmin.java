package studentpkg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class StudentAdmin {
	public StudentAdmin() {
		// TODO Auto-generated constructor stub
	}
	List<Student> stList = new ArrayList<Student>();
	public boolean addStudent(Student st) {
		boolean sts = false;
		//code
		try {
			if(st!=null) {
				//stList.add(st); 
				//store/add the student details to Student_TB table in the Oracle DB
				//1.load driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//2.establish connection
				//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","wcf","wcf123");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "wcf", "wcf123");
				//3.prepare query
				String qry ="insert into Student values(?,?,?,?)";
				PreparedStatement ps =	con.prepareStatement(qry);
				ps.setInt(1,st.getRno());
				ps.setString(2,st.getName());
				ps.setInt(3,st.getScore());
				ps.setString(4,st.getGrade());
				//4.execute qry
				int count = ps.executeUpdate();
				//5.process result
				if(count == 1)
					sts=true;
				//6.close connection
				con.close();
			}
			else
				throw new NullPointerException("Student object is null");
		}
		catch(ClassNotFoundException clex) {
			System.out.println(clex.getMessage());
		}
		catch(NullPointerException npex) {
			System.out.println(npex.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sts;
	}
	public void displayStudents() {
		/*int size = stList.size();
		if(size == 0)
			System.out.println("No students been added");
		else {
			for(Student st : stList)
				System.out.println(st);
		}*/
		ArrayList<Student> stdList = new ArrayList<Student>();
		//collect the student details from DB table to a list
		try {
			//1.load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.establish connection
			//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","wcf7sep","wcf7sep"); 
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "wcf", "wcf123");
			//3.prepare query
			String qry ="select * from Student";
			PreparedStatement ps =	con.prepareStatement(qry);
			//4.execute qry
			ResultSet rs = ps.executeQuery();
			//5.process the result
			while(rs.next()) {
				int rno =rs.getInt("rno");
				String name = rs.getString("name");
				int score = rs.getInt("score");
				String grade = rs.getString("grade");
				Student std = new Student(rno,name,score,grade);
				stdList.add(std);
			}
			stdList.forEach(System.out :: println);
			//6.close connection
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}