package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Role extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_role);
    }

    public void goToRegisterSeller(View view) {
        Intent intent = new Intent(this, Register_seller.class);
        startActivity(intent);
    }

    public void goToRegisterBuyer(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}