package com.pvt.services.serviceImpl;

import com.pvt.exceptions.LogicException;
import com.pvt.exceptions.UserLogicException;
import com.pvt.model.User;
import com.pvt.repository.UserRepository;
import com.pvt.validation.ValidationUsersParametrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pvt.services.BaseService;
import com.pvt.services.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User,Long> implements UserService {

    @Autowired
    private UserRepository userRepositoryService;

    @Override
    public List<User> getAllUsers() {

        return userRepositoryService.findAll();
    }

    @Override
    public User getByUsername(String username) {

        return userRepositoryService.findByUsername(username).orElse(null);
    }

    @Override
    public User getByEmail(String email) {

        return userRepositoryService.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserByIdWithTopic(Long id) {

        return userRepositoryService.getUserByIdWithTopic(id);
    }

    @Override
    @Transactional
    public void add(User user) throws LogicException {
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
        userRepositoryService.save(user);
    }

    @Transactional
    @Override
    public void modify(User user) throws LogicException {

        User userCheck;
        User userOld = userRepositoryService.findById(user.getID()).orElse(null);

        if(!userOld.getUsername().equals(user.getUsername())){

            userCheck = userRepositoryService.findByUsername(user.getUsername()).orElse(null);
            if (userCheck != null) {
                throw new UserLogicException("this username used");
            }
        }

        if(!userOld.getEmail().equals(user.getEmail())){

            userCheck = userRepositoryService.findByEmail(user.getEmail()).orElse(null);
            if (userCheck != null) {
                throw new UserLogicException("this email used");
            }
        }
        userRepositoryService.save(user);
    }
}
