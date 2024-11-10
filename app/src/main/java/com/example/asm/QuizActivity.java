package com.example.asm;

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

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private EditText enterAnswerInput;
    private Button btnSubmitAnswer;
    private ArrayList<Flashcard> flashcardList;
    private int currentIndex = 0;
    private int correctAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        enterAnswerInput = findViewById(R.id.enterAnswerInput);
        btnSubmitAnswer = findViewById(R.id.btnSubmitAnswer);

        // Get the flashcard list from the Intent
        Intent intent = getIntent();
        flashcardList = intent.getParcelableArrayListExtra("flashcardList");

        // Display the first question
        if (flashcardList != null && !flashcardList.isEmpty()) {
            displayQuestion(currentIndex);
        } else {
            textViewQuestion.setText("No questions available.");
        }

        btnSubmitAnswer.setOnClickListener(view -> {
            String userAnswer = enterAnswerInput.getText().toString();
            Flashcard currentFlashcard = flashcardList.get(currentIndex);

            if (userAnswer.equalsIgnoreCase(currentFlashcard.getAnswer())) {
                Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                correctAnswersCount++;
            } else {
                Toast.makeText(QuizActivity.this, "Incorrect! Correct answer: " + currentFlashcard.getAnswer(), Toast.LENGTH_SHORT).show();
            }

            //Move to the next question
            currentIndex++;
            if (currentIndex >= flashcardList.size()) {
                // All questions answered, show the result
                showResults();
            } else {
                displayQuestion(currentIndex);
            }

        });
    }

    // Method to display the question
    private void displayQuestion(int index) {
        Flashcard flashcard = flashcardList.get(index);
        textViewQuestion.setText(flashcard.getQuestion());
        enterAnswerInput.setText(""); // Clear the input field
    }

    // Method to show the feedback dialog
    private void showFeedbackDialog() {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_feedback, null);

        // Create an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false); // Make the dialog non-cancelable

        RadioGroup radioGroupFeedback = dialogView.findViewById(R.id.radioGroupFeedback);
        Button btnSubmitFeedback = dialogView.findViewById(R.id.btnSubmitFeedback);

        // Show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Handle the feedback submission
        btnSubmitFeedback.setOnClickListener(view -> {
            int selectedId = radioGroupFeedback.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRating = dialogView.findViewById(selectedId);
                String rating = selectedRating.getText().toString();
                Toast.makeText(QuizActivity.this, "Thank you for your feedback! Rating: " + rating, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuizActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();
            }
            // Navigate back to HomeActivity
            Intent homeIntent = new Intent(QuizActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish(); // Close the QuizActivity
            });
    }

    // Method to show the results and navigate back to HomeActivity
    private void showResults() {
        Toast.makeText(QuizActivity.this, "You answered " + correctAnswersCount + " out of " + flashcardList.size() + " questions correctly!", Toast.LENGTH_LONG).show();

        showFeedbackDialog();
    }
}