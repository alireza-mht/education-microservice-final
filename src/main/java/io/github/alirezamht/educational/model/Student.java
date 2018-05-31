package io.github.alirezamht.educational.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public String getPersianFirstName() {
        return persianFirstName;
    }

    public void setPersianFirstName(String persianFirstName) {
        this.persianFirstName = persianFirstName;
    }

    public String getPersianLastName() {
        return persianLastName;
    }

    public void setPersianLastName(String persianLastName) {
        this.persianLastName = persianLastName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPersianFieldName() {
        return persianFieldName;
    }

    public void setPersianFieldName(String persianFieldName) {
        this.persianFieldName = persianFieldName;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
