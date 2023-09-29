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

public class Register_seller extends AppCompatActivity {


    //importing database helper
    DatabaseHelper myDb;
//    importing text feilds and buttons
    EditText editName, editPhone, editeEmail, editUserName, editPassword;
    Button btnAddSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_register_seller);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.selRegName);
        editPhone = findViewById(R.id.selRegPhone);
        editeEmail = findViewById(R.id.selRegEmail);
        editUserName = findViewById(R.id.selRegUserName);
        editPassword = findViewById(R.id.selRegPassword);

        btnAddSeller = findViewById(R.id.selRegButton);

//        calling method
        addSeller();
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void addSeller(){
        btnAddSeller.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editName.getText().toString();
                        String phone = editPhone.getText().toString();
                        String email = editeEmail.getText().toString();
                        String username = editUserName.getText().toString();
                        String password = editPassword.getText().toString();

                        // Check if any of the fields are empty
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                            Toast.makeText(Register_seller.this, "Please fill all the fields", Toast.LENGTH_LONG).show();

                        }

                        // email validation
                        else if (!isValidEmail(email)) {
                            Toast.makeText(Register_seller.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                        }

                        // phone number validation
                        else if (phone.length() != 10)
                            Toast.makeText(Register_seller.this, "Phone number should have 10 digits", Toast.LENGTH_SHORT).show();

                        else{
                            boolean isInserted = myDb.insertData(name, phone, email, username, password);
                            if(isInserted == true) {
                                Toast.makeText(Register_seller.this,"Successfully Registered! ", Toast.LENGTH_LONG).show();

                                // go to LOGIN activity
                                Intent intent = new Intent(Register_seller.this, Login.class);
                                startActivity(intent);

                                finish();
                            }
                            else
                                Toast.makeText(Register_seller.this,"Registration Failed! ", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

}
