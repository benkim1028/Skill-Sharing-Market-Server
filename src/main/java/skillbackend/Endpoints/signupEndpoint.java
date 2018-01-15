package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import org.mindrot.jbcrypt.BCrypt;
import skillbackend.Model.Credentials;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/signup")
public class signupEndpoint{
   private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response signup(Credentials credentials){
       String username = credentials.getUsername();
       String password = credentials.getPassword();
       LOGGER.log(Level.INFO, "username = " + username + ", password = " + password);

       //TODO: create user table in a database
       //TODO: get hash of password and then save userid and hash of password into the database

       try(
           return Response.ok(token).build();
       } catch (Exception e) {
           LOGGER.log(Level.SEVERE, e.toString(), e);
           throw e;
       }
       return Response.Status(Response.Status.FORBIDDEN).build();

   }



}
