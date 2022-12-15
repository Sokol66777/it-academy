package com.web.controllers;


import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class StartController {

    @GetMapping(value = {"/start"})
    public ModelAndView startApp(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginForm", new UserForm());
        return modelAndView;
    }

    @PostMapping(value = {"/welcome"})
    public void welcome(@ModelAttribute("loginForm") UserForm userForm, HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");


    }
}
