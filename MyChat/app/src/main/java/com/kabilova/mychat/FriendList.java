package com.kabilova.mychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FriendList extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/friendlist";
    ListView friendList;
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        String username = getIntent().getStringExtra("username");

        friendList = (ListView) findViewById(R.id.friendList);
        TextView tvUsername = (TextView) findViewById(R.id.username);
        tvUsername.setText("username: " + username);


        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);

        RequestParams requestParams = new RequestParams();
        invokeWS(requestParams);


    }

    public void invokeWS (RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject obj = new JSONObject();
                try {
                    obj.getString("friendList");

                    friendList.setAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Could not return friend list!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}