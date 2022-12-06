package services;

import model.Topic;

import java.util.List;

public interface TopicService extends IService<Topic>{

    public List<Topic> getAll();

    public Topic getByName(String name);
}
