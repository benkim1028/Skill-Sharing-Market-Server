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

    private String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    // Getters and setters omitted
}
