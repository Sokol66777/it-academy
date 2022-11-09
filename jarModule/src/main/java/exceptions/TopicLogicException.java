package exceptions;

public class TopicLogicException extends Exception{
    public TopicLogicException() {
    }

    public TopicLogicException(String message) {
        super(message);
    }

    public TopicLogicException(String message, Exception e) {
        super(message, e);
    }

    public TopicLogicException(Exception e) {
        super(e);
    }
}
