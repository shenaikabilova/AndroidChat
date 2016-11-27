package com.kabilova.model;

import java.util.Date;

/**
 * Created by ShenaiKabilova.
 */

public class Message {
    private int userID;
    private int chatRoomID;
    private String msg;
    private Date timestamp;

    public Message() {
    }

    public Message(int userID, int chatRoomID, String msg, Date timestamp) {
        this.userID = userID;
        this.chatRoomID = chatRoomID;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public Message(int chatRoomID, String msg, Date timestamp) {
        this.chatRoomID = chatRoomID;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
