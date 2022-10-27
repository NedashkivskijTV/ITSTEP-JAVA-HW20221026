package org.example;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "address")
    private String examAddress;

    @Column(name = "exam_date")
    private Calendar examDateTime;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "exams_students", joinColumns = @JoinColumn(name = "exams_id"), inverseJoinColumns = @JoinColumn(name = "students_id"))
    private List<Student> students = new ArrayList<>();

    public Exam() {
    }

    public Exam(String examName, String examAddress, Calendar examDateTime) {
        this.examName = examName;
        this.examAddress = examAddress;
        this.examDateTime = examDateTime;
    }

    public int getId() {
        return id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamAddress() {
        return examAddress;
    }

    public void setExamAddress(String examAddress) {
        this.examAddress = examAddress;
    }

    public Calendar getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(Calendar examDateTime) {
        this.examDateTime = examDateTime;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("EEEE, d MMMM yyyy - HH:mm");
        return "Exam{" +
                "id=" + id +
                ", examName='" + examName + '\'' +
                ", examAddress='" + examAddress + '\'' +
                ", examDateTime= " + formater.format(examDateTime.getTime()) +
                '}';
    }

    public void addStudent(Student... newStudents) {
        Collections.addAll(students, newStudents);
        //students.add(student);
    }
}
