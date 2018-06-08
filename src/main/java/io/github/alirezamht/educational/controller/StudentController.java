package io.github.alirezamht.educational.controller;

import io.github.alirezamht.educational.model.Course;
import io.github.alirezamht.educational.model.Student;
import io.github.alirezamht.educational.service.CourseService;
import io.github.alirezamht.educational.service.StudentService;
import io.github.alirezamht.educational.util.ResponseFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
    protected RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    @GetMapping(value = "selectCourse" , params = {"studentNumber","session","id"})
    public @ResponseBody
    JSONObject selectCourse (@RequestParam("studentNumber") String studentNumber,@RequestParam("session") String session,
                             @RequestParam("id") String id , HttpServletResponse response){
        try {
            //based on authentication port (8080) authenticate the sessions
            String url = "http://localhost:8081/authenticate?" +"studentNumber="+studentNumber+"&session="+session;
            JSONObject authenticate  = restTemplate.getForObject(url,JSONObject.class);
            if(200 == (Integer) authenticate.get("status")){
                Course course =courseService.getCourseById(Long.valueOf(id).longValue());
                Student student = studentService.getStudentByStdNo(studentNumber);
                if(course.getCapacity()>0 ){
                    if (course.addStudentToCourse(student) && student.addCourseToStudent(course)) {
                        course.setCapacity(course.getCapacity() - 1);

                        courseService.update(course);
                        response.setStatus(HttpStatus.OK.value());
                        //Long a = courseService.getCountCourses();
                        // JSONObject result = courseService.getCourseById(Long.valueOf(id).longValue()).getJson();
                        JSONObject result = new JSONObject();
                        result.put("message", "course added to list successfully");
                        return ResponseFactory.getSuccessResponse(HttpStatus.OK.value()
                                , result);
                    }else{
                        JSONObject result = new JSONObject();
                        result.put("message","error.the course has been added to unites");
                        return ResponseFactory.getSuccessResponse(HttpStatus.BAD_REQUEST.value()
                                , result);
                    }
                }else{
                    JSONObject result = new JSONObject();
                    result.put("message","the course is full");
                    return ResponseFactory.getSuccessResponse(HttpStatus.BAD_REQUEST.value()
                            , result);
                }
            }
            return null;
        }catch (Exception e){
            if(e.toString().equals("org.springframework.web.client.HttpClientErrorException: 401 null")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return ResponseFactory.getErrorResponse(HttpStatus.UNAUTHORIZED.value()
                        ,"session expired"
                        ,"user session is not valid!need login to access."
                        ,"/authenticate");
            }else{
                e.printStackTrace();
            }
        }
        return null;
    }


    @GetMapping(value = "deleteCourse" , params = {"studentNumber","session","id"})
    public @ResponseBody
    JSONObject deleteCourse (@RequestParam("studentNumber") String studentNumber,@RequestParam("session") String session,
                             @RequestParam("id") String id , HttpServletResponse response){
        try {
            //based on authentication port (8080) authenticate the sessions
            String url = "http://localhost:8081/authenticate?" +"studentNumber="+studentNumber+"&session="+session;
            JSONObject authenticate  = restTemplate.getForObject(url,JSONObject.class);
            if(200 == (Integer) authenticate.get("status")){
                Course course =courseService.getCourseById(Long.valueOf(id).longValue());
                Student student = studentService.getStudentByStdNo(studentNumber);
                    if( course.removeStudentToCourse(student) && student.removeCourseToStudent(course) ) {
                        course.setCapacity(course.getCapacity() + 1);
                        courseService.update(course);
                        response.setStatus(HttpStatus.OK.value());
                        //Long a = courseService.getCountCourses();
                        // JSONObject result = courseService.getCourseById(Long.valueOf(id).longValue()).getJson();
                        JSONObject result = new JSONObject();
                        result.put("message", "course removed from list successfully");
                        return ResponseFactory.getSuccessResponse(HttpStatus.OK.value()
                                , result);
                    }else{
                    JSONObject result = new JSONObject();
                    result.put("message","error.No course with the specefic id");
                    return ResponseFactory.getSuccessResponse(HttpStatus.NOT_FOUND.value()
                            , result);
                }
            }
            return null;
        }catch (Exception e){
            if(e.toString().equals("org.springframework.web.client.HttpClientErrorException: 401 null")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return ResponseFactory.getErrorResponse(HttpStatus.UNAUTHORIZED.value()
                        ,"session expired"
                        ,"user session is not valid!need login to access."
                        ,"/authenticate");
            }else{
                e.printStackTrace();
            }
        }
        return null;
    }


}
