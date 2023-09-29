package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reset_pw2 extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw2);

        dbHelper = new DatabaseHelper(this);

        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(v);
            }
        });
    }


    public void changePassword(View view) {
        EditText usernameEditText = findViewById(R.id.resetPwUsernwame);
        EditText newPasswordEditText = findViewById(R.id.resetPwPassword);

        String username = usernameEditText.getText().toString().trim();
        String password = newPasswordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter the Username and new Password", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean changeSuccessful = dbHelper.changeBuyerPassword(username, password);

        if (changeSuccessful) {
            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            usernameEditText.setText("");
            newPasswordEditText.setText("");

            // go to back
            Intent intent = new Intent(Reset_pw2.this, BuyerLogin.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Username change failed. Please check the current username", Toast.LENGTH_SHORT).show();
        }
    }
}
