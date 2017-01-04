package com.kabilova.mychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.kabilova.model.User;
import com.kabilva.SessionManager.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FriendList extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/friendlist";
    String username;
    TextView tvUsername;
    ListView friendList;
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    List<User> users = new ArrayList<>();
    SessionManager sessionManager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.isLoggedIn();
        user = sessionManager.getUserDetails();

        friendList = (ListView) findViewById(R.id.friendList);
        tvUsername = (TextView) findViewById(R.id.username);
        tvUsername.setText("username: " + user.getUsername());

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);

        RequestParams requestParams = new RequestParams();
        invokeWS(requestParams);

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendList.this, ChatRoom.class);
                for(User u : users) {
                    if(u.getUsername().equals(friendList.getItemAtPosition(position).toString())) {
                        intent.putExtra("sendToID", u.getUserId());
                        startActivity(intent);
                    }
//                    intent.putExtra("sendTo", friendList.getItemAtPosition(position).toString());
//                    startActivity(intent);
                }
            }
        });
    }

    private void invokeWS (RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String str = new String(responseBody, "UTF-8");
                    JSONArray jsonArray = new JSONArray(str);

                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = Integer.parseInt(jsonObject.get("userId").toString());
                        String uName = jsonObject.get("username").toString();

                        users.add(new User(id, uName));

                        if(!uName.equals(user.getUsername())) {
                            list.add(uName);
                        }
                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                friendList.setAdapter(adapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}