package com.example.asm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateFlashcardActivity extends AppCompatActivity {
    private EditText getQuestion, getAnswer;
    private Button btnSave, btnReview;
    private static ArrayList<Flashcard> cardList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);

        getQuestion = findViewById(R.id.getQuestion);
        getAnswer = findViewById(R.id.getAnswer);
        btnSave = findViewById(R.id.btnSave);
        btnReview = findViewById(R.id.btnReview);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = getQuestion.getText().toString();
                String answer = getAnswer.getText().toString();

                if (!question.isEmpty() && !answer.isEmpty()) {
                    Flashcard flashcard = new Flashcard(question, answer);
                    cardList.add(flashcard);

                    Toast.makeText(CreateFlashcardActivity.this, "Flashcard Saved!", Toast.LENGTH_SHORT).show();

                    getQuestion.setText("");
                    getAnswer.setText("");

                } else {
                    Toast.makeText(CreateFlashcardActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateFlashcardActivity.this, CheckFlashcardActivity.class);
                intent.putParcelableArrayListExtra("flashcardList", cardList);
                startActivity(intent);
            }
        });
    }
}
