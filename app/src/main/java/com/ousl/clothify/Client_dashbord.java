package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Client_dashbord extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> item, category, price, description;
    // Add a new ArrayList for the image column
    ArrayList<byte[]> image;
    DatabaseHelper myDb;
    ClientItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_client_dashbord);

        myDb = new DatabaseHelper(this);

        item = new ArrayList<>();
        category = new ArrayList<>();
        price = new ArrayList<>();
        description = new ArrayList<>();
        image = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview1);
        adapter = new ClientItemsAdapter(this,item,category,price,description, image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // calling display data method
        displaydata();
    }

    // view item method
    private void displaydata() {
        Cursor result = myDb.getAllData();
        if (result.getCount() == 0) {
            Toast.makeText(Client_dashbord.this, "No Items Exists", Toast.LENGTH_LONG).show();
            return;
        } else {
            while (result.moveToNext()) {
                item.add(result.getString(1));
                category.add(result.getString(2));
                price.add(result.getString(3));
                description.add(result.getString(4));

                // Retrieve the image byte array and add it to the image ArrayList
                byte[] imageBytes = result.getBlob(5);
                image.add(imageBytes);
            }
            adapter.notifyDataSetChanged();  // Notify the adapter about the data change // delete if you want
        }
    }

    public void goToUserFeedback(View view) {
        Intent intent = new Intent(this, UserFeedback.class);
        startActivity(intent);
    }

    public void goToReview(View view) {
        Intent intent = new Intent(this, Review.class);
        startActivity(intent);
    }

    public void goToOurWork(View view) {
        Intent intent = new Intent(this, OurWork.class);
        startActivity(intent);
    }

    public void moreButton(View view) {
        Intent intent = new Intent(this, more2.class);
        startActivity(intent);
    }
}