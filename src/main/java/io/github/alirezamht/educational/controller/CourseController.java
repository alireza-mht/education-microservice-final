package io.github.alirezamht.educational.controller;

import com.sun.javafx.util.Logging;
import io.github.alirezamht.educational.util.ResponseFactory;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@RestController
@RequestMapping("/courses")
public class CourseController {
    protected RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value ="show" , params = {"studentNumber" , "session" , "id"  } )
    public @ResponseBody JSONObject showCourse(@RequestParam("studentNumber") String studentNumber
            , @RequestParam("session") String session , HttpServletResponse response) {

        try {
            //based on authentication port (8080) authenticate the sessions
           String url = "http://localhost:8080/authenticate?" +"studentNumber="+studentNumber+"&session="+session;
           JSONObject authenticate  = restTemplate.getForObject(url,JSONObject.class);
           if(200 == (Integer) authenticate.get("status")){
               response.setStatus(HttpStatus.OK.value());
               JSONObject result = new JSONObject();

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