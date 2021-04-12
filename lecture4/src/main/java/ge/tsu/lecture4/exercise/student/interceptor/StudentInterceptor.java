package ge.tsu.lecture4.exercise.student.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("DELETE") || request.getMethod().equals("POST")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0)
                for (Cookie cookie : cookies)
                    if (cookie.getName().equals("token") && cookie.getValue().equals("uNgAbUnGa"))
                        return true;
            else return false;
        }
        return true;
    }
}
