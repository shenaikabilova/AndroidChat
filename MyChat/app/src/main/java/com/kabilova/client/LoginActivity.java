package com.kabilova.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kabilova.model.User;
import com.kabilova.mychat.FriendList;
import com.kabilova.mychat.R;
import com.kabilva.SessionManager.SessionManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
//    final String URL_PATH = "http://10.0.2.2:3000/myapp/login";
    final String URL_PATH = "http://10.0.2.2:3000/myapp/example";

    public static final String CURRENT_USER = null;
    EditText username;
    EditText password;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);

        Button newReg = (Button) findViewById(R.id.buttonNewReg);
        newReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regView = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(regView);
            }
        });

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams requestParams = new RequestParams();
                requestParams.put("username", username.getText().toString());
                requestParams.put("password", password.getText().toString());
                invokeWS(requestParams);
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
                    if (!str.equals("null")) {
                        JSONObject obj = new JSONObject(str);
                        sessionManager.createLoginSession(new User(obj.getInt("userId"), obj.getString("username"), obj.getString("password")));

                        Intent createAccount = new Intent(LoginActivity.this, FriendList.class);
                        startActivity(createAccount);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_LONG).show();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    String str = new String(responseBody, "UTF-8");
//
//                    JSONObject jsonObject = new JSONObject(str);
//                    if (jsonObject.getBoolean("login")) {
//                        sessionManager.createLoginSession(username.getText().toString(), password.getText().toString());
//
//                        Intent createAccount = new Intent(LoginActivity.this, FriendList.class);
////                        createAccount.putExtra("username", username.getText().toString());
//                        startActivity(createAccount);
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_LONG).show();
//                    }
//                } catch (UnsupportedEncodingException e) {
//                    Toast.makeText(getApplicationContext(), "UnsupportedEncodingException", Toast.LENGTH_LONG).show();
//                }
//                catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(), "JSONException", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found!", Toast.LENGTH_LONG).show();
                }
                else if(statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Cannot connect to server!", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(), "Error!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}