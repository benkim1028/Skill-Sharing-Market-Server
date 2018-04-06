package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Database.postCRUD;
import skillbackend.Model.Hash;
import skillbackend.Model.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


//This Endpoint is to create a user
@Path("/posts")
public class postsEndpoint{
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );
    private Hash HASH = new Hash();
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPosts(Post post){
        LOGGER.log(Level.INFO, "createPost endpoint is called");
        try {
            saveInDatabase(post);
            return Response.ok().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.toString()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchPosts(){
        LOGGER.log(Level.INFO, "fetchPosts endpoint is called");
        postCRUD postCRUD = new postCRUD();
        String response = postCRUD.readAll();
        return Response.status(200).entity(response).build();
    }

    private void saveInDatabase(Post post) throws Exception {
        try {
            postCRUD postCRUD = new postCRUD();
            postCRUD.create(post);
        } catch(Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw e;
        }
        //TODO: create user table in a database
        //TODO: get hash of password and then save userid and hash of password into the database

    }



}

