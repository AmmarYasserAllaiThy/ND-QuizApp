package com.example.ammaryasser.quizapp;

import java.util.ArrayList;

class QuizFactory {

    private static final ArrayList<Quiz> quizzes = new ArrayList<>();
    private static ArrayList<Integer> oldQuizzesList = new ArrayList<>();

    /**
     * Initialize 26 different quizzes and randomly generate 10 each starting one.
     */
    static {
        quizzes.add(new Quiz(2, "Which of these letters is not a vowel?", "I", "O", "W", "A"));
        quizzes.add(new Quiz(2, "Agent 007, featured in many movies dating back to 1962, is known as _______ Bond?", "John", "Benedict", "James", "Elizier"));
        quizzes.add(new Quiz(0, "What flightless bird went extinct in the 1660s and has a reputation for stupidity?", "The dodo bird", "The Labrador duck", "The laughing owl", "The passenger pigeon"));
        quizzes.add(new Quiz(1, "The human brain communicates with the rest of the body through networks of what?", "Tendons", "Nerves", "Muscles", "Lipids"));
        quizzes.add(new Quiz(2, "Newton is said to have been inspired by what to describe the theory of gravity?", "Ladder", "Hailstone", "Apple", "Rock"));
        quizzes.add(new Quiz(1, "According to the Big Bang Theory, how did the universe begin?", "A rain storm", "An explosion", "A slow, calm expansion", "A meteor shower"));
        quizzes.add(new Quiz(1, "You want to make green paint. Which colors do you mix together?", "Red and yellow", "Blue and yellow", "Orange and purple", "Red and blue"));
        quizzes.add(new Quiz(1, "Which is the longest river in the world?", "Amazon", "Nile", "Mississippi", "Blue river"));
        quizzes.add(new Quiz(1, "Eritrea, which became the 182nd member of the UN in 1993, is in the continent of", "Asia", "Africa", "Europe", "Australia"));
        quizzes.add(new Quiz(1, "Which of the following is a prime number?", "1", "7", "12", "20"));
        quizzes.add(new Quiz(0, "What language do most people in Austria speak?", "German", "Austrian", "Hungarian", "English"));
        quizzes.add(new Quiz(2, "It takes a bright person to innovate! Who is credited with inventing the lightbulb?", "Alexander Graham Bell", "Benjamin Franklin", "Thomas Edison", "James Watt"));
        quizzes.add(new Quiz(2, "Who was president of the United States in 2005?", "Bill Clinton", "Barack Obama", "George W. Bush", "George H.W. Bush"));
        quizzes.add(new Quiz(0, "Which country is closer to the North Pole?", "Finland", "China", "Germany", "North Korea"));
        quizzes.add(new Quiz(1, "Which way do the longitude lines run on our planet?", "East to West", "North to South", "West to North", "South to East"));
        quizzes.add(new Quiz(0, "Where are the famous Egyptian Pyramids located?", "Giza", "Cairo", "Thebes", "Alexandria"));
        quizzes.add(new Quiz(1, "Which ocean is the largest?", "Atlantic", "Pacific", "Arab Gulf", "Indian"));
        quizzes.add(new Quiz(0, "Which of the following units of measurement is NOT part of the Metric System?", "Ounces", "Meters", "Grams", "Liter"));
        quizzes.add(new Quiz(1, "The bright stuff spewing out of this volcano is called...?", "Fire", "Lava", "Magma", "Honey"));
        quizzes.add(new Quiz(2, "Which German city is famous for the perfume it produces?", "Berlin", "Sniffburg", "Cologne", "Bremen"));
        quizzes.add(new Quiz(3, "For which of the following disciplines is Nobel Prize awarded?", "Physics and Chemistry", "Physiology or Medicine", "Literature, Peace and Economics", "All of the above"));
        quizzes.add(new Quiz(1, "Hitler party which came into power in 1933 is known as", "Labour Party", "Nazi Party", "Ku-Klux-Klan", "Democratic Party"));
        quizzes.add(new Quiz(3, "Galileo was an Italian astronomer who", "developed the telescope", "developed the telescope", "discovered that the movement of pendulum produces a regular time measurement", "All of the above"));
        quizzes.add(new Quiz(0, "First China War was fought between", "China and Britain", "China and France", "China and Egypt", "China and Greek"));
    }

    static Quiz getNext() {
        if (oldQuizzesList.size() < 10) {
            int n = getRandomQuiz();
            while (isOldQuiz(n)) n = getRandomQuiz();
            return quizzes.get(n);
        }
        return null;
    }

    private static boolean isOldQuiz(int newQuiz) {
        for (int oldQuiz : oldQuizzesList) if (oldQuiz == newQuiz) return true;
        oldQuizzesList.add(newQuiz);
        return false;
    }

    private static int getRandomQuiz() {
        return (int) (Math.random() * quizzes.size());
    }

    static void reset() {
        oldQuizzesList = new ArrayList<>();
    }
}