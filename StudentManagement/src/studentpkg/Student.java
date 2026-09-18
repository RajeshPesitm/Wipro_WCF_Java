package studentpkg;

public class Student {

	private int rno;
	private String name;
	private int score;
	private String grade;
	public Student() {
		super();
	}
	public Student(int rno, String name, int score, String grade) {
		super();
		this.rno = rno;
		this.name = name;
		this.score = score;
		this.grade = grade;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", score=" + score + ", grade=" + grade + "]";
	}
	public String calcGrade() {
		String grade = "no grade";
		if(this.score >=90 && this.score <= 100)
			grade = "A";
		if(this.score >= 80 && this.score <= 89)
			grade = "B";
		if(this.score >= 70 && this.score <=79)
			grade = "C";
		else
			grade = "D";
		return grade;
	}
}