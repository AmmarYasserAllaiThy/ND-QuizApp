package com.example.ammaryasser.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private CheckBox checkBox;
    private EditText et;

    private QuizFactory quizFactory;
    private QuizCountDown countDown;
    private int id = 0;
    private int correctRadioId;
    private int correctAnswersNo = 0;

    private final RadioGroup.OnCheckedChangeListener groupListener = (group, checkedId) -> checkBox.setChecked(checkedId == -1);
    private final CheckBox.OnCheckedChangeListener boxListener = (buttonView, isChecked) -> {
        et.setEnabled(isChecked);
        if (isChecked) {
            radioGroup.clearCheck();
//            countDown.cancel();
        }
//        else countDown.start();
        /* fixme...
             It should pause the countdown and resume it when submit but it doesn't have a pause()
             And if i used cancel() and then start() it
             It starts a new countdown.
         */
    };
    private final View.OnClickListener submitListener = view -> {
        int id = radioGroup.getCheckedRadioButtonId();
        if (checkBox.isChecked() || id != -1) {
            Toast.makeText(this,
                    (id == correctRadioId ? "✔ Correct" : "✘ Wrong") + " answer",
                    Toast.LENGTH_SHORT
            ).show();
            if (id == correctRadioId) correctAnswersNo++;
            countDown.onFinish();
        } else Toast.makeText(this, "No answer selected", Toast.LENGTH_SHORT).show();
    };

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
        checkBox = findViewById(R.id.checkbox);
        et = findViewById(R.id.et);
        Button submitBTN = findViewById(R.id.submit_btn);

        radioGroup.setOnCheckedChangeListener(groupListener);
        checkBox.setOnCheckedChangeListener(boxListener);
        submitBTN.setOnClickListener(submitListener);

        quizFactory = new QuizFactory(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDown.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        quizFactory.reset();
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
        Quiz quiz = quizFactory.getNext();

        if (quiz != null) {
            nTV.setText(getString(R.string.question_id, ++id, 10));
            qTV.setText(quiz.getQuestion());
            radioGroup.clearCheck();
            checkBox.setChecked(false);
            et.setText("");
            ansRadio_1.setText(quiz.getAnswers()[0]);
            ansRadio_2.setText(quiz.getAnswers()[1]);
            ansRadio_3.setText(quiz.getAnswers()[2]);
            correctRadioId =
                    quiz.getCorrectAnswerId() == 0 ? ansRadio_1.getId()
                            : quiz.getCorrectAnswerId() == 1 ? ansRadio_2.getId()
                            : ansRadio_3.getId();
            return true;
        }
        return false;
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
        }
    }
}