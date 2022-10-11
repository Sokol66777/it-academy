package UserImpl;

import model.User;

public class StringToUser {
    private StringToUser(){};
    public static User createUser(String[] UsersParts){
        User user = new User();
        user.setID(Integer.parseInt(UsersParts[0]));
        user.setUsername(UsersParts[1]);
        user.setPassword(UsersParts[2]);
        user.setEmail(UsersParts[3]);
        user.setRole(UsersParts[4]);
        return user;
    }

}
