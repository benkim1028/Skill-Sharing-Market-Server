package skillbackend.Model;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {
    private static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    private static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)){
            return true;
        } else {
            return false;
        }
    }
}
