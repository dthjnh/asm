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
    private EditText getTitle, getQuestion, getAnswer;
    private Button btnSave, btnReview;
    private static ArrayList<Flashcard> flashcardList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);

        getTitle = findViewById(R.id.getTitle);
        getQuestion = findViewById(R.id.getQuestion);
        getAnswer = findViewById(R.id.getAnswer);
        btnSave = findViewById(R.id.btnSave);
        btnReview = findViewById(R.id.btnReview);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = getTitle.getText().toString();
                String question = getQuestion.getText().toString();
                String answer = getAnswer.getText().toString();

                if (!title.isEmpty() && !question.isEmpty() && !answer.isEmpty()) {
                    // Create a new Flashcard and add it to the list
                    Flashcard flashcard = new Flashcard(title, question, answer);
                    flashcardList.add(flashcard);

                    Toast.makeText(CreateFlashcardActivity.this, "Flashcard Saved!", Toast.LENGTH_SHORT).show();

                    // Clear the input fields for the next flashcard
                    getTitle.setText("");
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
                // Send the flashcard list to ReviewFlashcardActivity
                Intent intent = new Intent(CreateFlashcardActivity.this, ReviewFlashcardActivity.class);
                intent.putParcelableArrayListExtra("flashcardList", flashcardList);
                startActivity(intent);
            }
        });
    }
}
