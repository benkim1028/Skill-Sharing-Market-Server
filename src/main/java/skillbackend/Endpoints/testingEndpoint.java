package skillbackend.Endpoints;

import skillbackend.Annotations.Secured;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/testing")
public class testingEndpoint {

    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(){
        return Response.ok("This one is working").build();
    }
}
