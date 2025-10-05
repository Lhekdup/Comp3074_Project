package ca.gbc.comp3074.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.gbc.comp3074.myapplication.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<String> messages;

    public ChatAdapter(List < String > messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_message, parent, false);
    return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ChatViewHolder holder,int position){
    holder.textView.setText(messages.get(position));
    }

    @Override
    public int getItemCount () {
    return messages.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewMessage);
        }
    }
}
