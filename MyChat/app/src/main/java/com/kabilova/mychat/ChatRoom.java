package com.kabilova.mychat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kabilova.model.User;
import com.kabilva.SessionManager.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class ChatRoom extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/chatroom";
    final String URL_NEW_MSG = "http://10.0.2.2:3000/myapp/newmsg";

    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    ListView listView;
    SessionManager sessionManager;
    User user;

    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sessionManager = new SessionManager(getApplicationContext());
        user = sessionManager.getUserDetails();

        final int userSendFrom = user.getUserId();
        final int userSendTo = getIntent().getIntExtra("sendToID", 0);

        msg = (EditText) findViewById(R.id.msg);
        listView = (ListView) findViewById(R.id.listView);

        Button button = (Button) findViewById(R.id.buttonSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                str.add(msg.getText().toString());

                final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                final String utcTime = sdf.format(new Date());

                // next thing you have to do is check if your adapter has changed
//                adapter.notifyDataSetChanged();

                RequestParams requestParams = new RequestParams();
                requestParams.put("sendFrom", userSendFrom);
                requestParams.put("sendTo", userSendTo);
                requestParams.put("msg", msg.getText().toString());
                requestParams.put("timestamp", utcTime);

                invokeWS(requestParams);
            }
        });

        listenToMsg(userSendFrom, userSendTo);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                listenToMsg(userSendFrom, userSendTo);
//            }
//        }, 10000);
    }

    private void invokeWS (RequestParams requestParams) {
        final int userSendFrom = user.getUserId();
        final int userSendTo = getIntent().getIntExtra("sendToID", 0);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                msg.setText("");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listenToMsg(userSendFrom, userSendTo);
                    }
                }, 10000);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Could not send a message!!!", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listenToMsg(userSendFrom, userSendTo);
                    }
                }, 10000);
            }
        });
    }

    private void listenToMsg(int sendFrom, int sendTo) {
        final int userSendFrom = user.getUserId();
        final int userSendTo = getIntent().getIntExtra("sendToID", 0);

        RequestParams requestParams = new RequestParams();
        requestParams.put("userFrom", sendFrom);
        requestParams.put("userTo", sendTo);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_NEW_MSG, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ArrayList<com.kabilova.model.ChatRoom> list = new ArrayList<>();
                ArrayAdapter<com.kabilova.model.ChatRoom> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
                try{
                    String str = new String(responseBody, "UTF-8");
                    JSONArray jsonArray = new JSONArray(str);

                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int chatRoomID = Integer.parseInt(jsonObject.get("chatRoomID").toString());
                        int sendFrom = Integer.parseInt(jsonObject.get("sendFromID").toString());
                        int sendTo = Integer.parseInt(jsonObject.get("sendToID").toString());
                        String sendFromUsername = jsonObject.get("sendFromUsername").toString();
                        String sendToUsername = jsonObject.get("sendToUsername").toString();
                        String message = jsonObject.get("message").toString();
                        String timestamp = jsonObject.get("timestamp").toString();

                        list.add(new com.kabilova.model.ChatRoom(sendFromUsername, sendToUsername, message, timestamp));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listenToMsg(userSendFrom, userSendTo);
                    }
                }, 10000);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Cannot get messages!", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listenToMsg(userSendFrom, userSendTo);
                    }
                }, 10000);
            }
        });
    }
}