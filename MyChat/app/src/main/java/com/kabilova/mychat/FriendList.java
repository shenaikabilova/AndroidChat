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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FriendList extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/friendlist";
    String username;
    TextView tvUsername;
    ListView friendList;
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        username = getIntent().getStringExtra("username");

        friendList = (ListView) findViewById(R.id.friendList);
        tvUsername = (TextView) findViewById(R.id.username);
        tvUsername.setText("username: " + username);

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);

        RequestParams requestParams = new RequestParams();
        invokeWS(requestParams);

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendList.this, ChatRoom.class);
                startActivity(intent);
            }
        });
    }

    public void invokeWS (RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.print(tvUsername);
                try{
                    String str = new String(responseBody, "UTF-8");
                    JSONArray jsonArray = new JSONArray(str);

                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = Integer.parseInt(jsonObject.get("userId").toString());
                        String uName = jsonObject.get("username").toString();

                        if(!uName.equals(username)) {
                            list.add(uName);
                        }
                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

//                JSONObject obj = new JSONObject();
//                try {
//                    String un = (String) obj.get("username");
//                    System.out.println(un);
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Gson gson = new Gson();

//                try {
//                    obj.getString("friendList");
//                String str = gson.fromJson("user:")

                friendList.setAdapter(adapter);

//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(), "Could not return friend list!", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}