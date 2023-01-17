package com.pvt.services;

import com.pvt.model.User;

import java.util.List;

public interface UserService extends IService<User,Long>{

    List<User> getAllUsers() ;

    User getByUsername(String username);

    User getByEmail(String email) ;

    User getUserByIdWithTopic(Long id);

}
