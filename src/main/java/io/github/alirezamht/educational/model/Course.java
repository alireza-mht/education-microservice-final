package io.github.alirezamht.educational.model;


import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long iD;
    @Column(nullable = false,updatable = false)
    private Long number;
    @Column(nullable = false)
    private Long GroupNo;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private Long Unite;
    @Column(nullable = false)
    private Long capacity;


    @ManyToMany (mappedBy = "courses" ,targetEntity = Student.class,fetch = FetchType.LAZY)
    private List <Student> studentSet = new ArrayList<>();

    public List<Student> getStudentSet() {
        return studentSet;
    }


    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getiD() {
        return iD;
    }


    public Course() {
    }

    public JSONObject getJson(){
        JSONObject object=new JSONObject();
        object.put("Number",number);
        object.put("GroupNo",GroupNo);
        object.put("Unite",Unite);
        object.put("Capacity",capacity);
        object.put("Name",Name);
        return object;
    }

}
