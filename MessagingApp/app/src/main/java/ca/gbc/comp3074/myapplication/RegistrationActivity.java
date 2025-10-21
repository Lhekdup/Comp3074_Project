package ca.gbc.comp3074.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    TextView loginbut;
    Button button;
    EditText email, password, password2;

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

        email = findViewById(R.id.editTextRegistrationEmailAddress);
        password = findViewById(R.id.editTextRegistrationPassword1);
        password2 = findViewById(R.id.editTextRegistrationPassword2);
        button = findViewById(R.id.registrationbutton);
        loginbut = findViewById(R.id.loginbutton);

        button.setOnClickListener(v -> {
            String signupemail = email.getText().toString().trim();
            String signuppassword = password.getText().toString().trim();
            String signuppassword2 = password2.getText().toString().trim();

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

        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, FriendListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}