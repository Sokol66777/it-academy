package com.web.controllers.user;


import com.pvt.exceptions.LogicException;
import com.pvt.exceptions.UserLogicException;
import com.web.fasad.UserFasad;
import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pvt.validation.ValidationUsersParametrs.validationPassword;


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

    @GetMapping(value = {"/welcome"})
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @GetMapping(value = {"/allUsers"})
    public ModelAndView allUsers(HttpServletRequest request,HttpServletResponse response) throws IOException {

        UserForm adminForm =(UserForm) request.getSession().getAttribute("user");
        String adminName = adminForm.getUsername();
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
        modelAndView.addObject("allUsers",trueUsers);
        return modelAndView;
    }

    @GetMapping(value = {"/delete"})
    public void deleteUser(@ModelAttribute("deleteUsersID") long id,HttpServletResponse response, HttpServletRequest request) throws IOException {
        userFasad.delete(id);
        response.sendRedirect(request.getContextPath()+"/user/allUsers");
    }

    @GetMapping(value = {"/preUpdate"})
    public ModelAndView redirectToUpdate(@ModelAttribute("updateUsersID") long updateUsersID){

        UserForm updateUserForm = userFasad.get(updateUsersID);

        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject("updateUserForm",updateUserForm);
        modelAndView.addObject("imageForm",new UserForm());

        return modelAndView;
    }

    @PostMapping(value = {"/update"})
    public ModelAndView updateUser(@ModelAttribute("updateUser") UserForm updateUserForm, HttpServletRequest request) {

        UserForm user = userFasad.getUserByIdWithTopic(updateUserForm.getId());
        UserForm imageForm = (UserForm) request.getSession().getAttribute("imageForm");
        ModelAndView modelAndView;
        if(updateUserForm.getConfirmedPassword()!= null &&!updateUserForm.getConfirmedPassword().equals(updateUserForm.getNewPassword())){

            modelAndView = new ModelAndView("updateUser");
            modelAndView.addObject("errorMassage", "password is not confirmed");
            modelAndView.addObject("updateUserForm",user);
        }else{


            if(imageForm!=null){
                user.setImage(imageForm.getImage());
            }
            user.setEmail(updateUserForm.getNewEmail());
            user.setUsername(updateUserForm.getNewUsername());
            try{
                if(updateUserForm.getNewPassword()!=null && !updateUserForm.getNewPassword().equals("")) {

                    try{
                        validationPassword(updateUserForm.getNewPassword());
                        user.setPassword(BCrypt.hashpw(updateUserForm.getNewPassword(), BCrypt.gensalt(10)));
                    }catch (UserLogicException e){
                        throw new LogicException(e);
                    }
                }

                userFasad.update(user);
                UserForm userFromSession = (UserForm) request.getSession().getAttribute("user");
                if(userFromSession.getId()==updateUserForm.getId()){

                    UserForm userWithTopic = userFasad.getUserByIdWithTopic(user.getId());
                    request.getSession().setAttribute("user", userWithTopic);
                }
                modelAndView = new ModelAndView("welcome");
            } catch (LogicException e) {

                modelAndView = new ModelAndView("updateUser");
                modelAndView.addObject("errorMassage", e.getMessage());
                modelAndView.addObject("updateUserForm",userFasad.get(updateUserForm.getId()));
                modelAndView.addObject("imageForm",new UserForm());
                return modelAndView;
            }
        }
        return modelAndView ;
    }

    @PostMapping(value = {"/uploadPhoto"})
    public ModelAndView uploadPhoto(@ModelAttribute("imageForm") UserForm imageForm, HttpServletRequest request) throws IOException {

        ModelAndView modelAndView;
        UserForm userForm = (UserForm)request.getSession().getAttribute("user");
        imageForm.setImage(imageForm.getFileData().getBytes());
        request.getSession().setAttribute("imageForm",imageForm);
        if(userForm!=null){
            modelAndView = new ModelAndView("updateUser");
            modelAndView.addObject("updateUserForm",userForm);
        }else{
            userForm=new UserForm();
            modelAndView = new ModelAndView("addUser");
            modelAndView.addObject("registrationForm",userForm);
        }
        return modelAndView;
    }

    @GetMapping(value = {"/viewImage"})
    public void viewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm imageForm = (UserForm) request.getSession().getAttribute("imageForm");
        if(imageForm.getImage()!=null) {

            response.setContentType("image/jpg");
            response.getOutputStream().write(imageForm.getImage());
        }
        response.getOutputStream().close();
    }

    @GetMapping(value = {"/imageOnWelcomePage"})
    public void imageOnWelcomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        if (userForm.getImage() != null) {

            response.setContentType("img/jpg");
            response.getOutputStream().write(userForm.getImage());
        }
        response.getOutputStream().close();
    }
}
