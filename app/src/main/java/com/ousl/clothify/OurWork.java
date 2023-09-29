package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class OurWork extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> description;

    // Add a new ArrayList for the image column
    ArrayList<byte[]> image;
    DatabaseHelper myDb;
    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_work);
        //getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);
        image = new ArrayList<>();
        description = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ReviewAdapter(this, image, description);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // calling display data method
        displaydata();
    }


    // view item method
    private void displaydata() {
        Cursor result = myDb.getAllReviews();
        if (result.getCount() == 0){
            Toast.makeText(OurWork.this,"No Reviews Exists", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (result.moveToNext()){

                // Retrieve the image byte array and add it to the image ArrayList
                byte[] imageBytes = result.getBlob(1);
                image.add(imageBytes);
                description.add(result.getString(2));
            }
            adapter.notifyDataSetChanged();  // Notify the adapter about the data change // delete if you want
        }

    }


}



