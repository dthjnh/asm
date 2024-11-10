package com.example.asm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {
    private EditText enterUsername, enterPassword;
    private Button btnSignUp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        enterUsername = findViewById(R.id.enterUsername);
        enterPassword = findViewById(R.id.enterPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = enterUsername.getText().toString();
                String password = enterPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    User user = new User(username, password);
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);

                    Toast.makeText(SignUpActivity.this,"Sign up successfully!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}