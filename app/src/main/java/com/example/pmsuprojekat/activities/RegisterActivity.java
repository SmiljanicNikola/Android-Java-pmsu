package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView registerClick, loginClick;
    EditText name, lastname, username, password, repassword, adress;
    DBHelper DB;
    DBHelperKupci DBKupci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
       /* Spinner aSpinner = findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this); */


        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        adress = (EditText) findViewById(R.id.adress);

        repassword = (EditText) findViewById(R.id.repassword);
        registerClick = (TextView) findViewById(R.id.textView1);
        loginClick = (TextView) findViewById(R.id.textView2);

        DB = new DBHelper(this);
        DBKupci = new DBHelperKupci(this);

        loginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
               // startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                // startActivity(intent);
                String ime = name.getText().toString();
                String prezime = lastname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String adresa = adress.getText().toString();

                if(ime.equals("")||prezime.equals("")||user.equals("")||pass.equals("")||repass.equals("")||adresa.equals(""))
                    Toast.makeText(RegisterActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(ime, prezime, user, pass);
                            Boolean insert2 = DB.insertKupci(ime, prezime, user, pass, adresa);

                            if (insert == true) {
                                //DBKupci.insertKupci(ime, prezime, user, pass, adresa);
                                Toast.makeText(RegisterActivity.this, "Uspesno ste se registrovali", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Korisnik vec postoji!", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Lozinke se ne podudaraju", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }


    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int position, long id){
        //Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }

}
