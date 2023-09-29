package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Role2 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role2);

        databaseHelper = new DatabaseHelper(this);

    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

        finish();
    }
    public void goToBuyerLogin(View view) {
        Intent intent = new Intent(this, BuyerLogin.class);
        startActivity(intent);

        finish();
    }
}