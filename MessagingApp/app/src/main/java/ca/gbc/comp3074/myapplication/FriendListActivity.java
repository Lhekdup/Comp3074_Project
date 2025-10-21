package ca.gbc.comp3074.myapplication;

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

        int[] friendButtons = {
                R.id.btn_friend1, R.id.btn_friend2, R.id.btn_friend3, R.id.btn_friend4,
                R.id.btn_friend5, R.id.btn_friend6, R.id.btn_friend7, R.id.btn_friend8
        };

        for (int id : friendButtons) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(FriendListActivity.this, ChatListActivity.class);
                startActivity(intent);
            });
        }

        Button emptySpaceBtn = findViewById(R.id.btn_empty_space);
        emptySpaceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(FriendListActivity.this, ChatListActivity.class);
            startActivity(intent);
        });
    }
}
