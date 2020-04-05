package rest;

import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import models.Dog;
import services.DogService;
import services.JWT;

/**
 * API that allows and administrator to retrieve all dogs in the system.
 *
 * @author Hans Cabrera
 */
@Path("GetDogs")
public class GetDogs {

    /**
     * API that allows and administrator to retrieve all dogs in the system.
     *
     * @param token The authentication token created on login.
     * @return A list containing all dogs in the system.
     */
    @GET
    @Path("{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Dog> getJson(@PathParam("token") String token) {
        Claims claims;
        try {
            claims = JWT.decodeJWT(token);
        } catch (Exception e) {
            return null;
        }

        if (!claims.get("isAdmin", Boolean.class)) {
            return null;
        }

        return new DogService().getDogs();
    }
}
