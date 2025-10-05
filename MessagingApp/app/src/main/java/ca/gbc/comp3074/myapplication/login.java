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

import java.util.HashMap;

public class login extends AppCompatActivity {

    TextView signupbut;
    Button button;
    EditText email, password;

    public static HashMap<String, String> dummyUsers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = findViewById(R.id.editTextLogEmailAddress);
        password = findViewById(R.id.editTextLogPassword);
        button = findViewById(R.id.logbutton);
        signupbut = findViewById(R.id.signupbutton);

        dummyUsers.put("tlhekdup@yahoo.ca", "password123");
        dummyUsers.put("lhekduptenzin@gmail.com", "password321");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginemail = email.getText().toString().trim();
                String loginpassword = password.getText().toString().trim();
                if (dummyUsers.containsKey(loginemail) &&
                        dummyUsers.get(loginemail).equals(loginpassword)) {
                    Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(login.this, profile.class);
                    intent.putExtra("email", loginemail);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
                finish();
            }
        });


    }
}