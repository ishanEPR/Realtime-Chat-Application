package com.example.chatapp;

import android.util.Log;

import com.example.chatapp.models.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatRoomSocketListener extends WebSocketListener {
    private final ChatRoomActivity activity;
    private final Gson gson;

    public ChatRoomSocketListener(ChatRoomActivity activity) {
        this.activity = activity;
        gson = new Gson();
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        activity.runOnUiThread(() -> {
            Log.d("socket","connected");
        });
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t,Response response) {
        super.onFailure(webSocket, t, response);
        Log.d("socket","error");
        t.printStackTrace();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        System.out.println(text);
        activity.runOnUiThread(() -> {
            if (text.startsWith("[")) {
                //array of messages
                activity.getAdapter().addMessages(
                        gson.fromJson(text, new TypeToken<List<Message>>() {
                        }.getType())
                );
            } else {
                activity.getAdapter().addMessage(gson.fromJson(text, Message.class));
            }
        });


    }
}
