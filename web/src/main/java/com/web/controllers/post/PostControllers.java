package com.web.controllers.post;

import com.web.jar.exceptions.LogicException;
import com.web.fasad.PostFasad;
import com.web.forms.PostForm;
import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostControllers {

    @Autowired
    PostFasad postFasad;

    @GetMapping(value = {"/postsOfTopic"})
    public ModelAndView postsOfTopic(@ModelAttribute(value = "idTopic") long idTopic, @ModelAttribute(value = "idUser") long idUser,
                                     HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("postsOfTopic");
        modelAndView.addObject("idTopic",idTopic);
        List<PostForm> posts = postFasad.getPostsByUserTopic(idUser,idTopic);
        request.setAttribute("posts",posts);
        return modelAndView;
    }

    @GetMapping(value = {"/deletePost"})
    public void deletePost(@RequestParam(value = "idPost") long idPost, @RequestParam(value = "idTopic") long idTopic,
                           HttpServletResponse response, HttpServletRequest request) throws IOException {

        postFasad.deletePost(idPost);
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        response.sendRedirect(request.getContextPath()+"/post/postsOfTopic?idTopic="+idTopic+"&idUser="+userForm.getId());

    }

    @GetMapping(value = {"/preAdd"})
    public ModelAndView preAdd(@RequestParam(value = "idTopic") long idTopic){

        ModelAndView modelAndView = new ModelAndView("addPost");
        modelAndView.addObject("idTopic",idTopic);
        modelAndView.addObject("addPostForm",new PostForm());
        return modelAndView;
    }

    @PostMapping(value = {"/addPost"})
    public ModelAndView addPost(@ModelAttribute(value = "addPostForm") PostForm addPostForm, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        ModelAndView modelAndView= new ModelAndView("addPost");;
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        addPostForm.setIdUser(userForm.getId());
        try{
            postFasad.addPost(addPostForm);
            response.sendRedirect(request.getContextPath()+"/post/postsOfTopic?idTopic="+addPostForm.getIdTopic()+
                    "&idUser="+addPostForm.getIdUser());
        } catch (LogicException e) {

            modelAndView.addObject("addPostForm", new PostForm());
            modelAndView.addObject("errorMassage","Incorrect name or password");
        }

        return modelAndView;
    }

    @GetMapping(value = {"/preUpdate"})
    public ModelAndView preUpdate(@RequestParam(value = "idPost") long idPost){

        PostForm updatePostForm = postFasad.get(idPost);
        ModelAndView modelAndView = new ModelAndView("updatePost");
        modelAndView.addObject("updatePostForm",updatePostForm);
        return modelAndView;
    }

    @PostMapping(value = {"/updatePost"})
    public ModelAndView updatePost(@ModelAttribute("updatePostForm") PostForm updatePostForm, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

        ModelAndView modelAndView = new ModelAndView("updatePost");
        try {
            postFasad.updatePost(updatePostForm);
            response.sendRedirect(request.getContextPath()+"/post/postsOfTopic?idTopic="+updatePostForm.getIdTopic()+
                    "&idUser="+updatePostForm.getIdUser());

        } catch (LogicException e) {
            modelAndView.addObject("updatePostForm",updatePostForm);
            modelAndView.addObject("errorMassage",e.getMessage());
        }
        return modelAndView;
    }

}
