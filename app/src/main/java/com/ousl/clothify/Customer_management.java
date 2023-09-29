package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Customer_management extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> id, name, phone, email;
    DatabaseHelper myDb;
    CustomerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);
        myDb = new DatabaseHelper(this);

        id = new ArrayList<>();
        name = new ArrayList<>();
        phone = new ArrayList<>();
        email = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new CustomerAdapter(this, id, name, phone, email);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // calling display data method
        displaydata();
    }


    // view item method
    private void displaydata() {
        Cursor result = myDb.getAllCustomers();
        if (result.getCount() == 0){
            Toast.makeText(Customer_management.this,"No Customers", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (result.moveToNext()){
                id.add(result.getString(0));
                name.add(result.getString(1));
                phone.add(result.getString(2));
                email.add(result.getString(3));

            }
            adapter.notifyDataSetChanged();  // Notify the adapter about the data change // delete if you want
        }

    }

}

