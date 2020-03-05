package com.example.ammaryasser.quizapp;

import android.content.Context;

import java.util.ArrayList;

class QuizFactory {

    private final ArrayList<Quiz> quizzes;
    private ArrayList<Integer> oldQuizzesList;
    private Context context;

    /**
     * Initialize 24 different quizzes and randomly generate 10 each starting one.
     */
    QuizFactory(Context context) {
        this.context = context;
        quizzes = new ArrayList<>();
        quizzes.add(new Quiz(getInteger(R.integer.q1), getString(R.string.q1), getStringArray(R.array.q1)));
        quizzes.add(new Quiz(getInteger(R.integer.q2), getString(R.string.q2), getStringArray(R.array.q2)));
        quizzes.add(new Quiz(getInteger(R.integer.q3), getString(R.string.q3), getStringArray(R.array.q3)));
        quizzes.add(new Quiz(getInteger(R.integer.q4), getString(R.string.q4), getStringArray(R.array.q4)));
        quizzes.add(new Quiz(getInteger(R.integer.q5), getString(R.string.q5), getStringArray(R.array.q5)));
        quizzes.add(new Quiz(getInteger(R.integer.q6), getString(R.string.q6), getStringArray(R.array.q6)));
        quizzes.add(new Quiz(getInteger(R.integer.q7), getString(R.string.q7), getStringArray(R.array.q7)));
        quizzes.add(new Quiz(getInteger(R.integer.q8), getString(R.string.q8), getStringArray(R.array.q8)));
        quizzes.add(new Quiz(getInteger(R.integer.q9), getString(R.string.q9), getStringArray(R.array.q9)));
        quizzes.add(new Quiz(getInteger(R.integer.q10), getString(R.string.q10), getStringArray(R.array.q10)));
        quizzes.add(new Quiz(getInteger(R.integer.q11), getString(R.string.q11), getStringArray(R.array.q11)));
        quizzes.add(new Quiz(getInteger(R.integer.q12), getString(R.string.q12), getStringArray(R.array.q12)));
        quizzes.add(new Quiz(getInteger(R.integer.q13), getString(R.string.q13), getStringArray(R.array.q13)));
        quizzes.add(new Quiz(getInteger(R.integer.q14), getString(R.string.q14), getStringArray(R.array.q14)));
        quizzes.add(new Quiz(getInteger(R.integer.q15), getString(R.string.q15), getStringArray(R.array.q15)));
        quizzes.add(new Quiz(getInteger(R.integer.q16), getString(R.string.q16), getStringArray(R.array.q16)));
        quizzes.add(new Quiz(getInteger(R.integer.q17), getString(R.string.q17), getStringArray(R.array.q17)));
        quizzes.add(new Quiz(getInteger(R.integer.q18), getString(R.string.q18), getStringArray(R.array.q18)));
        quizzes.add(new Quiz(getInteger(R.integer.q19), getString(R.string.q19), getStringArray(R.array.q19)));
        quizzes.add(new Quiz(getInteger(R.integer.q20), getString(R.string.q20), getStringArray(R.array.q20)));
        quizzes.add(new Quiz(getInteger(R.integer.q21), getString(R.string.q21), getStringArray(R.array.q21)));
        quizzes.add(new Quiz(getInteger(R.integer.q22), getString(R.string.q22), getStringArray(R.array.q22)));
        quizzes.add(new Quiz(getInteger(R.integer.q23), getString(R.string.q23), getStringArray(R.array.q23)));
        quizzes.add(new Quiz(getInteger(R.integer.q24), getString(R.string.q24), getStringArray(R.array.q24)));
        reset();
    }

    void reset() {
        oldQuizzesList = new ArrayList<>();
    }

    Quiz getNext() {
        if (oldQuizzesList.size() < 10) {
            int n = getRandomQuiz();
            while (isOldQuiz(n)) n = getRandomQuiz();
            return quizzes.get(n);
        }
        return null;
    }

    private boolean isOldQuiz(int newQuiz) {
        for (int oldQuiz : oldQuizzesList) if (oldQuiz == newQuiz) return true;
        oldQuizzesList.add(newQuiz);
        return false;
    }

    private int getRandomQuiz() {
        return (int) (Math.random() * quizzes.size());
    }

    private int getInteger(int resId) {
        return context.getResources().getInteger(resId);
    }

    private String getString(int resId) {
        return context.getString(resId);
    }

    private String[] getStringArray(int resId) {
        return context.getResources().getStringArray(resId);
    }
}