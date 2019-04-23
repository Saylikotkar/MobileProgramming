package com.example.chp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Questions[] mQuestionBank = new Questions[]
            {
                    new Questions(R.string.question_california,
                            true),
                    new Questions(R.string.question_oceans, true),
                    new Questions(R.string.question_mideast,
                            false),
                    new Questions(R.string.question_africa,
                            false),
                    new Questions(R.string.question_americas,
                            true),
                    new Questions(R.string.question_asia, true),
            };
    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                                           }
                                       }
        );
        // to show question at the start
        updateQuestion();
    }

         private void updateQuestion() {
          int question = mQuestionBank[mCurrentIndex].getTextResId();
           mQuestionTextView.setText(question);
           mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

     }




    private void checkAnswer(Boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if(userPressedTrue == answerIsTrue)
            messageResId = R.string.correct_toast;

        else messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }



    }

