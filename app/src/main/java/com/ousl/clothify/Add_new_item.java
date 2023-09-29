package com.ousl.clothify;

// import packages
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Add_new_item extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    private static final int REQUEST_CODE = 1;
    ImageView editImage;
    EditText editItem, editCategory, editPrice, editDescription;
    Button btnAddNewItem;
    Uri selectedImageUri;
    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_add_new_item);

        // database instance
        myDb = new DatabaseHelper(this);
        // define text fields and buttons
        editItem = findViewById(R.id.textItemName);
        editCategory = findViewById(R.id.textItemCategory);
        editPrice = findViewById(R.id.textItemPrice);
        editDescription = findViewById(R.id.textItemDescription);
        btnAddNewItem = findViewById(R.id.addItemButton);
        editImage = findViewById(R.id.AddItemImageView);

        // Open Image picker
        editImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnAddNewItem = findViewById(R.id.addItemButton);
        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            // calling add new item method
            public void onClick(View v) {
                if (imageBytes != null) {
                    saveItemToDatabase(imageBytes);
                    // Clear the imageBytes array
                    imageBytes = null;


                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Bitmap bitmap = decodeUri(data.getData());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageBytes = stream.toByteArray();


            // Set the selected image to the ImageView
            Uri selectedImageUri = data.getData();
            editImage.setImageURI(selectedImageUri);
        }
    }

    private Bitmap decodeUri(Uri selectedImage) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;
            float ratio = Math.min((float) imageWidth / 500, (float) imageHeight / 500);
            options.inSampleSize = (int) ratio;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // function to add values to the database
    private void saveItemToDatabase(byte[] imageBytes) {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String addItem = editItem.getText().toString();
        String addCategory = editCategory.getText().toString();
        String addPrice = editPrice.getText().toString();
        String addDescription = editDescription.getText().toString();

        db.execSQL("INSERT INTO " + DatabaseHelper.ITEM_TABLE +
                        "(" + DatabaseHelper.ITEM_COL_2 + ", " + DatabaseHelper.ITEM_COL_3 + ", " + DatabaseHelper.ITEM_COL_4 + ", " + DatabaseHelper.ITEM_COL_5 + ", " + DatabaseHelper.ITEM_COL_6 + ") VALUES (?, ?, ?, ?, ?)",
                new Object[]{addItem, addCategory, addPrice, addDescription, imageBytes});

        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();

        // go to back
        Intent intent = new Intent(Add_new_item.this, Item_management.class);
        startActivity(intent);

        finish();

    }

}

