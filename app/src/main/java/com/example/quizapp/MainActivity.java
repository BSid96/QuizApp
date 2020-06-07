package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView mTextQuestion;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private int usersScore;
    private int wrongScore;
    final String CORRECT = "correct";
    final String WRONG = "wrong";
    final String INDEX = "index";
    QuizModule q1;

    private QuizModule[] questionCollection = new QuizModule[]{
            new QuizModule(R.string.q1, true),
            new QuizModule(R.string.q2, false),
            new QuizModule(R.string.q3, true),
            new QuizModule(R.string.q4, false),
            new QuizModule(R.string.q5, true),
            new QuizModule(R.string.q6, false),
            new QuizModule(R.string.q7, true),
            new QuizModule(R.string.q8, false),
            new QuizModule(R.string.q9, true),
            new QuizModule(R.string.q10, false),

    };
    final int UserProgress = (100 / questionCollection.length) + 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            usersScore = savedInstanceState.getInt(CORRECT);
            wrongScore = savedInstanceState.getInt(WRONG);
            mQuestionIndex = savedInstanceState.getInt(INDEX);
        } else {
            usersScore = 0;
            wrongScore = 0;
            mQuestionIndex = 0;
        }



        mTextQuestion = findViewById(R.id.txtQuestion);
        q1 = questionCollection[mQuestionIndex];

        mTextQuestion.setText(q1.getQuestion());


        View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnTrue) {
                    evaluateUsersAnswer(true);
                } else {
                    evaluateUsersAnswer(false);
                }
                changeQuestion();
            }
        };

        btnTrue = findViewById(R.id.btnTrue);
        btnTrue.setOnClickListener(myClickListener);
        btnWrong = findViewById(R.id.btnWrong);
        btnWrong.setOnClickListener(myClickListener);

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);
        if ((usersScore != 0) || (wrongScore != 0)) {
            String show = "You have got " + usersScore + " correct answers and " + wrongScore + " wrong!";
            mQuizStatsTextView.setText(show);
            q1 = questionCollection[mQuestionIndex];

            mTextQuestion.setText(q1.getQuestion());
        }


    }

    private void changeQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % 10;

        if (mQuestionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is " + usersScore + " out of " + questionCollection.length + " !");
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }

        q1 = questionCollection[mQuestionIndex];

        mTextQuestion.setText(q1.getQuestion());
        mProgressBar.incrementProgressBy(UserProgress);
    }

    private void evaluateUsersAnswer(boolean userGuess) {

        boolean currentQuestionsAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (userGuess == currentQuestionsAnswer) {
            usersScore += 1;
            String show = "You have got " + usersScore + " correct answers and " + wrongScore + " wrong!";
            mQuizStatsTextView.setText(show);

        } else {
            wrongScore += 1;
            String show = "You have got " + usersScore + " correct answers and " + wrongScore + " wrong!";
            mQuizStatsTextView.setText(show);
        }
    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CORRECT,usersScore);
        outState.putInt(WRONG,wrongScore);
        outState.putInt(INDEX,mQuestionIndex);

    }
}

