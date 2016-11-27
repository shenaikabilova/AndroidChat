package com.kabilova.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kabilova.mychat.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button hasAccount = (Button) findViewById(R.id.buttonHasAccount);
        hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(backLogin);
            }
        });
    }
}
