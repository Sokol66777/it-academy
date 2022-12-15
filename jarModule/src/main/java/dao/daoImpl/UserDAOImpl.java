package dao.daoImpl;

import dao.BaseDAO;
import dao.UserDAO;
import exceptions.UserLogicException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.User;
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
        User userCheck;
        user.setRole("user");

        userCheck = getByEmail(user.getEmail());
        if (userCheck != null) {
            throw new UserLogicException("this email used");
        }

        userCheck = getByUsername(user.getUsername());
        if (userCheck != null) {
            throw new UserLogicException("this username used");
        }
        entityManager.persist(user);

    }
    @Transactional
    @Override
    public void modify(User user) throws UserLogicException {

        User userCheck;
        User userOld = super.get(user.getID());

        if(!userOld.getUsername().equals(user.getUsername())){

            userCheck = getByUsername(user.getUsername());
            if (userCheck != null) {
                throw new UserLogicException("this username used");
            }
        }

        if(!userOld.getEmail().equals(user.getEmail())){

            userCheck = getByEmail(user.getEmail());
            if (userCheck != null) {
                throw new UserLogicException("this email used");
            }
        }
        entityManager.merge(user);
    }

}
