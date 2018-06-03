package io.github.alirezamht.educational.model;


import org.json.simple.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;
    @Column(nullable = false,updatable = false)
    private Long Number;
    @Column(nullable = false)
    private Long GroupNo;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private Long Unite;
    @Column(nullable = false)
    private Long Capacity;


    public Course(Long number, Long groupNo, String name, Long unite, Long capacity) {
        Number = number;
        GroupNo = groupNo;
        Name = name;
        Unite = unite;
        Capacity = capacity;
    }

    public Course() {
    }

    public static Course getModel(JSONObject object){
        Long Number  = (Long) object.get("Number");
        Long GroupNo  = (Long) object.get("GroupNo");
        Long Unite  = (Long) object.get("Unite");
        Long Capacity  = (Long) object.get("Capacity");
        String Name = (String) object.get("Name");

        Course course = new Course(Number , GroupNo , Name , Unite , Capacity);

        return course;

    }

    public JSONObject getJson(){
        JSONObject object=new JSONObject();
        //object.put("id",id);
        object.put("Number",Number);
        object.put("GroupNo",GroupNo);
        object.put("Unite",Unite);
        object.put("Capacity",Capacity);
        object.put("Name",Name);
        return object;
    }
}
