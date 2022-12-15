package com.pvt.services.serviceImpl;

import com.pvt.dao.UserDAO;
import com.pvt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pvt.services.BaseService;
import com.pvt.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserDAO userDAOService;

    @Override
    public List<User> getAllUsers() {

        return userDAOService.getAllUsers();
    }

    @Override
    public User getByUsername(String username) {

        return userDAOService.getByUsername(username);
    }

    @Override
    public User getByEmail(String email) {

        return userDAOService.getByEmail(email);
    }

    @Override
    public User getUserByIdWithTopic(long id) {

        return userDAOService.getUserByIdWithTopic(id);
    }
}
