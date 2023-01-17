package com.web.jar.services.serviceImpl;

import com.web.jar.exceptions.LogicException;
import com.web.jar.exceptions.TopicLogicException;
import com.web.jar.model.Topic;
import com.web.jar.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.jar.services.BaseService;
import com.web.jar.services.TopicService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl extends BaseService<Topic,Long> implements TopicService {

    @Autowired
    private TopicRepository topicRepositoryService;

    @Override
    public List<Topic> getAll() {

        return topicRepositoryService.findAll();
    }

    @Override
    public Topic getByName(String name) {

        return topicRepositoryService.findByName(name).orElse(null);
    }

    @Transactional
    @Override
    public void add(Topic topic) throws LogicException {

        Topic topicCheck;
        topicCheck = topicRepositoryService.findByName(topic.getName()).orElse(null);
        if(topicCheck!=null){
            throw new TopicLogicException("Topic with this name is create");
        }
        topicRepositoryService.save(topic);
    }
}
