package com.ousl.clothify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Login extends AppCompatActivity implements SensorEventListener {

    private MediaPlayer mp;
    private boolean isRunning = false;
    private SensorManager sensorManager;
    private Sensor lightSensor;

    // authentication
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //forgot password button
        TextView textView = findViewById(R.id.textView9);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResetPw();
            }
        });

        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.editTextTextPersonName);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (databaseHelper.checkUserCredentials(username, password)) {
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 500 && !isRunning) {
            isRunning = true;
            mp = new MediaPlayer();
            try {
                // Setting audio file path
                int resId = getResources().getIdentifier("sensor", "raw", getPackageName());
                mp.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + resId));
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.values[0] <= 40 && isRunning && mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            isRunning = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    @Override
    protected void onResume() {
        super.onResume();
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        if (mp != null) {
            mp.release();
        }
    }

    public void goToRegisterSeller(View view) {
        Intent intent = new Intent(this, Register_seller.class);
        startActivity(intent);
    }

    public void goToResetPw() {
        Intent intent = new Intent(this, Reset_pw.class);
        startActivity(intent);
    }

    public void goToRole2(View view) {
        Intent intent = new Intent(this, Role2.class);
        startActivity(intent);

        finish();
    }

}