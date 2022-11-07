package dao;

import model.Topic;

import java.util.List;
import java.util.Set;

public interface TopicDAO extends DAO<Topic>{
    public List<Topic> getAll();
    public Topic getByName(String name);


}
