package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        // Set the content view for the splash screen
        setContentView(R.layout.activity_main);

        // Create a 3-second delay before launching the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Role2.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}