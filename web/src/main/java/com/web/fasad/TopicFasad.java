package com.web.fasad;


import com.pvt.exceptions.LogicException;
import com.pvt.model.Post;
import com.pvt.model.Topic;
import com.pvt.services.TopicService;
import com.pvt.services.UserService;
import com.web.forms.PostForm;
import com.web.forms.TopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TopicFasad {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    public Topic buildTopic(TopicForm topicForm){

        Topic topic  =new Topic();
        topic.setName(topicForm.getName());
        topic.setID(topicForm.getId());
        Set<Post> posts = new HashSet<>();
        for(PostForm postForm: topicForm.getPosts()){

            Post post = new Post();
            post.setID(postForm.getId());
            post.setText(postForm.getText());
            post.setName(postForm.getName());
            post.setUser(userService.get(postForm.getIdUser()));
            post.setTopic(topicService.get(topicForm.getId()));
            posts.add(post);
        }
        topic.setPosts(posts);
        return topic;
    }

    public List<TopicForm> getAllTopics(){

        List<TopicForm> topicForms = new ArrayList<>();
        List<Topic> topics = topicService.getAll();
        for(Topic topic:topics){
            topicForms.add(new TopicForm(topic));
        }
        return topicForms;
    }

    public void add(TopicForm topicForm) throws LogicException {
        Topic topic = buildTopic(topicForm);
        topicService.add(topic);
    }

    public TopicForm get(long id){

        Topic topic = topicService.get(id);
        return new TopicForm(topic);
    }
}
