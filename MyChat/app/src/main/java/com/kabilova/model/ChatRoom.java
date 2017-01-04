package com.kabilova.model;

/**
 * Created by ShenaiKabilova.
 */

public class ChatRoom {
    private String sendFrom;
    private String sendTo;
    private String message;
    private String timestamp;

    public ChatRoom(String sendFrom, String sendTo, String message, String timestamp) {
        this.sendFrom = sendFrom;
        this.sendTo = sendTo;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return sendFrom;
    }

    public void setUsername(String username) {
        this.sendFrom = username;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return sendFrom + " : " + message;
    }
}
