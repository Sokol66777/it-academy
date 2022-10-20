package exceptions;

public class RepeatedDataException extends Exception{
    public RepeatedDataException(){
        super();
    }

    public RepeatedDataException(String message){
        super(message);
    }

    public RepeatedDataException(String message, Exception e){
        super(message,e);
    }

    public RepeatedDataException(Exception e){
        super(e);
    }
}
