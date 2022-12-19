package com.web.controllers.user;


import com.web.fasad.UserFasad;
import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    UserFasad userFasad;


    @GetMapping(value = {"/logout"})
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    @GetMapping(value = {"/allUsers"})
    public ModelAndView allUsers(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String adminName = (String) request.getParameter("adminName");
        List<UserForm> trueUsers = new ArrayList<>();
        List<UserForm> userForms = null;
        userForms = userFasad.getAllUsers();

        if(userForms!=null) {
            for (UserForm user : userForms) {
                if (!user.getUsername().equals(adminName)) {
                    trueUsers.add(user);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("users");
        return modelAndView;
    }
}
