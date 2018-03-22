package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Annotations.Secured;
import skillbackend.Database.userCRUD;
import skillbackend.Model.JWT;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("/profile")
public class profileEndpoint {
    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());

    @GET
    @Path("/info")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response profile(@HeaderParam("AUTHORIZATION") String authorizationHeader) {
        LOGGER.log(Level.INFO, "Profile endpoint is called");
        String token = authorizationHeader.substring("Bearer".length()).trim();
        String username = new JWT().getUsername(token);
        LOGGER.log(Level.INFO, "Requested Profile of username = " + username);
        userCRUD userCRUD = new userCRUD();
        String userInfo = userCRUD.getUserInfo(username);
        System.out.println(userInfo);

        return Response.status(200).entity(userInfo).build();
    }
}
