package com.pvt.dao.daoImpl;

import com.pvt.dao.BaseDAO;
import com.pvt.exceptions.TopicLogicException;
import com.pvt.dao.TopicDAO;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import com.pvt.model.Topic;
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
