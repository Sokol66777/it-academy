package com.web.controllers.user;

import com.web.fasad.TopicFasad;
import com.web.fasad.UserFasad;
import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserFasad userFasad;
    @Autowired
    TopicFasad topicFasad;


    @GetMapping(value = {"/start"})
    public ModelAndView startApp(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginForm", new UserForm());
        return modelAndView;
    }


    @PostMapping(value = {"/login"})
    public ModelAndView welcome(@ModelAttribute("loginForm") UserForm loginForm, HttpServletRequest request){

        UserForm user = userFasad.getByUsername(loginForm.getUsername());
        ModelAndView modelAndView;
        if (user != null && user.getPassword().equals(loginForm.getPassword())) {
            modelAndView = new ModelAndView("welcome");
            if (user.getRole().equals("admin")) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("allTopics", topicFasad.getAllTopics());
            } else {
                UserForm userWithTopic = userFasad.getUserByIdWithTopic(user.getId());
                request.getSession().setAttribute("user", userWithTopic);
            }
        }else{
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("errorMassage","Incorrect name or password");

        }
        return modelAndView;
    }
}
