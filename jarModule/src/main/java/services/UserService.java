package services;

import model.User;

import java.util.List;

public interface UserService extends IService<User>{

    List<User> getAllUsers() ;

    User getByUsername(String username);

    User getByEmail(String email) ;

    User getUserByIdWithTopic(long id);

}
