package com.tu.android.chat;

import com.google.gson.Gson;
import com.tu.android.dao.UserDao;
import com.tu.android.daoImpl.UserDaoImpl;
import com.tu.android.model.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by ShenaiKabilova
 */
@Path("/example")
public class ExampleExistUser {
    @GET
    public String getUsers(@QueryParam("username") String username, @QueryParam("password") String password) throws JSONException {

        Gson gson = new Gson();
        UserDao dao = new UserDaoImpl();

        User user = dao.checkExistUser(username, password);

        return gson.toJson(user);

    }
}