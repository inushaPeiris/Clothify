package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserFeedback extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText editTopic, editDescription;
    Button buttonSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_user_feedback);

        // database instance
        myDb = new DatabaseHelper(this);

        // define text fields and buttons
        editTopic = findViewById(R.id.editFeedbackTopic);
        editDescription = findViewById(R.id.editFeedbackDescription);
        buttonSubmit = findViewById(R.id.buttonFeedbackSubmit);

        // calling add new item method
        addNewFeedback();
    }

    // add new item method
    public void addNewFeedback(){
        buttonSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertFeedback(
                                editTopic.getText().toString(),
                                editDescription.getText().toString());

                        if (isInserted == true){
                            Toast.makeText(UserFeedback.this, "Feedback Added", Toast.LENGTH_SHORT).show();

                            // go to back
                            Intent intent = new Intent(UserFeedback.this, Client_dashbord.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(UserFeedback.this, "Feedback Not Added", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}