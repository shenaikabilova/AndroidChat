package com.tu.android.daoImpl;

import com.tu.android.dao.ChatRoomDao;
import com.tu.android.model.ChatRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShenaiKabilova
 */
public class ChatRoomDaoImpl implements ChatRoomDao {
    @Override
    public void addNewMsg(ChatRoom chatRoom) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
                final String QUERY = "INSERT INTO chatroom(ID_USER_SEND_FROM, ID_USER_SEND_TO, MSG, TIMESTAMP) VALUES(?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                preparedStatement.setInt(1, chatRoom.getSendFromID());
                preparedStatement.setInt(2, chatRoom.getSendToID());
                preparedStatement.setString(3, chatRoom.getMessage());
                preparedStatement.setString(4, chatRoom.getTimestamp());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public List<ChatRoom> getMsg(int sendFrom, int sendTo) {
        List<ChatRoom> messages = null;

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
            final String QUERY = "SELECT ID, ID_USER_SEND_FROM, ID_USER_SEND_TO, MSG, TIMESTAMP from chatroom where (ID_USER_SEND_FROM = " + sendFrom + " or ID_USER_SEND_TO = " + sendFrom + ") and (ID_USER_SEND_FROM = " + sendTo + " or ID_USER_SEND_TO = " + sendTo + ")";
            final String userSelect = "SELECT USERNAME from users where ID=%s";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            messages = new ArrayList<>();

            while (resultSet.next()) {
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setChatRoomID(resultSet.getInt("ID"));

                chatRoom.setSendFromID(resultSet.getInt("ID_USER_SEND_FROM"));
                PreparedStatement userNameFromStatement = connection.prepareStatement(String.format(userSelect, resultSet.getInt("ID_USER_SEND_FROM")));
                ResultSet resultSetUsernameFrom = userNameFromStatement.executeQuery();
                if(resultSetUsernameFrom.next()) {
                    chatRoom.setSendFromUsername(resultSetUsernameFrom.getString("USERNAME"));
                }

                chatRoom.setSendToID(resultSet.getInt("ID_USER_SEND_TO"));
                PreparedStatement userNameToStatement = connection.prepareStatement(String.format(userSelect, chatRoom.getSendToID()));
                ResultSet resultSetUsernameTo = userNameToStatement.executeQuery();
                if(resultSetUsernameTo.next()) {
                    chatRoom.setSendToUsername(resultSetUsernameTo.getString("USERNAME"));
                }

                chatRoom.setMessage(resultSet.getString("MSG"));
                chatRoom.setTimestamp(resultSet.getString("TIMESTAMP"));

                messages.add(chatRoom);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
