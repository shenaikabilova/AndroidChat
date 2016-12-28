package com.kabilova.mychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        final EditText msg = (EditText) findViewById(R.id.msg);
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> str = new ArrayList<String>();

        Button button = (Button) findViewById(R.id.buttonSend);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, str);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.add(msg.getText().toString());
                msg.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });
    }
}
