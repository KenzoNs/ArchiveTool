package application.exception;

public class ToShortException extends Exception{
    public ToShortException(){
        super("The field was to short");
    }
}
