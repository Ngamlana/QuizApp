package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        EditText editTextQuestion2 = findViewById(R.id.question_2);

        CheckBox checkBoxQuestion2Greece = findViewById(R.id.question_2_Greece);
        CheckBox checkBoxQuestion2Burma = findViewById(R.id.question_2_Burma);
        CheckBox checkBoxQuestion2Luxembourg = findViewById(R.id.question_2_Luxembourg);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Greece.isChecked() == true && checkBoxQuestion2Burma.isChecked() == false && checkBoxQuestion2Luxembourg.isChecked() == true) {
            answerQuestion2 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = editTextQuestion2.getText().toString().toLowerCase();
        ret[2] = Boolean.toString(answerQuestion2);
        ret[3] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();


        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"paris", "south africa", "true", "south"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 4. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String) radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        EditText editAnotherText = findViewById(R.id.question_2);
        editAnotherText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_Greece);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Burma);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Luxembourg);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();
    }
}