package validation;

import exceptions.UserLogicException;
import model.User;

public class ValidationUsersParametrs {

    public static void validationPassword(String password) throws UserLogicException {
        if(!password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            throw new UserLogicException("Password is not valid");
        }
    }
}
