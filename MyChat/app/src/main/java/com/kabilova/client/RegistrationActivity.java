package com.kabilova.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kabilova.mychat.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {
    final String URL_PATH = "http://10.0.2.2:3000/myapp/registration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText username = (EditText) findViewById(R.id.settingsUsername);
        final EditText pass1 = (EditText) findViewById(R.id.settingsPass1);
        final EditText pass2 = (EditText) findViewById(R.id.settingsPass2);

        Button hasAccount = (Button) findViewById(R.id.buttonHasAccount);
        hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(backLogin);
            }
        });

        Button reg = (Button) findViewById(R.id.buttonRegistration);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams requestParams = new RequestParams();
                if(pass1.getText().toString().equals(pass2.getText().toString())) {
                    requestParams.put("username", username.getText().toString());
                    requestParams.put("password", pass1.getText().toString());
                    invokeWS(requestParams);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords must be the same.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void invokeWS(RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(URL_PATH, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Intent backLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(backLogin);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
