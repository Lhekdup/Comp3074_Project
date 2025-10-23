package ca.gbc.comp3074.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FriendListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        // Friend buttons (8)
        int[] friendButtons = {
                R.id.btn_friend1, R.id.btn_friend2, R.id.btn_friend3, R.id.btn_friend4,
                R.id.btn_friend5, R.id.btn_friend6, R.id.btn_friend7, R.id.btn_friend8
        };

        // Friend names
        String[] friendNames = {
                "Friend 1", "Friend 2", "Friend 3", "Friend 4",
                "Friend 5", "Friend 6", "Friend 7", "Friend 8"
        };

        // Friend statuses
        String[] friendStatuses = {
                "Online now", "Last seen: March 10", "Offline", "Online now",
                "Last seen: March 11", "Offline", "Online now", "Last seen: March 9"
        };

        // Friend icons (same for now)
        int[] friendIcons = {
                R.drawable.friendlist_icon, R.drawable.friendlist_icon, R.drawable.friendlist_icon,
                R.drawable.friendlist_icon, R.drawable.friendlist_icon, R.drawable.friendlist_icon,
                R.drawable.friendlist_icon, R.drawable.friendlist_icon
        };

        // Set click for each friend button
        for (int i = 0; i < friendButtons.length; i++) {
            int buttonId = friendButtons[i];
            String friendName = friendNames[i];
            String friendStatus = friendStatuses[i];
            int friendIcon = friendIcons[i];

            Button btn = findViewById(buttonId);
            btn.setOnClickListener(v -> {
                // Open chat with selected friend
                Intent intent = new Intent(FriendListActivity.this, ChatActivity.class);
                intent.putExtra("friendName", friendName);
                intent.putExtra("friendStatus", friendStatus);
                intent.putExtra("friendIcon", friendIcon);

                // Add fade animation
                Bundle animation = ActivityOptions.makeCustomAnimation(
                        FriendListActivity.this,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                ).toBundle();

                startActivity(intent, animation);
            });
        }

        // Click empty space to open chat list
        Button emptySpaceBtn = findViewById(R.id.btn_empty_space);
        emptySpaceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(FriendListActivity.this, ChatListActivity.class);
            Bundle animation = ActivityOptions.makeCustomAnimation(
                    FriendListActivity.this,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            ).toBundle();
            startActivity(intent, animation);
        });
    }
}

