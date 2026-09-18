package SortingCreative;

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
