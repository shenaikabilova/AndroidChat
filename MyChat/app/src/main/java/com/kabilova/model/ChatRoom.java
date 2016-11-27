package com.kabilova.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShenaiKabilova.
 */

public class ChatRoom {
    private int chatRoomID;
    private String chatRoomName;
    private List<User> users = new ArrayList<>();

    public ChatRoom() {
    }

    public ChatRoom(int chatRoomID, String chatRoomName, List<User> users) {
        this.chatRoomID = chatRoomID;
        this.chatRoomName = chatRoomName;
        this.users = users;
    }

    public ChatRoom(String chatRoomName, List<User> users) {
        this.chatRoomName = chatRoomName;
        this.users = users;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
