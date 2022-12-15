package com.web.fasad;

import com.pvt.model.User;
import com.pvt.services.UserService;
import com.web.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFasad {

    @Autowired
    private UserService userService;

    public User getByUsername(String username){
        User user = userService.getByUsername(username);
        return user;
    }

}
