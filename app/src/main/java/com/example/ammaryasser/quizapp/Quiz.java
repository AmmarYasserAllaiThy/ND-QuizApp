package com.example.ammaryasser.quizapp;

import java.util.Arrays;

public class Quiz {

    private int correctAnswerId;
    private String question;
    private String[] answers;

    public Quiz(int correctAnswerId, String question, String... answers) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "correctAnswerId=" + correctAnswerId +
                ", question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}