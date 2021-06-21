package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.RegisterActivity;


import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {
    Button back;
    TextView register;
    TextView login;
    TextView registerAsSalesman;
    EditText username, password;
    DBHelper DB;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /*int SPLASH_TIME_OUT = 5000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish(); // da nebi mogao da ode back na splash
            }
        }, SPLASH_TIME_OUT);*/

        /*back = findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        if(sharedPreferenceConfig.read_login_status()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        login = findViewById(R.id.textView1);
        register = findViewById(R.id.textView2);
        registerAsSalesman = findViewById(R.id.textView3);

        DB=new DBHelper(this);



        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        registerAsSalesman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivityProdavac.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Popunite sva polja!", Toast.LENGTH_SHORT).show();

                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Uspesno ste se ulogovali", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        sharedPreferenceConfig.login_status(true);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Unesite prave kredencijale!", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });



    }
}
