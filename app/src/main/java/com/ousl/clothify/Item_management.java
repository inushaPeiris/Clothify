package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Item_management extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_management);
        //getSupportActionBar().hide();

        // database instance
        myDb = new DatabaseHelper(this);

    }

    // navigation to add new item screen
    public void goToAddNewItem(View view) {
        Intent intent = new Intent(this, Add_new_item.class);
        startActivity(intent);
    }

    // navigation to add new item screen
    public void goToViewItem(View view) {
        Intent intent = new Intent(this, viewItem.class);
        startActivity(intent);
    }

    // navigation to update item screen
    public void goToUpdateItem(View view) {
        Intent intent = new Intent(this, UpdateItemActivity.class);
        startActivity(intent);
    }

    // navigation to add new item screen
    public void goToDeleteItem(View view) {
        Intent intent = new Intent(this, deleteItem.class);
        startActivity(intent);
    }
}