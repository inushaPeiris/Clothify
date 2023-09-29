package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFeedback extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> topic, description;
    DatabaseHelper myDb;
    FeedbackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        //getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);

        topic = new ArrayList<>();
        description = new ArrayList<>();

        recyclerView = findViewById(R.id.feedbackRecyclerview);
        adapter = new FeedbackAdapter(this,topic,description);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // calling feedback method
        displayFeedback();
    }


    // view feedback method
    private void displayFeedback() {
        Cursor result = myDb.getAllFeedback();
        if (result.getCount() == 0){
            Toast.makeText(ViewFeedback.this,"No Feedback Exists", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (result.moveToNext()){
                topic.add(result.getString(1));
                description.add(result.getString(2));
            }
        }

    }
}
