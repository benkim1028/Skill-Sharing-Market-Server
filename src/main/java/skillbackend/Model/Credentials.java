package skillbackend.Model;

import java.io.Serializable;

public class Credentials implements Serializable {

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String idp;

    public String getIdp() { return idp; }

    public void setIdp(String idp) { this.idp = idp; }

    private String idToken;

    public String getIdToken() { return idToken; }

    public void setIdToken(String idToken) { this.idToken = idToken; }


    // Getters and setters omitted
}
