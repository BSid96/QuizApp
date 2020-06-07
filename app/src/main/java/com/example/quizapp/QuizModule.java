package com.example.quizapp;

public class QuizModule {
    private Integer mQuestion;
    private boolean mAnswer;

    public QuizModule(Integer question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public Integer getQuestion() {
        return mQuestion;
    }

    public void setQuestion(Integer question) {
        mQuestion = question;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
