package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateItemActivity extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText editItemId, editItemName, editCategory, editPrice, editDescription;
    Button btnUpdateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_update_item);

        // database instance
        myDb = new DatabaseHelper(this);

        // define text fields and buttons
        editItemId = findViewById(R.id.editItemId);
        editItemName = findViewById(R.id.editItemName);
        editCategory = findViewById(R.id.editItemCategory);
        editPrice = findViewById(R.id.editItemPrice);
        editDescription = findViewById(R.id.editItemDescription);
        btnUpdateItem = findViewById(R.id.btnUpdateItem);

        // get the item ID from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int itemId = extras.getInt("itemId");
            String itemName = extras.getString("itemName");
            String category = extras.getString("category");
            String price = extras.getString("price");
            String description = extras.getString("description");

            // set the existing item details in the edit fields
            editItemId.setText(String.valueOf(itemId));
            editItemName.setText(itemName);
            editCategory.setText(category);
            editPrice.setText(price);
            editDescription.setText(description);
        }

        // calling update item method
        updateItem();
    }

    // update item method
    public void updateItem() {
        btnUpdateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = Integer.parseInt(editItemId.getText().toString());
                boolean isUpdated = myDb.updateItem(itemId,
                        editItemName.getText().toString(),
                        editCategory.getText().toString(),
                        editPrice.getText().toString(),
                        editDescription.getText().toString());

                if (isUpdated) {
                    Toast.makeText(UpdateItemActivity.this, "Item Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateItemActivity.this, "Failed to update item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // navigation to add new item screen
    public void goToViewItem(View view) {
        Intent intent = new Intent(this, viewItem.class);
        startActivity(intent);
    }
}
