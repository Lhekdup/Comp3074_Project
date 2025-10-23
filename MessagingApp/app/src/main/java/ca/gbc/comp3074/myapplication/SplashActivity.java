package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find buttons
        ImageButton loginButton = findViewById(R.id.loginButton);
        ImageButton registerButton = findViewById(R.id.registerButton);

        // Go to login page
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Go to register page
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}

