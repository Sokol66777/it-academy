package com.web.controllers.user;


import com.web.jar.exceptions.LogicException;
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
@RequestMapping("/add")
public class RegistrationController {

    @Autowired
    UserFasad userFasad;

    @GetMapping
    public ModelAndView redirectToRegistration(){
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("registrationForm",new UserForm());
        modelAndView.addObject("imageForm", new UserForm());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registrationUser(@ModelAttribute("registrationForm") UserForm registrationForm, HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("welcome");
        UserForm imageForm = (UserForm) request.getSession().getAttribute("imageForm");

        if(!registrationForm.getConfirmedPassword().equals(registrationForm.getPassword())){

            modelAndView = new ModelAndView("addUser");
            modelAndView.addObject("registrationForm",new UserForm());
            modelAndView.addObject("imageForm",new UserForm());
            modelAndView.addObject("errorMassage","password is not confirmed");
        }else{
            try {
                if(imageForm!=null){
                    registrationForm.setImage(imageForm.getImage());
                }
                userFasad.addUser(registrationForm);
                response.sendRedirect(request.getContextPath()+"/user/welcome");
            } catch (LogicException e) {

                modelAndView = new ModelAndView("addUser");
                modelAndView.addObject("errorMassage",e.getMessage());
                modelAndView.addObject("registrationForm",new UserForm());
                modelAndView.addObject("imageForm",new UserForm());
            }

        }
        return modelAndView;
    }
}
