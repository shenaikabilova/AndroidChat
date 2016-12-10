package com.tu.android.chat;

import com.tu.android.dao.UserDao;
import com.tu.android.daoImpl.UserDaoImpl;
import com.tu.android.model.User;

import javax.ws.rs.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/registration")
public class RegisterUser {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
/*    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        System.out.println("Hello, Android");
        return "Got it!";
    }
*/
    @POST
    public void doPost(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.println("success connect to server ");
        UserDao createAccount = new UserDaoImpl();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        createAccount.addUser(user);
    }
}
