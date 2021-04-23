package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends Activity implements AdapterView.OnItemSelectedListener{
    TextView registerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Spinner aSpinner = findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this);


        /*int SPLASH_TIME_OUT = 20000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                //finish(); // da nebi mogao da ode back na splash
            }
        }, SPLASH_TIME_OUT);*/




        registerClick = findViewById(R.id.textView1);

        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int position, long id){
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

}
