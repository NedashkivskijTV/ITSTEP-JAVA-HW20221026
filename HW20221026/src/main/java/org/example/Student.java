package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "school_number")
    private String schoolInfo;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "exams_students", joinColumns = @JoinColumn(name = "students_id"), inverseJoinColumns = @JoinColumn(name = "exams_id"))
    private List<Exam> exams = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String schoolInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolInfo = schoolInfo;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(String schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public List<Exam> getExams() {
        return exams;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schoolInfo='" + schoolInfo + '\'' +
                '}';
    }

    public void addExam(Exam... newExams){
        Collections.addAll(exams, newExams);
        //exams.add(exam);
    }
}
