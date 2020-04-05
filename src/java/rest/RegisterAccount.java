package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import models.User;
import services.AccountService;

/**
 * API that allows a user to register an account.
 *
 * @author Hans Cabrera
 */
@Path("register")
public class RegisterAccount {

    /**
     * API that allows a user to register an account.
     *
     * @param content JSON containing new account details.
     * @return A string containing details of any error or if the creation was
     * successful.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        User user = gson.fromJson(content, User.class);
        AccountService as = new AccountService();
        if (as.register(user)) {
            return "account registered";
        }
        return "Username already taken.";
    }
}
