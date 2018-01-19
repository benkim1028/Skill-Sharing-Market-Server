package skillbackend.Model;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)){
            return true;
        } else {
            return false;
        }
    }
}
