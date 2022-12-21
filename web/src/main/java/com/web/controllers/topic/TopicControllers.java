package com.web.controllers.topic;


import com.pvt.exceptions.LogicException;
import com.web.fasad.PostFasad;
import com.web.fasad.TopicFasad;
import com.web.fasad.UserFasad;
import com.web.forms.PostForm;
import com.web.forms.TopicForm;
import com.web.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = {"/topic"})
public class TopicControllers {

    @Autowired
    UserFasad userFasad;

    @Autowired
    TopicFasad topicFasad;

    @Autowired
    PostFasad postFasad;

    @GetMapping(value = {"/preAdd"})
    public ModelAndView preAdd(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("addTopic");
        modelAndView.addObject("addTopicForm",new TopicForm());
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        if(userForm.getRole().equals("user")){

            List<TopicForm> allTopics=  topicFasad.getAllTopics();
            Set<TopicForm > usersTopic = (userFasad.getUserByIdWithTopic(userForm.getId())).getTopics();

            for(TopicForm topic:usersTopic){
                allTopics.remove(topic);
            }
            request.setAttribute("allTopics",allTopics);
        }
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    public ModelAndView addTopic(@ModelAttribute("addTopicForm") TopicForm addTopicForm, HttpServletRequest request,
                                 @RequestParam(name = "idTopic",required = false,defaultValue = "0") long idTopic){

        UserForm user = (UserForm) request.getSession().getAttribute("user");
        ModelAndView modelAndView;
        if(user.getRole().equals("admin")) {

            try {
                topicFasad.add(addTopicForm);
                request.getSession().setAttribute("allTopics", topicFasad.getAllTopics());
            } catch ( LogicException e) {

                modelAndView=new ModelAndView("addTopic");
                modelAndView.addObject("errorMassage",e.getMessage());
                modelAndView.addObject("addTopicForm",new TopicForm());
                return modelAndView;
            }
        }else{
            TopicForm topic = topicFasad.get(idTopic);
            Set<TopicForm> topicSet = user.getTopics();
            topicSet.add(topic);

            try {
                userFasad.update(user);
            } catch (LogicException e) {

                modelAndView=new ModelAndView("addTopic");
                modelAndView.addObject("errorMassage",e.getMessage());
                modelAndView.addObject("addTopicForm",new TopicForm());
                return modelAndView;
            }
            request.getSession().setAttribute("user",userFasad.getUserByIdWithTopic(user.getId()));
        }
        modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @GetMapping(value = {"/delete"})
    public ModelAndView deleteTopic(@ModelAttribute(name = "deleteTopicID") long idTopic, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("welcome");
        UserForm userWithTopic = (UserForm) request.getSession().getAttribute("user");
        Set<TopicForm> topicsForm = userWithTopic.getTopics();
        List<PostForm> deletePosts = postFasad.getPostsByUserTopic(userWithTopic.getId(), idTopic);

        for (PostForm deletePost:deletePosts){
            postFasad.deletePost(deletePost.getId());
        }

        TopicForm deleteTopic = topicFasad.get(idTopic);
        topicsForm.remove(deleteTopic);

        try {

            userFasad.update(userWithTopic);
            request.getSession().setAttribute("userWithTopic",userFasad.getUserByIdWithTopic(userWithTopic.getId()));
        } catch (LogicException e) {
            modelAndView.addObject("errorMassage",e.getMessage());
        }
        return modelAndView;
    }
}
