package ca.gbc.comp3074.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // Friend buttons from layout
        int[] friendButtons = {
                R.id.btn_friend1, R.id.btn_friend2, R.id.btn_friend3, R.id.btn_friend4,
                R.id.btn_friend5, R.id.btn_friend6, R.id.btn_friend7, R.id.btn_friend8,
                R.id.btn_friend9, R.id.btn_friend10
        };

        // Friend names
        String[] friendNames = {
                "Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5",
                "Friend 6", "Friend 7", "Friend 8", "Friend 9", "Friend 10"
        };

        // Friend statuses
        String[] friendStatuses = {
                "Online now", "Last seen: March 10", "Offline", "Online now", "Last seen: March 11",
                "Offline", "Online now", "Last seen: March 9", "Online now", "Offline"
        };

        // Friend icons
        int[] friendIcons = {
                R.drawable.friendlist_icon, R.drawable.friendlist_icon, R.drawable.friendlist_icon,
                R.drawable.friendlist_icon, R.drawable.friendlist_icon, R.drawable.friendlist_icon,
                R.drawable.friendlist_icon, R.drawable.friendlist_icon, R.drawable.friendlist_icon,
                R.drawable.friendlist_icon
        };

        // Set click actions for all friends
        for (int i = 0; i < friendButtons.length; i++) {
            int buttonId = friendButtons[i];
            String friendName = friendNames[i];
            String friendStatus = friendStatuses[i];
            int friendIcon = friendIcons[i];

            View button = findViewById(buttonId);

            // Open chat when button is clicked
            button.setOnClickListener(v -> {
                Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
                intent.putExtra("friendName", friendName);
                intent.putExtra("friendStatus", friendStatus);
                intent.putExtra("friendIcon", friendIcon);

                // Add fade animation
                Bundle animation = ActivityOptions.makeCustomAnimation(
                        ChatListActivity.this,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                ).toBundle();

                // Start chat activity
                startActivity(intent, animation);
            });
        }
    }
}


