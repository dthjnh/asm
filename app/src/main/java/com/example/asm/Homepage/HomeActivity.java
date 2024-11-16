package com.example.asm.Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm.CreateandCheckFlashcard.CheckFlashcardActivity;
import com.example.asm.CreateandCheckFlashcard.CreateFlashcardActivity;
import com.example.asm.Quizz.QuizActivity;
import com.example.asm.R;
import com.example.asm.StoredData.User;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {
    private Button btnCreateFLashcard, btnReviewFlashcard, btnQuiz, btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCreateFLashcard = findViewById(R.id.btnCreateFlashcard);
        btnReviewFlashcard = findViewById(R.id.btnReviewFlashcard);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnCreateFLashcard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(HomeActivity.this, CreateFlashcardActivity.class);
               startActivity(intent);
           }
       });

        btnReviewFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CheckFlashcardActivity.class);
                startActivity(intent);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                Toast.makeText(HomeActivity.this,"Log Out!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}