package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void goToItemManagement(View view) {
        Intent intent = new Intent(this, Item_management.class);
        startActivity(intent);
    }

    public void goToCustomerManagement(View view) {
        Intent intent = new Intent(this, Customer_management.class);
        startActivity(intent);
    }

    public void goToViewReviews(View view) {
        Intent intent = new Intent(this, OurWork.class);
        startActivity(intent);
    }

    public void goToViewFeedback(View view) {
        Intent intent = new Intent(this, ViewFeedback.class);
        startActivity(intent);
    }
    public void moreButton(View view) {
        Intent intent = new Intent(this, more.class);
        startActivity(intent);
    }
}