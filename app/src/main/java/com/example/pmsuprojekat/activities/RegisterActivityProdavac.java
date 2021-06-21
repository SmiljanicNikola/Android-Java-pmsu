package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import java.time.LocalDate;

public class RegisterActivityProdavac extends AppCompatActivity {

    TextView registerClick, loginClick;
    EditText name, lastname, username, password, repassword, adress,eemail,nnaziv;
    DBHelper DB;
    DBHelperKupci DBKupci;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_prodavac);
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        adress = (EditText) findViewById(R.id.adress);
        eemail = (EditText) findViewById(R.id.email);
        nnaziv = (EditText) findViewById(R.id.naziv);


        repassword = (EditText) findViewById(R.id.repassword);
        registerClick = (TextView) findViewById(R.id.textView1);
        loginClick = (TextView) findViewById(R.id.textView2);

        DB = new DBHelper(this);

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
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                String email = eemail.getText().toString();
                String naziv = nnaziv.getText().toString();
                String uloga = "prodavac";

                if(ime.equals("")||prezime.equals("")||user.equals("")||pass.equals("")||repass.equals("")||email.equals("")||adresa.equals("")||naziv.equals(""))
                    Toast.makeText(RegisterActivityProdavac.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(ime, prezime, user, pass, uloga,false);
                            Boolean insert2 = DB.insertProdavci(ime, prezime, user, pass, LocalDate.parse("2021-04-04"), email, adresa, naziv);

                            if (insert == true) {
                                //DBKupci.insertKupci(ime, prezime, user, pass, adresa);
                                Toast.makeText(RegisterActivityProdavac.this, "Uspesno ste se registrovali", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivityProdavac.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegisterActivityProdavac.this, "Korisnik vec postoji!", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(RegisterActivityProdavac.this, "Lozinke se ne podudaraju", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}
