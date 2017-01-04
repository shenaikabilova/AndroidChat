package com.kabilva.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kabilova.client.LoginActivity;
import com.kabilova.model.User;
import com.kabilova.mychat.FriendList;

import java.util.HashMap;

/**
 * Created by ShenaiKabilova.
 */

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "loginstatus"; //shared file name
    private static final String IS_LOGIN = "isLoggedIn"; //all shares pref-s keys

    public static final String KEY_ID = "userId"; //id
    public static final String KEY_NAME = "username"; //username
    public static final String KEY_PASSWORD = "password"; //password

    public SessionManager (Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(User user) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID, user.getUserId());
        editor.putString(KEY_NAME, user.getUsername());
        editor.putString(KEY_PASSWORD, user.getPassword());

        editor.commit();
    }

    //proverka za login status na potrebitelq; pri false redirect do login activity
    //v protiven sluchai ne pravi nishto
    public void checkLogin() {
        if(!this.isLoggedIn()) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //zatvarq vsichki activitita
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // dobavq nov flag, za da startira novoto activity
            context.startActivity(intent); //starting login activity
        }
    }

    //Get stored session data
    public User getUserDetails() {
//        HashMap<String, User> user = new HashMap<String, Object>();
//        user.put(KEY_ID, sharedPreferences.getInt(KEY_ID, 0));
//        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
//        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        User user = new User();
        user.setUserId(sharedPreferences.getInt(KEY_ID, 0));
        user.setUsername(sharedPreferences.getString(KEY_NAME, null));
        user.setPassword(sharedPreferences.getString(KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}