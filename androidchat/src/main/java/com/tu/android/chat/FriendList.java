package com.tu.android.chat;

import com.google.gson.Gson;
import com.tu.android.dao.UserDao;
import com.tu.android.daoImpl.UserDaoImpl;
import com.tu.android.model.User;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShenaiKabilova
 */
@Path("/friendlist")
public class FriendList {
    @GET
    public String getFriendList() {
        UserDao dao = new UserDaoImpl();
        String response = null;
//        JSONObject jsonObject = new JSONObject();

        List<User> users = new ArrayList<>();
        for(User user : dao.getUsers()) {
            users.add(new User(user.getUserId(), user.getUsername()));
        }

//        com.tu.android.model.FriendList friendList = new com.tu.android.model.FriendList(users);
        Gson gson = new Gson();
//        String friendsGson =
//
//        try {
//            response = jsonObject.put("friendList", dao.getUsers()).toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        System.out.println(gson.toJson(users));

        return gson.toJson(users);
    }
}