package com.example.ammaryasser.quizapp;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        int correctAnswersNo = Objects.requireNonNull(getIntent().getExtras()).getInt("correctAnswersNo", -1);

        TextView correctTV = findViewById(R.id.correct_tv);
        TextView wrongTV = findViewById(R.id.wrong_tv);
        ProgressBar progressBar = findViewById(R.id.progressbar);

        correctTV.setText(getString(R.string.correct_answers, correctAnswersNo));
        wrongTV.setText(getString(R.string.wrong_answers, 10 - correctAnswersNo));
        progressBar.setProgress(correctAnswersNo);

        Toast.makeText(this, "Your score = " + correctAnswersNo + "/10", Toast.LENGTH_LONG).show();
    }
}
