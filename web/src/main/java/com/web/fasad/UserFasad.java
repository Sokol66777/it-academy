package com.web.fasad;

import com.pvt.exceptions.LogicException;
import com.pvt.model.Post;
import com.pvt.model.Topic;
import com.pvt.model.User;
import com.pvt.services.UserService;
import com.web.forms.PostForm;
import com.web.forms.TopicForm;
import com.web.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserFasad {

    @Autowired
    private UserService userService;

    public User buildUser(UserForm userForm){
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setRole(userForm.getRole());
        user.setPassword(userForm.getPassword());
        Set<Topic> topics = new HashSet<>();
        for(TopicForm topicForm:userForm.getTopics()){
            Topic topic = new Topic();
            topic.setID(topicForm.getId());
            topic.setName(topicForm.getName());
            Set<Post> posts = new HashSet<>();
            for(PostForm postForm: topicForm.getPosts()){
                Post post = new Post();
                post.setID(postForm.getId());
                post.setName(postForm.getName());
                post.setText(postForm.getText());
                posts.add(post);
            }
            topic.setPosts(posts);
            topics.add(topic);
        }
        user.setTopics(topics);

        return user;
    }
    public UserForm getByUsername(String username){
        User user = userService.getByUsername(username);

        if(user==null){

            return null;
        }
        return new UserForm(user);
    }

    public UserForm getUserByIdWithTopic(long id){
        User user = userService.getUserByIdWithTopic(id);
        if(user==null){

            return null;
        }
        return new UserForm(user);
    }

    public void addUser(UserForm userForm) throws LogicException {
        User user = buildUser(userForm);
        userService.add(user);
    }

    public List<UserForm> getAllUsers(){
        List<UserForm> userForms = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        for(User user:users){
            UserForm userForm = new UserForm(user);
            userForms.add(userForm);
        }
        return userForms;
    }

}
