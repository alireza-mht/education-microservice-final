package io.github.alirezamht.educational.controller;

import io.github.alirezamht.educational.model.Session;
import io.github.alirezamht.educational.model.Student;
import io.github.alirezamht.educational.service.SessionService;
import io.github.alirezamht.educational.service.StudentService;
import io.github.alirezamht.educational.util.CryptoHelper;
import io.github.alirezamht.educational.util.ResponseFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("/student")
public class StudentAuthController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SessionService sessionService;


    @GetMapping(value = "login",params = {"studentNumber","password"})
    public @ResponseBody JSONObject login(@RequestParam("studentNumber") String studentNumber, @RequestParam("password") String password, HttpServletResponse response){
        Student s=studentService.getStudentByStdNo(studentNumber);
        if(s==null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseFactory.getErrorResponse(HttpStatus.NOT_FOUND.value()
                    ,"username not found"
                    ,"no student exists with entered student number"
                    ,"/student/login");

        }else if(!s.getPassword().equals(password)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseFactory.getErrorResponse(HttpStatus.UNAUTHORIZED.value()
                    ,"wrong password"
                    ,"login for User {USER}: invalid password".replace("{USER}",studentNumber)
                    ,"/student/login");
        }else {
            response.setStatus(HttpStatus.OK.value());
            JSONObject result=new JSONObject();
            Session session;
            ArrayList<Session> savedSessions= (ArrayList<Session>) sessionService.getSessionByUserIdentifier(studentNumber);
            if(savedSessions!=null)
                savedSessions.sort(Comparator.comparing(Session::getExpireTime));
            Session savedSession=savedSessions!=null && savedSessions.size()>0 ? savedSessions.get(0) :null;

            if(savedSession!=null && savedSession.getExpireTime()>System.currentTimeMillis()){
                savedSession.setExpireTime(System.currentTimeMillis()+(60*10*1000));
                session=savedSession;
                sessionService.update(session);
                result.put("status","refreshed");
            }else {
                session = getNewSession(studentNumber);
                sessionService.save(session);
                result.put("status","new");
            }
            String ss =session.getJson().toString();
            try {
                ss=CryptoHelper.encrypt(ss);
                result.put("session",ss);
            } catch (Exception e) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseFactory.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()
                        ,"internal server error"
                        ,e.getMessage()
                        ,"/authenticate");
            }
            return ResponseFactory.getSuccessResponse(HttpStatus.OK.value(),result);
        }
    }

    @GetMapping("/")
    public String root(){
        return "Student Controller";
    }

    @GetMapping(value = "create")
    public String createSampleStudent(){

        Student s=new Student("mehdi","akbarian","مهدی",
                "اکبریان",1,"09368640180",1,"Software Eng."
                ,"نرم افزاز","2080631000","9312430000","123456");
        studentService.save(s);

        return "done";
    }

    private Session getNewSession(String userIdentifier){
        Session session=new Session(System.currentTimeMillis()
                ,System.currentTimeMillis()+(10*60*1000)
                ,CryptoHelper.getRandomSession(),userIdentifier,"All");
        return session;
    }
}
