package com.example.ammaryasser.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView nTV;
    private TextView qTV;
    private RadioGroup radioGroup;
    private RadioButton ansRadio_1;
    private RadioButton ansRadio_2;
    private RadioButton ansRadio_3;
    private RadioButton ansRadio_4;

    private QuizCountDown countDown;
    private int id = 0;
    private int correctRadioId;
    private int correctAnswersNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        progressBar = findViewById(R.id.progressbar);
        nTV = findViewById(R.id.nTV);
        qTV = findViewById(R.id.qTV);
        radioGroup = findViewById(R.id.radioGroup);
        ansRadio_1 = findViewById(R.id.ansRadio_1);
        ansRadio_2 = findViewById(R.id.ansRadio_2);
        ansRadio_3 = findViewById(R.id.ansRadio_3);
        ansRadio_4 = findViewById(R.id.ansRadio_4);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDown.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();

        QuizFactory.reset();
        int duration = 10000;
        countDown = new QuizCountDown(duration, 10) {
            public void onFinish() {
                progressBar.setProgress(0);
                if (!showNextQuiz()) {
                    cancel();
                    startActivity(new Intent(getApplicationContext(), ResultsActivity.class).putExtra(
                            "correctAnswersNo", correctAnswersNo));
                    finish();
                } else {
                    progressBar.setMax(duration);
                    progressBar.setProgress(progressBar.getMax());
                    start();
                }
            }
        };
        countDown.start();
        showNextQuiz();
    }

    private boolean showNextQuiz() {
        Quiz quiz = QuizFactory.getNext();

        if (quiz != null) {
            nTV.setText(getString(R.string.question_id, ++id, 10));
            qTV.setText(quiz.getQuestion());
            radioGroup.clearCheck();
            ansRadio_1.setText(quiz.getAnswers()[0]);
            ansRadio_2.setText(quiz.getAnswers()[1]);
            ansRadio_3.setText(quiz.getAnswers()[2]);
            ansRadio_4.setText(quiz.getAnswers()[3]);
            correctRadioId =
                    quiz.getCorrectAnswerId() == 0 ? ansRadio_1.getId()
                            : quiz.getCorrectAnswerId() == 1 ? ansRadio_2.getId()
                            : quiz.getCorrectAnswerId() == 2 ? ansRadio_3.getId()
                            : ansRadio_4.getId();
            return true;
        }
        return false;
    }

    public void submit(View view) {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id != -1) {
            Toast.makeText(this,
                    (id == correctRadioId ? "✔ Correct" : "✘ Wrong") + " answer",
                    Toast.LENGTH_SHORT
            ).show();
            if (id == correctRadioId) correctAnswersNo++;
            countDown.onFinish();
        } else Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();
    }


    private class QuizCountDown extends CountDownTimer {
        QuizCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            progressBar.setMax((int) millisInFuture);
            progressBar.setProgress(progressBar.getMax());
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progressBar.setProgress((int) millisUntilFinished);
        }

        @Override
        public void onFinish() {
            progressBar.setProgress(0);
        }
    }
}