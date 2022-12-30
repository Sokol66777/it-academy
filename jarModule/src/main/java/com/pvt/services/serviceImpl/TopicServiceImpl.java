package com.pvt.services.serviceImpl;

import com.pvt.dao.TopicDAO;
import com.pvt.exceptions.LogicException;
import com.pvt.exceptions.TopicLogicException;
import com.pvt.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pvt.services.BaseService;
import com.pvt.services.TopicService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl extends BaseService<Topic> implements TopicService {

    @Autowired
    private TopicDAO topicDAOService;

    @Override
    public List<Topic> getAll() {

        return topicDAOService.getAll();
    }

    @Override
    public Topic getByName(String name) {

        return topicDAOService.getByName(name);
    }

    @Transactional
    @Override
    public void add(Topic topic) throws LogicException {

        Topic topicCheck;
        topicCheck = topicDAOService.getByName(topic.getName());
        if(topicCheck!=null){
            throw new TopicLogicException("Topic with this name is create");
        }
        topicDAOService.add(topic);
    }
}
