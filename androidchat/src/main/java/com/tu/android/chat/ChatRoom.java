package com.tu.android.chat;

import com.tu.android.dao.ChatRoomDao;
import com.tu.android.daoImpl.ChatRoomDaoImpl;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ShenaiKabilova
 */
@Path("/chatroom")
public class ChatRoom {
    @POST
    public void doPost(@FormParam("sendFrom") Integer sendFrom, @FormParam("sendTo") int sendTo,
                         @FormParam("msg") String msg, @FormParam("timestamp") String timestamp) {

        System.out.println(sendFrom + " " + sendTo + " " + msg + " " + timestamp);

        com.tu.android.model.ChatRoom chatRoom = new com.tu.android.model.ChatRoom(sendFrom, sendTo, msg, timestamp);
        ChatRoomDao dao = new ChatRoomDaoImpl();
        dao.addNewMsg(chatRoom);

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//
//            Date date = format.parse(timestamp);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
