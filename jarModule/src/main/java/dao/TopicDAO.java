package dao;

import model.Topic;

import java.util.Set;

public interface TopicDAO extends DAO<Topic>{
    public Set<Topic> getAll();
    public Topic getByName(String name);


}
