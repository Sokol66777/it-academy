package validation;

public class ValidationPassword {

    public static boolean validationPassword(String password){
        boolean result;
        result=password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}");
        if(result) {
            return true;
        }
        return false;
    }
}
