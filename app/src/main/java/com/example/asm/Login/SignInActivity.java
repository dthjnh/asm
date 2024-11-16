package com.example.asm.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm.Homepage.HomeActivity;
import com.example.asm.R;
import com.example.asm.StoredData.User;

public class SignInActivity extends AppCompatActivity {
    private EditText enterUsername, enterPassword;
    private Button btnSignIn;
    private User user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        enterUsername = findViewById(R.id.enterUsername);
        enterPassword = findViewById(R.id.enterPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = enterUsername.getText().toString();
                String inputPassword = enterPassword.getText().toString();

                if (inputName.equals(user.getUsername()) && inputPassword.equals(user.getPassword())) {
                    Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    Toast.makeText(SignInActivity.this,"Sign in successfully",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}