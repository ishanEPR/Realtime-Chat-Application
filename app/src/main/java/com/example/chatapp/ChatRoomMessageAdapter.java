package com.example.chatapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomMessageAdapter extends RecyclerView.Adapter<ChatRoomMessageAdapter.ViewHolder> {

    private List<Message> messages = new ArrayList<>();
    private final String sender;

    public ChatRoomMessageAdapter(String sender) {
        this.sender = sender;
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    public void addMessages(List<Message> messages) {
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.getMessage().setText(message.getMessage());
        holder.getSender().setText(message.getSender());
//        if (sender.equals(message.getSender())) {
//            holder.getPanel().setBackgroundColor(Color.parseColor("#ffdd75"));
//        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView sender;
        private final TextView message;
        private final LinearLayout panel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.tv_sender);
            message = itemView.findViewById(R.id.tv_message);
            panel = itemView.findViewById(R.id.ll_panel);
        }

        public TextView getSender() {
            return sender;
        }

        public TextView getMessage() {
            return message;
        }

        public LinearLayout getPanel() {
            return panel;
        }
    }
}
