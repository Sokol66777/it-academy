package com.pvt.services.serviceImpl;

import com.pvt.dao.UserDAO;
import com.pvt.exceptions.LogicException;
import com.pvt.exceptions.UserLogicException;
import com.pvt.model.User;
import com.pvt.validation.ValidationUsersParametrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pvt.services.BaseService;
import com.pvt.services.UserService;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void add(User user) throws LogicException {
        User userCheck;
        user.setRole("user");

        ValidationUsersParametrs.validationPassword(user.getPassword());

        userCheck = getByEmail(user.getEmail());
        if (userCheck != null) {
            throw new UserLogicException("this email used");
        }

        userCheck = getByUsername(user.getUsername());
        if (userCheck != null) {
            throw new UserLogicException("this username used");
        }
        userDAOService.add(user);
    }
}
