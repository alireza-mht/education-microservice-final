package io.github.alirezamht.educational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication

public class UserEducation {

    public static void main(String[] args){
        SpringApplication.run(UserEducation.class, args);


    }

    @GetMapping("/")

    public String root(){
        return "hello";
    }

}
