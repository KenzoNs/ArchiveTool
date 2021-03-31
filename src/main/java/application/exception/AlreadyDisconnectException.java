package application.exception;

public class AlreadyDisconnectException extends Exception{

    public AlreadyDisconnectException(){
        super("You are already diconnected");
    }
}
