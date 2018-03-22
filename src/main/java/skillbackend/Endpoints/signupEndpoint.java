package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Database.userCRUD;
import skillbackend.Model.Hash;
import skillbackend.Model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


//This Endpoint is to create a user
@Path("/signup")
public class signupEndpoint{
   private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );
   private Hash HASH = new Hash();
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response signup(User aUser){
       LOGGER.log(Level.INFO, "SignUp endpoint is called");
       try {
           //save the user information in the database
           saveInDatabase(aUser);
           //if successfully saved in the database, give the user a token (same as signin process)
           return Response.ok().build();
       } catch (Exception e) {
           LOGGER.log(Level.SEVERE, e.toString(), e);
           return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.toString()).build();
       }

   }

   private void saveInDatabase(User aUser) throws Exception {
       try {
           userCRUD userCRUD = new userCRUD();
           userCRUD.create(aUser);
       } catch(Exception e){
           LOGGER.log(Level.SEVERE, e.toString(), e);
           throw e;
       }
      //TODO: create user table in a database
      //TODO: get hash of password and then save userid and hash of password into the database

   }



}
