package demo.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_marks1")
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private int sub1Insem;
    private int sub1Endsem;

    private int sub2Insem;
    private int sub2Endsem;

    private int sub3Insem;
    private int sub3Endsem;

    private int sub4Insem;
    private int sub4Endsem;

    private int sub5Insem;
    private int sub5Endsem;

    private int totalMarks;
    private double percentage;
    private double cgpa;

    public StudentResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSub1Insem() {
        return sub1Insem;
    }

    public void setSub1Insem(int sub1Insem) {
        this.sub1Insem = sub1Insem;
    }

    public int getSub1Endsem() {
        return sub1Endsem;
    }

    public void setSub1Endsem(int sub1Endsem) {
        this.sub1Endsem = sub1Endsem;
    }

    public int getSub2Insem() {
        return sub2Insem;
    }

    public void setSub2Insem(int sub2Insem) {
        this.sub2Insem = sub2Insem;
    }

    public int getSub2Endsem() {
        return sub2Endsem;
    }

    public void setSub2Endsem(int sub2Endsem) {
        this.sub2Endsem = sub2Endsem;
    }

    public int getSub3Insem() {
        return sub3Insem;
    }

    public void setSub3Insem(int sub3Insem) {
        this.sub3Insem = sub3Insem;
    }

    public int getSub3Endsem() {
        return sub3Endsem;
    }

    public void setSub3Endsem(int sub3Endsem) {
        this.sub3Endsem = sub3Endsem;
    }

    public int getSub4Insem() {
        return sub4Insem;
    }

    public void setSub4Insem(int sub4Insem) {
        this.sub4Insem = sub4Insem;
    }

    public int getSub4Endsem() {
        return sub4Endsem;
    }

    public void setSub4Endsem(int sub4Endsem) {
        this.sub4Endsem = sub4Endsem;
    }

    public int getSub5Insem() {
        return sub5Insem;
    }

    public void setSub5Insem(int sub5Insem) {
        this.sub5Insem = sub5Insem;
    }

    public int getSub5Endsem() {
        return sub5Endsem;
    }

    public void setSub5Endsem(int sub5Endsem) {
        this.sub5Endsem = sub5Endsem;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
