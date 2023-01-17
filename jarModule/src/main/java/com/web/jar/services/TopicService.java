package com.web.jar.services;

import com.web.jar.model.Topic;

import java.util.List;

public interface TopicService extends IService<Topic,Long>{

    public List<Topic> getAll();

    public Topic getByName(String name);
}
