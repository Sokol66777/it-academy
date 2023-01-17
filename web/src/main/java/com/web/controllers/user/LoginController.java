package com.web.controllers.user;

import com.web.fasad.EmailService;
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

import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserFasad userFasad;
    @Autowired
    TopicFasad topicFasad;

    @Autowired
    EmailService emailService;

    @GetMapping(value = {"/start"})
    public ModelAndView startApp(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginForm", new UserForm());
        return modelAndView;
    }

    @GetMapping
    public void login(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath()+"/login");
    }


    @PostMapping(value = {"/loginS"})
    public ModelAndView welcome(@ModelAttribute("loginForm") UserForm loginForm, HttpServletRequest request){

        UserForm user = null;
        ModelAndView modelAndView;

        try {
            user=userFasad.getByUsername(loginForm.getUsername());
        }catch (Exception e){
        }

        modelAndView = new ModelAndView("welcome");

        if(user.getImage()!=null){

            request.getSession().setAttribute("imageForm",user);
        }
        if (user.getRole().equals("admin")) {

            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("allTopics", topicFasad.getAllTopics());
        } else {

            UserForm userWithTopic = userFasad.getUserByIdWithTopic(user.getId());
            request.getSession().setAttribute("user", userWithTopic);
        }

        emailService.sendEmail("Sokol66777@mail.ru", user.getEmail(), "login", "user "+user.getUsername()+" login in APP");
        return modelAndView;
    }
}
