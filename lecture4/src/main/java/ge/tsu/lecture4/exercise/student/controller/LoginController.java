package ge.tsu.lecture4.exercise.student.controller;

import ge.tsu.lecture4.exercise.student.modules.StudentLoginView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @PostMapping("/login")
    void login(@RequestBody StudentLoginView student, HttpServletResponse httpServletResponse){
        if(student.getUsername().equals("atulie") && student.getPassword().equals("xelladze")){
            Cookie cookie = new Cookie("token", "uNgAbUnGa");
            httpServletResponse.addCookie(cookie);
            return;
        }
        Cookie cookie = new Cookie("token", "notuNgAbUnGa");
        httpServletResponse.addCookie(cookie);
    }
}
