package com.web.interceptors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

public class UserIsLoggedInInterceptor extends WebContentInterceptor {

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//        if(request.getSession().getAttribute("user")==null){
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
//            requestDispatcher.forward(request,response);
//            return false;
//        }
        return true;
    }
}
