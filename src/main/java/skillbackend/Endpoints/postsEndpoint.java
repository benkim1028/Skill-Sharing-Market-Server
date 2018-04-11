package skillbackend.Endpoints;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Annotations.Secured;
import skillbackend.Database.postCRUD;
import skillbackend.Model.Hash;
import skillbackend.Model.Posts.BuyPost;
import skillbackend.Model.Posts.PostBase;
import skillbackend.Model.Posts.SellPost;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


//This Endpoint is to create a user
@Path("/posts")
public class postsEndpoint {
    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());
    private Hash HASH = new Hash();

    @POST
    @Path("/{category}/newbuypost")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBuyPost(@PathParam("category") String category, BuyPost buyPost) {
        LOGGER.log(Level.INFO, "createBuyPost endpoint is called");
        String nameInDatabase = category + "_buy";
        return save(nameInDatabase, buyPost);
    }

    @POST
    @Path("/{category}/newsellpost")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSellPost(@PathParam("category") String category, SellPost sellPost) {
        LOGGER.log(Level.INFO, "createSellPost endpoint is called");
        String nameInDatabase = category + "_sell";
        return save(nameInDatabase, sellPost);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchPosts() {
        LOGGER.log(Level.INFO, "basic fetchPosts endpoint is called");
        postCRUD postCRUD = new postCRUD("post");
        String response = postCRUD.readAll();
        return Response.status(200).entity(response).build();
    }

    @GET
    @Path("/{category}/getbuyposts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchBuyPosts(@PathParam("category") String category) {
        LOGGER.log(Level.INFO, "fetchPosts endpoint ( " + category + " ) is called ");
        return read(category + "_buy");

    }
    @GET
    @Path("/{category}/getsellposts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSellPosts(@PathParam("category") String category) {
        LOGGER.log(Level.INFO, "fetchPosts endpoint ( " + category + " ) is called ");
        return read(category + "_sell");

    }
    private Response save(String category, PostBase post) {
        try {
            postCRUD postCRUD = new postCRUD(category);
            postCRUD.create(post);
            return Response.ok().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.toString()).build();
        }
    }
    private Response read(String category){
        postCRUD postCRUD = new postCRUD(category);
        String response = postCRUD.readAll();
        return Response.status(200).entity(response).build();
    }



}

