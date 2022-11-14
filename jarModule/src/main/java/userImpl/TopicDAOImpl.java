package userImpl;

import dao.AbstractJPADAO;
import dao.TopicDAO;
import exceptions.TopicLogicException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Topic;

import java.util.List;

public class TopicDAOImpl extends AbstractJPADAO implements TopicDAO {
    @Override
    public void delete(long id) {

        init();
        Topic topic = entityManager.find(Topic.class,id);
        entityManager.remove(topic);
        close();
    }

    @Override
    public void add(Topic topic) throws TopicLogicException {

        Topic topicCheck;
        topicCheck = getByName(topic.getName());
        if(topicCheck!=null){
            throw new TopicLogicException("Topic with this name is create");
        }
        init();
        entityManager.persist(topic);
        close();
    }

    @Override
    public void modify(Topic topic) {

        init();
        entityManager.merge(topic);
        close();
    }

    @Override
    public Topic get(long id) {

        init();
        Topic topic = entityManager.find(Topic.class,id);
        close();
        return topic;
    }

    @Override
    public List<Topic> getAll() {

        init();
        TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getAllTopics", Topic.class);
        List<Topic> topics = namedQuery.getResultList();
        close();
        return topics;
    }

    @Override
    public Topic getByName(String name) {
        Topic topic;
        try {
            init();
            TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getTopicByName", Topic.class).
                    setParameter("name",name);
            topic = namedQuery.getSingleResult();
            close();
        }catch (NoResultException e){
            close();
            return null;
        }
        return topic;
    }
}
