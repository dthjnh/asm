package com.example.asm.Quizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asm.Homepage.HomeActivity;
import com.example.asm.R;
import com.example.asm.StoredData.Flashcard;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private EditText enterAnswerInput;
    private Button btnSubmitAnswer;
    private ArrayList<Flashcard> cardList;
    private int currentIndex = 0;
    private int correctAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        enterAnswerInput = findViewById(R.id.enterAnswerInput);
        btnSubmitAnswer = findViewById(R.id.btnSubmitAnswer);

        Intent intent = getIntent();
        cardList = intent.getParcelableArrayListExtra("flashcardList");

        if (cardList != null && !cardList.isEmpty()) {
            displayQuestion(currentIndex);
        } else {
            textViewQuestion.setText("No questions available.");
        }

        btnSubmitAnswer.setOnClickListener(view -> {
            String userAnswer = enterAnswerInput.getText().toString();
            Flashcard currentFlashcard = cardList.get(currentIndex);

            if (userAnswer.equalsIgnoreCase(currentFlashcard.getAnswer())) {
                Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                correctAnswersCount++;
            } else {
                Toast.makeText(QuizActivity.this, "Incorrect! Correct answer: " + currentFlashcard.getAnswer(), Toast.LENGTH_SHORT).show();
            }

            currentIndex++;
            if (currentIndex >= cardList.size()) {
                showResults();
            } else {
                displayQuestion(currentIndex);
            }

        });
    }

    private void displayQuestion(int index) {
        Flashcard flashcard = cardList.get(index);
        textViewQuestion.setText(flashcard.getQuestion());
        enterAnswerInput.setText("");
    }

    private void showFeedbackDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_feedback, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);

        RadioGroup radioGroupFeedback = dialogView.findViewById(R.id.radioGroupFeedback);
        Button btnSubmitFeedback = dialogView.findViewById(R.id.btnSubmitFeedback);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnSubmitFeedback.setOnClickListener(view -> {
            int selectedId = radioGroupFeedback.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRating = dialogView.findViewById(selectedId);
                String rating = selectedRating.getText().toString();
                Toast.makeText(QuizActivity.this, "Thank you for your feedback! Rating: " + rating, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuizActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();
            }
            Intent homeIntent = new Intent(QuizActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
            });
    }

    private void showResults() {
        Toast.makeText(QuizActivity.this, "You answered " + correctAnswersCount + " out of " + cardList.size() + " questions correctly!", Toast.LENGTH_LONG).show();

        showFeedbackDialog();
    }
}