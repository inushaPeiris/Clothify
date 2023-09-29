package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class more extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
    }
    public void logOutButton(View view) {
        Intent intent = new Intent(this, Role2.class);
        startActivity(intent);

        finish();
    }

    public void ResetPwButton(View view) {
        Intent intent = new Intent(this, Reset_pw.class);
        startActivity(intent);
    }
}