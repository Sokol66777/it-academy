package validation;

import Exceptions.RepeatedDataException;


public class ValidationParametrs {




    public static void validationPassword(String password) throws RepeatedDataException {
        if(!password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            throw new RepeatedDataException("Password is not valid");
        }

    }




}
