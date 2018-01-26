package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Database.CRUD;
import skillbackend.Database.tokenCRUD;
import skillbackend.Database.userCRUD;
import skillbackend.Model.Credentials;
import skillbackend.Model.Identifier;
import skillbackend.Model.JWT;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
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
            authenticate(credentials);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response and set cookie
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(Credentials credentials) throws Exception {
        //TODO: check if the user exist in the database and provided password is valid
        LOGGER.log(Level.INFO, "Authenticating the user - username: " + credentials.getUsername() + " password: " + credentials.getPassword());
        CRUD userCRUD = new userCRUD();
        try {
            userCRUD.read(credentials);
        } catch(Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw e;
        }
        return;
    }

    private String issueToken(String username){
        LOGGER.log(Level.INFO,"Issuing a token for a user: " + username);
        String token = jwt.createJWT(username, Identifier.issuer, Identifier.subject, time);
        //CRUD tokenCRUD = new tokenCRUD();
        //tokenCRUD.create(token);
        return token;
    }

}


