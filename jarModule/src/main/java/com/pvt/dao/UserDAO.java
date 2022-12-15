package com.pvt.dao;


import com.pvt.model.User;


import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getAllUsers() ;
    User getByUsername(String username);
    User getByEmail(String email) ;
    User getUserByIdWithTopic(long id);

}
