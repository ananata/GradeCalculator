package com.example.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    /*===============
     Declare variables
     ===============*/
    double assignPoints, assignTotal, projectPoints, projectTotal, midtermPoints, midtermTotal, finalPoints, finalTotal;
    EditText assignEarned, assignPoss, projectEarned, projectPoss, midtermEarned, midtermPoss, finalEarned, finalPoss;
    double assignPercent, projectPercent, midtermPercent, finalPercent;
    double courseGrade;

    /*====================
     Initialize constants
     ====================*/
    final int ASSIGN_WEIGHT = 20;
    final double PROJECT_WEIGHT = 30;
    final double MIDTERM_WEIGHT = 30;
    final double FINAL_WEIGHT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*=============================
         Associate variables with objects
         ==============================*/
        assignEarned = (EditText) findViewById(R.id.editTextAssignmentEarned);
        assignPoss = (EditText) findViewById(R.id.editTextAssignmentTotal);
        projectEarned = (EditText) findViewById(R.id.editTextProjectEarned);
        projectPoss = (EditText) findViewById(R.id.editTextProjectTotal);
        midtermEarned = (EditText) findViewById(R.id.editTextMidtermEarned);
        midtermPoss = (EditText) findViewById(R.id.editTextMidtermTotal);
        finalEarned = (EditText) findViewById(R.id.editTextFinalEarned);
        finalPoss = (EditText) findViewById(R.id.editTextFinalTotal);
        final Button buttonCalc = findViewById(R.id.buttonCalculate);


        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*==================================
                 Store total points earned in class
                 ==================================*/
                double total = 0;

                /*=====================================
                 Get user inputs and calculate percents
                 =====================================*/

                // Assignment Points
                try {
                    assignPoints = Double.parseDouble(assignEarned.getText().toString());
                    assignTotal = Double.parseDouble(assignPoss.getText().toString());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Please enter valid numbers for assignment points earned and/or possible");
                }
                if (assignTotal > 0) {
                    assignPercent = assignPoints / assignTotal;
                } else {
                    throw new ArithmeticException("Assignment points possible must exceed zero");
                }
                total += (assignPercent * ASSIGN_WEIGHT);


                // Project Points
                try {
                    projectPoints = Double.parseDouble(projectEarned.getText().toString());
                    projectTotal = Double.parseDouble(projectPoss.getText().toString());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Please enter valid numbers for project points earned and/or possible");
                }
                if (projectTotal > 0) {
                    projectPercent = projectPoints / projectTotal;
                } else {
                    throw new ArithmeticException("Project points possible must exceed zero");
                }
                total += (projectPercent * PROJECT_WEIGHT);


                // Mid-term Points
                try {
                    midtermPoints = Double.parseDouble(midtermEarned.getText().toString());
                    midtermTotal = Double.parseDouble(midtermPoss.getText().toString());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Please enter valid numbers for midterm points earned and/or possible");
                }
                if (midtermTotal > 0) {
                    midtermPercent = midtermPoints / midtermTotal;
                } else {
                    throw new ArithmeticException("Midterm points possible must exceed zero");
                }
                total += (midtermPercent * MIDTERM_WEIGHT);


                // Final Points
                try {
                    finalPoints = Double.parseDouble(finalEarned.getText().toString());
                    finalTotal = Double.parseDouble(finalPoss.getText().toString());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Please enter valid numbers for final points earned and/or possible");
                }
                if (finalTotal > 0) {
                    finalPercent = finalPoints / finalTotal;
                } else {
                    throw new ArithmeticException("Final points possible must exceed zero");
                }
                total += (finalPercent * FINAL_WEIGHT);

                /*================================
                Calculate course grade and display
                 ================================*/
                courseGrade = total / 100;
                showToast("Course grade: " + customFormat("###.#", courseGrade * 100) + "%"); showToast(getLetterGrade(total));
                clear(assignEarned); clear(assignPoss); clear(projectEarned); clear(projectPoss); clear(midtermEarned); clear(midtermPoss); clear(finalEarned); clear(finalPoss);
            }
        });
    }

    /**
     * Generates pop-up objects that display information
     * temporarily.
     * @param grade
     */
    private void showToast(String grade) {
        //String message = "Your course grade calculates to: " + grade;
        Toast.makeText(MainActivity.this, grade, Toast.LENGTH_SHORT).show();
    }

    /**
     * Clears input text field
     * @param object
     */
    private void clear(EditText object) {
        object.setText("");
    }

    /**
     * Generates letter grade associated with the total number
     * of points earned during the semester.
     * Based on 10-point grading scale.
     * @param totalPoints
     * @return
     */
    private String getLetterGrade(double totalPoints) {
        String result = "Letter Grade: ";

        /*===============
        Get letter grade
         ===============*/
        if (totalPoints < 60) { // 0 - 59
            result += "F";
        }
        else if (totalPoints < 70) { // 60 - 69
            result += "D";
        }
        else if (totalPoints < 80) { // 70 - 79
            result += "C";
        }
        else if (totalPoints < 90){
            result += "B";
        }
        else {
            result += "A";
        }

        return result;
    }

    private String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

}
