package com.web.fasad;

import com.web.jar.exceptions.LogicException;
import com.web.jar.exceptions.UserLogicException;
import com.web.jar.model.Post;
import com.web.jar.model.Topic;
import com.web.jar.model.User;
import com.web.jar.services.UserService;
import com.web.forms.PostForm;
import com.web.forms.TopicForm;
import com.web.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.web.jar.validation.ValidationUsersParametrs.validationPassword;

@Component
public class UserFasad {

    @Autowired
    private UserService userService;

    public User buildUser(UserForm userForm){

        User user = new User();
        user.setID(userForm.getId());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setRole(userForm.getRole());
        user.setPassword(userForm.getPassword());
        user.setImage(userForm.getImage());
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

        try{
            validationPassword(userForm.getPassword());
        }catch (UserLogicException e){
            throw new LogicException(e);
        }

        userForm.setPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt(10)));
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

    public void delete(long id){
        userService.delete(id);
    }

    public UserForm get(long id){

        UserForm userForm = new UserForm(userService.get(id));
        return userForm;
    }

    public void update(UserForm userForm) throws LogicException {

        User user = buildUser(userForm);
        userService.modify(user);
    }
}
