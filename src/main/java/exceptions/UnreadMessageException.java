package exceptions;

public class UnreadMessageException extends Exception {
    public UnreadMessageException(String message){
        super(message);
    }
}
