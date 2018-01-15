package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Model.Credentials;
import skillbackend.Model.JWT;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


//This Endpoint is to authenticate the user
@Path("/signin")
public class signinEndpoint {
    //private static Connection con = jdbcConnection.getConnection();
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );
    private static final JWT jwt = new JWT();
    private static final String issuer = "Ben Kim";
    private static final String subject = "123456789";
    private static final int time = 999999999;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials credentials) {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        LOGGER.log(Level.INFO, "username = " + username + ", password = " + password);

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password){
        //TODO: check if the user exist in the database and provided password is valid
            LOGGER.log(Level.INFO, "authenticated");
    }

    private String issueToken(String username) throws Exception {
            String token = jwt.createJWT(username, issuer, subject, time);
        //TODO: create token table in a database
        //TODO: save this issued token in the database
        //String token =  getSaltString() + username;
//        Date issueDate = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(issueDate);
//        cal.add(Calendar.MONTH, 6);
//        Date expiryDate = cal.getTime();
//        String issuer = "BenKim";
        

//        String query = "INSERT INTO Validation (token, expiryDate, issueDate, issuer)"
//                + " values ('"+token+"','"+ new java.sql.Date(expiryDate.getTime()) +"','"+ new java.sql.Date(issueDate.getTime())+"', '"+ issuer+"')";
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(query);
//        }catch (Exception e) {
//            LOGGER.log(Level.SEVERE, e.toString(), e);
//            throw e;
//        }
        return token;
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}


