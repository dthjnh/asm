package com.example.asm.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm.R;
import com.example.asm.StoredData.User;

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

                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.length() < 6) {
                    Toast.makeText(SignUpActivity.this, "Username must be accomplish", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6 || !password.matches(".*[!@#$%^&*].*")) {
                    Toast.makeText(SignUpActivity.this, "Password must be at least 6 characters and contain 1 special character ", Toast.LENGTH_SHORT).show();
                    return;
                }

                    User user = new User(username, password);
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);

                    Toast.makeText(SignUpActivity.this,"Sign up successfully!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}