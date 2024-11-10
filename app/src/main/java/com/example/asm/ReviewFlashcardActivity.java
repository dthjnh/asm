package com.example.asm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewFlashcardActivity extends AppCompatActivity {
    private TextView textViewFlashcardContent;
    private Button btnBack, btnNextFlashcard, btnQuizz, btnShuffle;
    private ArrayList<Flashcard> flashcardList;
    private int currentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_flashcard);

        textViewFlashcardContent = findViewById(R.id.textViewFlashcardContent);
        btnBack = findViewById(R.id.btnBack);
        btnNextFlashcard = findViewById(R.id.btnNextFlashcard);
        btnQuizz = findViewById(R.id.btnQuizz);
        btnShuffle = findViewById(R.id.btnShuffle);

        // Get the flashcard list from the Intent
        Intent intent = getIntent();
        flashcardList = intent.getParcelableArrayListExtra("flashcardList");

        // Display the first flashcard
        if (flashcardList != null && !flashcardList.isEmpty()) {
            displayFlashcard(currentIndex);
        } else {
            textViewFlashcardContent.setText("No flashcards available.");
        }

        btnNextFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flashcardList != null && !flashcardList.isEmpty()) {
                    currentIndex++;
                    if (currentIndex >= flashcardList.size()) {
                        currentIndex = 0; // Loop back to the first flashcard
                    }
                    displayFlashcard(currentIndex);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewFlashcardActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(ReviewFlashcardActivity.this, QuizActivity.class);
                quizIntent.putParcelableArrayListExtra("flashcardList", flashcardList);
                startActivity(quizIntent);
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flashcardList!=null && !flashcardList.isEmpty()) {
                    Collections.shuffle(flashcardList);
                    currentIndex = 0; // Reset the current index
                    textViewFlashcardContent.setText("Flashcards shuffled!");
                }  displayFlashcard(currentIndex);
            }
        });
    }

    // Method to display the flashcard content
    private void displayFlashcard(int index) {
        Flashcard flashcard = flashcardList.get(index);
        String flashcardContent = "Title: " + flashcard.getTitle() + "\nQuestion: " + flashcard.getQuestion() + "\nAnswer: " + flashcard.getAnswer();
        textViewFlashcardContent.setText(flashcardContent);
    }
}
