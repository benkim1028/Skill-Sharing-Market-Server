package skillbackend.Filters;

import javassist.bytecode.stackmap.TypeData;
import skillbackend.Annotations.Secured;
import skillbackend.Model.JWT;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final JWT jwt = new JWT();
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        // Todo: need to check if hash of this token exists in the database
        // Todo: need to check if this token is valid JWT Token
        LOGGER.log(Level.INFO, "Token Validation started, token: " +token);
        if (jwt.parseJWT(token)){
            LOGGER.log(Level.INFO, "Token Validation passed");
            return;
        } else {
            LOGGER.log(Level.SEVERE, "Token Validation is failed");
            throw new Exception();
        }

    }
}
