package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class viewItem extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> id, item, category, price, description;

    // Add a new ArrayList for the image column
    ArrayList<byte[]> image;
    DatabaseHelper myDb;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        //getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);

        id = new ArrayList<>();
        item = new ArrayList<>();
        category = new ArrayList<>();
        price = new ArrayList<>();
        description = new ArrayList<>();
        image = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, id, item, category, price, description, image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // calling display data method
        displaydata();
    }


    // view item method
    private void displaydata() {
        Cursor result = myDb.getAllData();
        if (result.getCount() == 0){
            Toast.makeText(viewItem.this,"No Items Exists", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (result.moveToNext()){
                id.add(result.getString(0));
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

}
