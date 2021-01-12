package com.example.chatapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btn_enter)).setOnClickListener(v -> {

            String sender = ((EditText) findViewById(R.id.et_name)).getText().toString();

            Intent chatRoomIntent = new Intent(this, ChatRoomActivity.class);
            chatRoomIntent.putExtra("SENDER_NAME", sender.isEmpty() ? "Anonymous" : sender);
            startActivity(chatRoomIntent);
        });

    }
}