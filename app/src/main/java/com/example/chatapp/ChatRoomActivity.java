package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chatapp.models.Message;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class ChatRoomActivity extends AppCompatActivity {

    private RecyclerView rvMessages;
    private Button btnSend;
    private EditText etMessage;
    private String sender;
    private ChatRoomMessageAdapter adapter;
    private ChatRoomSocketListener socketListener;
    private WebSocket webSocket;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        this.rvMessages = findViewById(R.id.rv_messages);
        this.btnSend = findViewById(R.id.btn_send);
        this.etMessage = findViewById(R.id.et_message);

        this.sender = getIntent().getStringExtra("SENDER_NAME");

        adapter = new ChatRoomMessageAdapter(sender);
        rvMessages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMessages.setAdapter(adapter);

        initSocketLister();

        btnSend.setOnClickListener(v -> {
            webSocket.send(gson.toJson(new Message(sender, etMessage.getText().toString())));
            etMessage.setText("");
        });
    }

    private void initSocketLister() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:8080").build();
        socketListener = new ChatRoomSocketListener(this);
        webSocket = client.newWebSocket(request, socketListener);
    }

    public ChatRoomMessageAdapter getAdapter() {
        return adapter;
    }
}