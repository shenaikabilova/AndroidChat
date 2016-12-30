package com.tu.android.chat;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by ShenaiKabilova
 */
@Path("/chatroom")
public class ChatRoom {
    @POST
    public String doPost(@FormParam("sendFrom") String sendFrom, @FormParam("sendTo") String sendTo,
                         @FormParam("msg") String msg, @FormParam("timestamp") String timestamp) {

        System.out.println(sendFrom + " " + sendTo + " " + msg + " " + timestamp);
        return null;
    }
}
