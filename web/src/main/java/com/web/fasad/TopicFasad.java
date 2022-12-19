package com.web.fasad;


import com.pvt.model.Topic;
import com.pvt.services.TopicService;
import com.pvt.services.UserService;
import com.web.forms.TopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicFasad {

    @Autowired
    private TopicService topicService;

    public List<TopicForm> getAllTopics(){

        List<TopicForm> topicForms = new ArrayList<>();
        List<Topic> topics = topicService.getAll();
        for(Topic topic:topics){
            topicForms.add(new TopicForm(topic));
        }
        return topicForms;
    }
}
