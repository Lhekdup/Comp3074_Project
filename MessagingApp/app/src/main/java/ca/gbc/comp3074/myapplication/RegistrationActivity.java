package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    ImageButton signupbutton, backbutton;
    EditText inputEmail, inputPassword, inputPassword2, inputUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPassword2 = findViewById(R.id.inputPassword2);
        signupbutton = findViewById(R.id.signupbutton);
        backbutton = findViewById(R.id.backbutton);

        signupbutton.setOnClickListener(v -> {
            String signupemail = inputEmail.getText().toString().trim();
            String signuppassword = inputPassword.getText().toString().trim();
            String signuppassword2 = inputPassword2.getText().toString().trim();

            if (signupemail.isEmpty() || signuppassword.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            } else if (!signuppassword.equals(signuppassword2)) {
                Toast.makeText(RegistrationActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            } else {
                LoginActivity.dummyUsers.put(signupemail, signuppassword);
                Toast.makeText(RegistrationActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("email", signupemail);
                startActivity(intent);
                finish();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}