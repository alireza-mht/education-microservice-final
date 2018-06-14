package io.github.alirezamht.educational.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String persianFirstName;
    @Column(nullable = false)
    private String persianLastName;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private int field;
    @Column(nullable = false)
    private String fieldName;
    @Column(nullable = false)
    private String persianFieldName;
    @Column(nullable = false)
    private String nationalNumber;
    @Column(nullable = false,unique = true)
    private String studentNumber;
    @Column(nullable = false)
    private String password;



    @ManyToMany(cascade = {CascadeType.ALL},targetEntity = Course.class,fetch = FetchType.LAZY)
    @JoinTable(
            name = "stu_par",
            joinColumns ={ @JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses = new ArrayList<>();


    public List<Course> getCourseSet() {
        return courses;
    }

    public Student(Long id, String firstName, String lastName, String persianFirstName, String persianLastName, int type, String phoneNumber, int field, String fieldName, String persianFieldName, String nationalNumber, String studentNumber,String password) {
        this(firstName, lastName, persianFirstName, persianLastName, type, phoneNumber, field, fieldName, persianFieldName, nationalNumber, studentNumber,password);
        this.id = id;

    }

    public Student(String firstName, String lastName, String persianFirstName, String persianLastName, int type, String phoneNumber, int field, String fieldName, String persianFieldName, String nationalNumber, String studentNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.persianFirstName = persianFirstName;
        this.persianLastName = persianLastName;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.field = field;
        this.fieldName = fieldName;
        this.persianFieldName = persianFieldName;
        this.nationalNumber = nationalNumber;
        this.studentNumber = studentNumber;
        this.password=password;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", persianFirstName='" + persianFirstName + '\'' +
                ", persianLastName='" + persianLastName + '\'' +
                ", type=" + type +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", field=" + field +
                ", fieldName='" + fieldName + '\'' +
                ", persianFieldName='" + persianFieldName + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }

    public boolean addCourseToStudent(Course course){
        if (!course.getStudentSet().contains(this)) {
            this.getCourseSet().add(course);
            course.getStudentSet().add(this);
            return true;
        }else return false;
    }

    public boolean removeCourseFromStudent(Course course){
        if (course.getStudentSet().contains(this)) {
            this.getCourseSet().remove(course);
            course.getStudentSet().remove(this);
            return true;
        }else return false;
    }

}
