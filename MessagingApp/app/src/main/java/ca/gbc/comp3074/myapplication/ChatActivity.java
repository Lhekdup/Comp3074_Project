package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.gbc.comp3074.myapplication.adapters.ChatAdapter;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editTextMessage;
    ImageButton btnBack;
    List<String> messages;
    ChatAdapter adapter;

    TextView chatFriendName, chatFriendStatus;
    ImageView chatFriendIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get data from previous activity
        String friendName = getIntent().getStringExtra("friendName");
        String friendStatus = getIntent().getStringExtra("friendStatus");
        int friendIcon = getIntent().getIntExtra("friendIcon", R.drawable.friendlist_icon);

        // Find views
        chatFriendName = findViewById(R.id.chat_friend_name);
        chatFriendStatus = findViewById(R.id.chat_friend_status);
        chatFriendIcon = findViewById(R.id.chat_friend_icon);
        btnBack = findViewById(R.id.btn_back);
        editTextMessage = findViewById(R.id.editTextMessage);
        recyclerView = findViewById(R.id.recyclerViewChat);

        // Set friend data
        chatFriendName.setText(friendName != null ? friendName : "Unknown");
        chatFriendStatus.setText(friendStatus != null ? friendStatus : "Last seen: Unknown");
        chatFriendIcon.setImageResource(friendIcon);

        // Back button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Setup chat list
        messages = new ArrayList<>();
        adapter = new ChatAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Send message
        findViewById(R.id.buttonSend).setOnClickListener(v -> {
            String msg = editTextMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                messages.add(msg);
                adapter.notifyItemInserted(messages.size() - 1);
                recyclerView.scrollToPosition(messages.size() - 1);
                editTextMessage.setText("");
            }
        });

        // Open call screen
        findViewById(R.id.btn_call).setOnClickListener(v -> {
            Intent intent = new Intent(ChatActivity.this, CallActivity.class);
            intent.putExtra("friendName", chatFriendName.getText().toString());
            intent.putExtra("friendIcon", R.drawable.friendlist_icon);
            startActivity(intent);
        });
    }
}

