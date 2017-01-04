package com.tu.android.model;

/**
 * Created by ShenaiKabilova
 */
public class ChatRoom {
    private int chatRoomID;
    private int sendFromID;
    private String sendFromUsername;
    private int sendToID;
    private String sendToUsername;
    private String message;
    private String timestamp;

    public ChatRoom() {
    }

    public ChatRoom(int sendFromID, int sendToID, String message, String timestamp) {
        this.sendFromID = sendFromID;
        this.sendToID = sendToID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public int getSendFromID() {
        return sendFromID;
    }

    public void setSendFromID(int username) {
        this.sendFromID = username;
    }

    public int getSendToID() {
        return sendToID;
    }

    public void setSendToID(int sendToID) {
        this.sendToID = sendToID;
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

    public String getSendFromUsername() {
        return sendFromUsername;
    }

    public void setSendFromUsername(String sendFromUsername) {
        this.sendFromUsername = sendFromUsername;
    }

    public String getSendToUsername() {
        return sendToUsername;
    }

    public void setSendToUsername(String sendToUsername) {
        this.sendToUsername = sendToUsername;
    }
}
