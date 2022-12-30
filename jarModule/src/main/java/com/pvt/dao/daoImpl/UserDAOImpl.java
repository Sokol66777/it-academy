package com.pvt.dao.daoImpl;

import com.pvt.dao.BaseDAO;
import com.pvt.exceptions.UserLogicException;
import com.pvt.dao.UserDAO;
import com.pvt.validation.ValidationUsersParametrs;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import com.pvt.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    public UserDAOImpl(){
        super();
        clazz = User.class;
    }

    @Override
    public List<User> getAllUsers() {

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAllUsers", clazz);
        List<User> users = namedQuery.getResultList();
        return users;
    }

    @Override
    public User getByUsername(String username){

        User user;
        try {
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByUsername", clazz).
                    setParameter("username", username);
            user = namedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {

        User user;
        try {
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByEmail", clazz).
                    setParameter("email", email);
            user = namedQuery.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
        return user;
    }

    @Override
    public User getUserByIdWithTopic(long id) {

        User user;
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByIDWithTopic",clazz).
                setParameter("id",id);
        user = namedQuery.getSingleResult();

        return user;
    }

    @Transactional
    @Override
    public void add(User user) throws UserLogicException {

        entityManager.persist(user);

    }
    @Transactional
    @Override
    public void modify(User user) throws UserLogicException {

        entityManager.merge(user);
    }

}
