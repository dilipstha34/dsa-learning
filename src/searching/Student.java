package searching;

public class Student {

    private int rollNo;
    private String studentName;
    private double tuitionFee;

    // Constructor
    public Student(int rollNo, String studentName, double tuitionFee) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.tuitionFee = tuitionFee;
    }

    // Getter for Roll No
    public int getRollNo() {
        return rollNo;
    }

    // Getter for Student Name
    public String getStudentName() {
        return studentName;
    }

    // Getter for Tuition Fee
    public double getTuitionFee() {
        return tuitionFee;
    }

    @Override
    public String toString() {
        return rollNo + "\t" +
                studentName + "\t\t" +
                tuitionFee;
    }
}