package com.ousl.clothify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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

public class Review extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    private static final int REQUEST_CODE = 1;
    ImageView editImage;
    EditText editText;
    Button btnSubmit;
    Uri selectedImageUri;
    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // database instance
        myDb = new DatabaseHelper(this);
        // define text fields and buttons
        editText = findViewById(R.id.textReview);
        btnSubmit = findViewById(R.id.btnSubmit);
        editImage = findViewById(R.id.ReviewImageView);


        // Open Image picker
        editImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            // calling add new item method
            public void onClick(View v) {
                if (imageBytes != null) {
                    saveReviewToDatabase(imageBytes);

                    // Clear the imageBytes array
                    imageBytes = null;

                }
//                // Opening the MyReview class with the added data
//                Intent intent = new Intent(Review.this, MyReview.class);
//                intent.putExtra("reviewText", editText.getText().toString());
//                intent.putExtra("imageBytes", imageBytes);
//                startActivity(intent);
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
    private void saveReviewToDatabase(byte[] imageBytes) {
        SQLiteDatabase db = myDb.getWritableDatabase();
        String reviewText = editText.getText().toString();

        db.execSQL("INSERT INTO " + DatabaseHelper.REVIEW_TABLE +
                        "(" + DatabaseHelper.REVIEW_COL2 + ", " + DatabaseHelper.REVIEW_COL3 + ") VALUES (?, ?)",
                new Object[]{imageBytes, reviewText});

        Toast.makeText(this, "Review Saved Successfully", Toast.LENGTH_SHORT).show();

        // go to back
        Intent intent = new Intent(Review.this, Client_dashbord.class);
        startActivity(intent);

        finish();
    }

}

