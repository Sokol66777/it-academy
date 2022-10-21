package exceptions;

public class ConnectionToDataBaseException extends Exception{

    public ConnectionToDataBaseException(){
        super();
    }

    public ConnectionToDataBaseException(String message) {
        super(message);
    }
    public ConnectionToDataBaseException(String message, Exception e) {
        super(message,e);
    }
    public ConnectionToDataBaseException(Exception e) {
        super(e);
    }

}
