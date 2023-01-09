package com.pvt.services.serviceImpl;

import com.pvt.exceptions.LogicException;
import com.pvt.exceptions.TopicLogicException;
import com.pvt.model.Topic;
import com.pvt.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pvt.services.BaseService;
import com.pvt.services.TopicService;
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
