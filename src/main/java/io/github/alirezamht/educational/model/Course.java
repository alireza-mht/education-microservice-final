package io.github.alirezamht.educational.model;


import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long iD;
    @Column(nullable = false,updatable = false)
    private Long Number;
    @Column(nullable = false)
    private Long GroupNo;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private Long Unite;
    @Column(nullable = false)
    private Long capacity;
////
////    @org.springframework.data.annotation.Transient
////    private Long maxCapecity;
//
//    public Long getMaxCapecity() {
//        return maxCapecity;
//    }
//
//    public void setMaxCapecity(Long maxCapecity) {
//        this.maxCapecity = maxCapecity;
//    }

  //  private static Long maxCapecity;

    @ManyToMany(mappedBy = "courseSet")
    private Set <Student> studentSet = new HashSet<>();


    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getiD() {
        return iD;
    }

    public void setID(Long ID) {
        this.iD = ID;
    }

    public Course(Long number, Long groupNo, String name, Long unite, Long capacity) {
        Number = number;
        GroupNo = groupNo;
        Name = name;
        Unite = unite;
        //this.maxCapecity=capacity;
        this.capacity = capacity;
    }

    public Course() {
    }

//    public static Course getModel(JSONObject object){
//        Long Number  = (Long) object.get("Number");
//        Long GroupNo  = (Long) object.get("GroupNo");
//        Long Unite  = (Long) object.get("Unite");
//        Long Capacity  = (Long) object.get("Capacity");
//        String Name = (String) object.get("Name");
//
//        Course course = new Course(Number , GroupNo , Name , Unite , Capacity);
//
//        return course;
//
//    }

    public JSONObject getJson(){
        JSONObject object=new JSONObject();
        //object.put("id",id);
        object.put("Number",Number);
        object.put("GroupNo",GroupNo);
        object.put("Unite",Unite);
        object.put("Capacity",capacity);
        object.put("Name",Name);
        return object;
    }

    public boolean addStudentToCourse(Student student){
        if (!studentSet.contains(student)) {
            this.studentSet.add(student);
            return true;
        }else return false;
    }
    public Boolean removeStudentToCourse(Student student){
        if (studentSet.contains(student)) {
            this.studentSet.remove(student);
            return true;
        }else return false;
    }
}
