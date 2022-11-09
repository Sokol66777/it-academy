package userImpl;

import dao.AbstractJPADAO;
import dao.UserDAO;
import exceptions.LogicException;
import exceptions.UserLogicException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.User;

import java.util.List;

public class UserDAOImpl extends AbstractJPADAO implements UserDAO {


    @Override
    public List<User> getAllUsers() {

        init();
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAllUsers", User.class);
        List<User> users = namedQuery.getResultList();
        close();
        return users;
    }

    @Override
    public User getByUsername(String username){

        User user;
        try {
            init();
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByUsername", User.class).
                    setParameter("username", username);
            user = namedQuery.getSingleResult();
            close();
        }catch (NoResultException e){
            close();
            return null;
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user;
        try {
            init();
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByEmail", User.class).
                    setParameter("email", email);
            user = namedQuery.getSingleResult();
            close();
        }catch(NoResultException e){
            close();
            return null;
        }
        return user;
    }

    @Override
    public User getUserByIdWithTopic(long id) {
        User user;
        try{
            init();
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getUserByIDWithTopic", User.class).
                    setParameter("id",id);
            user = namedQuery.getSingleResult();
            close();
        }catch (NoResultException e){
            close();
            return null;
        }
        return user;
    }

    @Override
    public void delete(long id)  {

        init();
        User deleteUser = entityManager.find(User.class,id);
        entityManager.remove(deleteUser);
        close();

    }


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
        init();
        entityManager.persist(user);
        close();


    }

    @Override
    public void modify(User user) throws UserLogicException {
        User userCheck;
        User userOld = get(user.getID());

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
        init();
        entityManager.merge(user);
        close();
    }

    @Override
    public User get(long ID)  {

        init();
        User user = entityManager.find(User.class,ID);
        close();
        return user;

    }
}
