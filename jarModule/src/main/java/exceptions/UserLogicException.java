package exceptions;

public class UserLogicException extends Exception{
    public UserLogicException(){
        super();
    }

    public UserLogicException(String message){
        super(message);
    }

    public UserLogicException(String message, Exception e){
        super(message,e);
    }

    public UserLogicException(Exception e){
        super(e);
    }
}
