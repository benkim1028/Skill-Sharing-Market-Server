package skillbackend.Exceptions;

public class InvalidUserException extends Exception {
    public InvalidUserException(){
        super("Username does not exist in our database.");
    }
}
