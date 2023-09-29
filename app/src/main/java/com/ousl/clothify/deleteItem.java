package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteItem extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText removeId;
    Button btnDeleteItem, btnViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);
        //getSupportActionBar().hide();

        // database instance
        myDb = new DatabaseHelper(this);

        // define text fields and buttons
        removeId = findViewById(R.id.textItemId);
//        btnViewItem = findViewById(R.id.viewResentItemButton);
        btnDeleteItem = findViewById(R.id.deleteItemButton);


        // calling item delete method
        deleteData();
    }


    // show message via Alert Dialog Box
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    // delete item method
    public boolean deleteData(){
        btnDeleteItem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedatarows = myDb.deleteData(removeId.getText().toString());
                        if (deletedatarows>0)
                            Toast.makeText(deleteItem.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(deleteItem.this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return false;
    }

    // navigation to add new item screen
    public void goToViewItem(View view) {
        Intent intent = new Intent(this, viewItem.class);
        startActivity(intent);
    }
}