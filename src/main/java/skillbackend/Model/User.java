package skillbackend.Model;

import org.bson.Document;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phonenumber;
    private Date birthdate;
    private String gender;

    public Date getBirthdate() { return birthdate; }

    public void setBirthdate(Date birthdate) { this.birthdate = birthdate;}

    public String getPhonenumber() { return phonenumber; }

    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getGender() {return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getFirstname() { return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public static User createUser(String firstname, String lastname, String username, String password, String gender, String phonenumber){
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setUsername(username);
        u.setPassword(password);
        u.setGender(gender);
        u.setPhonenumber(phonenumber);
        return u;
    }
    public static User createUser(String firstname, String lastname, String username){
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setUsername(username);
        u.setPassword(null);
        u.setGender(null);
        u.setPhonenumber(null);
        return u;
    }
    public static Document createDBObject(User user) {
        Hash HASH = new Hash();
        Document doc = new Document();

        doc.append("username", user.getUsername());
        doc.append("firstname", user.getFirstname());
        doc.append("lastname", user.getLastname());
        doc.append("password", HASH.hashPassword(user.getPassword()));
        doc.append("gender", user.getGender());
        doc.append("phonenumber", user.getPhonenumber());
        doc.append("birthdate", user.getBirthdate());
        return doc;
    }

}
