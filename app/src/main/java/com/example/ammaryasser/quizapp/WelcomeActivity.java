package com.example.ammaryasser.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private final View.OnClickListener startListener = view -> startActivity(new Intent(this, MainActivity.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.start_btn).setOnClickListener(startListener);
    }
}