package com.tu.android.chat;

import com.google.gson.Gson;
import com.tu.android.dao.ChatRoomDao;
import com.tu.android.daoImpl.ChatRoomDaoImpl;
import com.tu.android.model.*;
import com.tu.android.model.ChatRoom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by ShenaiKabilova
 */
@Path("/newmsg")
public class GetMsg {
    @GET
    public String getMessages(@QueryParam("userFrom") int userFrom, @QueryParam("userTo") int userTo) {
        ChatRoomDao dao = new ChatRoomDaoImpl();
        List<ChatRoom> listMsg =  dao.getMsg(userFrom, userTo);
        Gson gson = new Gson();

        System.out.println(gson.toJson(listMsg));
        return gson.toJson(listMsg);
    }
}
