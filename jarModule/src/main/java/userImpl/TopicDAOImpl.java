package userImpl;

import dao.AbstractJPADAO;
import dao.TopicDAO;
import exceptions.UserLogicException;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import model.Topic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopicDAOImpl extends AbstractJPADAO implements TopicDAO {
    @Override
    public void delete(long id) {

        init();
        Topic topic = entityManager.find(Topic.class,id);
        entityManager.remove(topic);
        close();
    }

    @Override
    public void add(Topic topic) throws UserLogicException {

        init();
        entityManager.persist(topic);
        close();
    }

    @Override
    public void modify(Topic topic) throws UserLogicException {

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
    public Set<Topic> getAll() {

        init();
        TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getAllTopics", Topic.class);
        List<Topic> topics = namedQuery.getResultList();
        close();
        return new HashSet<>(topics);
    }

    @Override
    public Topic getByName(String name) {

        init();
        TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getTopicByName", Topic.class);
        Topic topic = namedQuery.getSingleResult();
        return topic;
    }
}
