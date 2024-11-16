package com.example.asm.CreateandCheckFlashcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm.Homepage.HomeActivity;
import com.example.asm.Quizz.QuizActivity;
import com.example.asm.R;
import com.example.asm.StoredData.Flashcard;

import java.util.ArrayList;
import java.util.Collections;

public class CheckFlashcardActivity extends AppCompatActivity {
    private TextView textViewFlashcardContent;
    private Button btnBack, btnNextFlashcard, btnQuizz, btnShuffle;
    private ArrayList<Flashcard> cardList;
    private int currentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_flashcard);

        textViewFlashcardContent = findViewById(R.id.textViewFlashcardContent);
        btnBack = findViewById(R.id.btnBack);
        btnNextFlashcard = findViewById(R.id.btnNextFlashcard);
        btnQuizz = findViewById(R.id.btnQuizz);
        btnShuffle = findViewById(R.id.btnShuffle);

        Intent intent = getIntent();
        cardList = intent.getParcelableArrayListExtra("flashcardList");

        if (cardList != null && !cardList.isEmpty()) {
            displayFlashcard(currentIndex);
        } else {
            textViewFlashcardContent.setText("No flashcards available.");
        }

        btnNextFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardList != null && !cardList.isEmpty()) {
                    currentIndex++;
                    if (currentIndex >= cardList.size()) {
                        currentIndex = 0; // Loop back to the first flashcard
                    }
                    displayFlashcard(currentIndex);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckFlashcardActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(CheckFlashcardActivity.this, QuizActivity.class);
                quizIntent.putParcelableArrayListExtra("flashcardList", cardList);
                startActivity(quizIntent);
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardList!=null && !cardList.isEmpty()) {
                    Collections.shuffle(cardList);
                    currentIndex = 0;
                    textViewFlashcardContent.setText("Flashcards shuffled!");
                }  displayFlashcard(currentIndex);
            }
        });
    }

    private void displayFlashcard(int index) {
        Flashcard flashcard = cardList.get(index);
        String flashcardContent = "Question: " + flashcard.getQuestion() + "\nAnswer: " + flashcard.getAnswer();
        textViewFlashcardContent.setText(flashcardContent);
    }

}
