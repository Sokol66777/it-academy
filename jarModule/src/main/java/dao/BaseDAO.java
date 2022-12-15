package dao;

import exceptions.LogicException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BaseDAO<T> implements DAO<T>{

    protected Class<T> clazz;

    @PersistenceContext
    @Getter
    protected EntityManager entityManager;

    @Transactional
    @Override
    public void delete(long id) {

        T t = entityManager.find(clazz,id);
        entityManager.remove(t);
    }

    @Transactional
    @Override
    public void add(T t) throws LogicException {

        entityManager.persist(t);
    }

    @Transactional
    @Override
    public void modify(T t) throws LogicException {

        entityManager.merge(t);
    }

    @Override
    public T get(long id) {
        return entityManager.find(clazz,id);
    }
}
