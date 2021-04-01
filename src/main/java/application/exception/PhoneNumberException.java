package application.exception;

public class PhoneNumberException extends Exception{
    public PhoneNumberException(){
        super("Incorrect phone number format");
    }
}
