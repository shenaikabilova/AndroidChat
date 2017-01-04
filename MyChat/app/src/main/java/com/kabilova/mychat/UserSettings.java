package com.kabilova.mychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kabilova.model.User;
import com.kabilva.SessionManager.SessionManager;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        User user = sessionManager.getUserDetails();

        EditText username = (EditText) findViewById(R.id.settingsUsername);
        EditText pass1 = (EditText) findViewById(R.id.settingsPass1);
        EditText pass2 = (EditText) findViewById(R.id.settingsPass2);
        Button settingsUpdate = (Button) findViewById(R.id.settingsUpdate);
        Button settingsDelete = (Button) findViewById(R.id.settingsDelete);

        username.setText(user.getUsername());
        pass1.setText(user.getPassword());

        settingsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        settingsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
