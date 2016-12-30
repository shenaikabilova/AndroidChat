package com.kabilova.mychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kabilva.SessionManager.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class ChatRoom extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/chatroom";
    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    SessionManager sessionManager;

    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, Object> user = sessionManager.getUserDetails();

        final Object sendFrom = user.get(SessionManager.KEY_NAME);
        final String sendTo = getIntent().getStringExtra("sendTo");

        msg = (EditText) findViewById(R.id.msg);
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> str = new ArrayList<String>();

        Button button = (Button) findViewById(R.id.buttonSend);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, str);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.add(msg.getText().toString());

                final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                final String utcTime = sdf.format(new Date());

                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();

                RequestParams requestParams = new RequestParams();
                requestParams.put("sendFrom", sendFrom);
                requestParams.put("sendTo", sendTo);
                requestParams.put("msg", msg.getText().toString());
                requestParams.put("timestamp", utcTime);

                invokeWS(requestParams);
            }
        });
    }

    private void invokeWS (RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                msg.setText("");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Could not send a message!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void listenToMsg(RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
      //  client.get(URL_PATH)
    }
}
