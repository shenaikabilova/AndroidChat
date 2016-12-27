package com.tu.android.model;

/**
 * Created by ShenaiKabilova
 */
public class Message {
    private int msgID;
    private String sendTo;
    private String sendFrom;
    private String msg;

    public Message() {
    }

    public Message(String sendTo, String sendFrom, String msg) {
        this.sendTo = sendTo;
        this.sendFrom = sendFrom;
        this.msg = msg;
    }

    public Message(int msgID, String sendTo, String sendFrom, String msg) {
        this.msgID = msgID;
        this.sendTo = sendTo;
        this.sendFrom = sendFrom;
        this.msg = msg;
    }

    public int getMsgID() {
        return msgID;
    }

    public void setMsgID(int msgID) {
        this.msgID = msgID;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
