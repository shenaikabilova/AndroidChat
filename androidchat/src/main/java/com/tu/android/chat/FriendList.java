package com.tu.android.chat;

import com.google.gson.Gson;
import com.tu.android.dao.UserDao;
import com.tu.android.daoImpl.UserDaoImpl;
import com.tu.android.model.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

/**
 * Created by ShenaiKabilova
 */
@Path("/friendlist")
public class FriendList {
    @GET
    public String getFriendList() {
        UserDao dao = new UserDaoImpl();
        String response = null;
        JSONObject jsonObject = new JSONObject();

        Gson gson = new Gson();

//        try {
//            response = jsonObject.put("friendList", dao.getUsers()).toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return gson.toJson(dao.getUsers());
    }
}