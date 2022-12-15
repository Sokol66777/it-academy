package dao.daoImpl;

import dao.BaseDAO;
import dao.TopicDAO;
import exceptions.TopicLogicException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

    public TopicDAOImpl(){
        super();
        clazz= Topic.class;
    }

    @Transactional
    @Override
    public void add(Topic topic) throws TopicLogicException {

        Topic topicCheck;
        topicCheck = getByName(topic.getName());
        if(topicCheck!=null){
            throw new TopicLogicException("Topic with this name is create");
        }
        entityManager.persist(topic);
    }

    @Override
    public List<Topic> getAll() {

        TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getAllTopics", Topic.class);
        List<Topic> topics = namedQuery.getResultList();

        return topics;
    }

    @Override
    public Topic getByName(String name) {

        Topic topic;
        try {
            TypedQuery<Topic> namedQuery = entityManager.createNamedQuery("Topic.getTopicByName", Topic.class).
                    setParameter("name",name);
            topic = namedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

        return topic;
    }
}
