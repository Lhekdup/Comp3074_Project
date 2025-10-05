package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profile extends AppCompatActivity {

    Button buttonback, buttonout;

    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonback = findViewById(R.id.gotochatbutton);
        buttonout = findViewById(R.id.signoutbutton);

        buttonback.setOnClickListener(v -> {
            Intent intent = new Intent(profile.this, chat.class);
            startActivity(intent);
        });

        buttonout.setOnClickListener(v -> {
            Intent intent = new Intent(profile.this, login.class);
            startActivity(intent);
        });

        textViewEmail = findViewById(R.id.textViewEmail);

        String email = getIntent().getStringExtra("email");

        if (email != null) {
            textViewEmail.setText("Welcome,\n" + email + "!");
        } else {
            textViewEmail.setText("Welcome, Guest!");
        }
    }
}