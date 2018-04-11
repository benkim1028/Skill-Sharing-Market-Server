package skillbackend.Endpoints;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import javassist.bytecode.stackmap.TypeData;
import org.json.JSONObject;
import skillbackend.Database.userCRUD;
import skillbackend.Model.Credentials;
import skillbackend.Model.Identifier;
import skillbackend.Model.JWT;
import skillbackend.Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

//This Endpoint is to authenticate the user
@Path("/signin")
public class signinEndpoint {

    //private static Connection con = jdbcConnection.getConnection();
    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());
    private static final JWT jwt = new JWT();
    private static final int time = 800000;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials credentials) {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        LOGGER.log(Level.INFO, "Authentication started - username = " + username + ", password = " + password);

        try {
            if(credentials.getIdp().equals("default")) {
                return authenticateDefault(credentials);

            } else {
                return authenticateGoogleUser(credentials.getIdToken());
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Response authenticateDefault(Credentials credentials) throws Exception {
        // Authenticate the user using the credentials provided
        authenticate(credentials);

        // Issue a token for the user
        String token = issueToken(credentials.getUsername());

        // Return the token on the response and set cookie
        JSONObject jsonToken = new JSONObject();
        jsonToken.put("token", token);
        jsonToken.put("message", "Login Successful");
        return Response.status(200).entity(jsonToken.toString()).build();
    }

    private Response authenticateGoogleUser(String idTokenString) throws GeneralSecurityException, IOException {
        LOGGER.log(Level.INFO, "Google Authentication started");
        final HttpTransport transport = new NetHttpTransport();
        final JsonFactory jsonFactory = new JacksonFactory();
        final String CLIENT_ID = "850459285748-p1q00q4nifbg302s0m3ulu91pfkgbjmd.apps.googleusercontent.com";
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            JSONObject jsonToken = new JSONObject();
            if(emailVerified) {
                if(userExists(email)) {
                    //if the user exists, process is same as default login
                    jsonToken.put("token", issueToken(email));
                    jsonToken.put("message", "Login Successful");
                    return Response.status(200).entity(jsonToken.toString()).build();
                } else {
                    // create an incomplete user and save into the database
                    User aUser = User.createUser(givenName, familyName, email);
                    userCRUD userCRUD = new userCRUD();
                    userCRUD.create(aUser);
                    
                    // send response and request more information
                    jsonToken.put("token", "");
                    jsonToken.put("message", "Need More Information");
                    return Response.status(200).entity(jsonToken.toString()).build();
                }
            } else {
                jsonToken.put("token", "");
                jsonToken.put("message", "Verify your email first");
                return Response.status(200).entity(jsonToken.toString()).build();
            }
        } else {
            System.out.println("Invalid ID token.");
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(Credentials credentials) throws Exception {
        //TODO: check if the user exist in the database and provided password is valid
        LOGGER.log(Level.INFO, "Authenticating the user - username: " + credentials.getUsername() + " password: " + credentials.getPassword());
        userCRUD userCRUD = new userCRUD();
        try {
            userCRUD.read(credentials);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw e;
        }
        return;
    }

    private boolean userExists(String email){
        userCRUD userCRUD = new userCRUD();
        try {
            userCRUD.getUserInfo(email);
            return true;
        } catch (NullPointerException e){
            return false;
        }

    }

    private String issueToken(String username) {
        LOGGER.log(Level.INFO, "Issuing a token for a user: " + username);
        String token = jwt.createJWT(username, Identifier.issuer, Identifier.subject, time);
        //CRUD tokenCRUD = new tokenCRUD();
        //tokenCRUD.create(token);
        return token;
    }

}


