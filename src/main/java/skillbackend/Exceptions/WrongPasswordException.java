package skillbackend.Exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(){
        super("User provided wrong password.");
    }
}
