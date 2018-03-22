package skillbackend.Exceptions;

public class UsernameExistException extends Exception {
    public UsernameExistException(){
        super("This username exists already");
    }
}
