package com.tu.android.dao;

import com.tu.android.model.ChatRoom;
import com.tu.android.model.User;

import java.util.List;

/**
 * Created by ShenaiKabilova
 */
public interface ChatRoomDao {
    void addNewMsg (ChatRoom chatRoom);
    List<ChatRoom> getMsg (int sendFrom, int sendTo);
}
