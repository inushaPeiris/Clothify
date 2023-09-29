package com.ousl.clothify;

import static com.ousl.clothify.DatabaseHelper.isValidEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_buyer extends AppCompatActivity {

    // Importing database helper
    DatabaseHelper myDb;

    // Importing text fields and buttons
    EditText editName, editPhone, editEmail, editUserName, editPassword;
    Button btnAddBuyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_register_buyer);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.buyRegName);
        editPhone = findViewById(R.id.buyRegPhone);
        editEmail = findViewById(R.id.buyRegEmail);
        editUserName = findViewById(R.id.buyRegUserName);
        editPassword = findViewById(R.id.buyRegPassword);

        btnAddBuyer = findViewById(R.id.buyRegButton);

        // Call the method to add buyer
        addBuyer();
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, BuyerLogin.class);
        startActivity(intent);
    }

    public void addBuyer() {
        btnAddBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String email = editEmail.getText().toString();
                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();




                // Check if any of the fields are empty
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Register_buyer.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }

                // Email validation
                else if (!isValidEmail(email)) {
                    Toast.makeText(Register_buyer.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }

                // Phone number validation
                else if (phone.length() != 10) {
                    Toast.makeText(Register_buyer.this, "Phone number should have 10 digits", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDb.insertBuyerData(name, phone, email, username, password);
                    if (isInserted) {
                        Toast.makeText(Register_buyer.this, "Successfully Registered!", Toast.LENGTH_LONG).show();

                        // go to LOGIN activity
                        Intent intent = new Intent(Register_buyer.this, BuyerLogin.class);
                        startActivity(intent);

                        finish();

                    } else {
                        Toast.makeText(Register_buyer.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
