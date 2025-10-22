package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {

    // UI elements
    ImageView callIcon;
    TextView callStatusText;
    ImageButton btnBackToMessages, btnEndCall, btnMute, btnCameraOff, btnScreenShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Find all views by their IDs
        callIcon = findViewById(R.id.call_icon);
        callStatusText = findViewById(R.id.call_status_text);
        btnBackToMessages = findViewById(R.id.call_backtomessagesbutton);
        btnEndCall = findViewById(R.id.call_callendbutton);
        btnMute = findViewById(R.id.call_mutebutton);
        btnCameraOff = findViewById(R.id.call_cameraoffbutton);
        btnScreenShare = findViewById(R.id.call_screensharebutton);

        // Get friend data from intent
        String friendName = getIntent().getStringExtra("friendName");
        int friendIcon = getIntent().getIntExtra("friendIcon", R.drawable.friendlist_icon);

        // Small icon is used in chat, but here we show a big image
        callIcon.setImageResource(R.drawable.call_icon);

        // Set friend name
        callStatusText.setText("Calling " + (friendName != null ? friendName : "Unknown"));

        // Go back to chat without ending call
        btnBackToMessages.setOnClickListener(v -> {
            Intent intent = new Intent(CallActivity.this, ChatActivity.class);
            intent.putExtra("friendName", friendName);
            intent.putExtra("friendIcon", friendIcon);
            intent.putExtra("friendStatus", "Online now");

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(intent);
            finish(); // Optional, because feature not fully implemented
        });

        // End call and go back to chat
        btnEndCall.setOnClickListener(v -> {
            Intent intent = new Intent(CallActivity.this, ChatActivity.class);
            intent.putExtra("friendName", friendName);
            intent.putExtra("friendIcon", friendIcon);
            intent.putExtra("friendStatus", "Last seen: Just now");
            startActivity(intent);
            finish();
        });

        // Mute button (no function yet)
        btnMute.setOnClickListener(v -> {
            // Later we can add microphone mute logic here
        });

        // Camera Off button -> open CameraOffActivity
        btnCameraOff.setOnClickListener(v -> {
            Intent intent = new Intent(CallActivity.this, CameraOffActivity.class);
            intent.putExtra("friendName", friendName);
            intent.putExtra("friendIcon", friendIcon);
            startActivity(intent);
        });

        // Screen Share button -> open ScreenShareActivity
        btnScreenShare.setOnClickListener(v -> {
            Intent intent = new Intent(CallActivity.this, ScreenShareActivity.class);
            intent.putExtra("friendName", friendName);
            intent.putExtra("friendIcon", friendIcon);
            startActivity(intent);
        });
    }
}


