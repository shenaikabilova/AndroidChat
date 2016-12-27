package com.tu.android.chat;

import com.tu.android.dao.UserDao;
import com.tu.android.daoImpl.UserDaoImpl;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by ShenaiKabilova
 */
@Path("/login")
public class LoginUser {
    @GET
    public String getUsers(@QueryParam("username") String username, @QueryParam("password") String password) throws JSONException{
        String response = null;
        JSONObject json = new JSONObject();
        UserDao dao = new UserDaoImpl();
        if(dao.isUser(username, password)) {
            response = json.put("login", true).toString();
        }
        else response = json.put("login", false).toString();

        return response;
    }
}