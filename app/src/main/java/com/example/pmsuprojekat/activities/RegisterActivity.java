package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        int SPLASH_TIME_OUT = 5000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                //finish(); // da nebi mogao da ode back na splash
            }
        }, SPLASH_TIME_OUT);
    }

}
