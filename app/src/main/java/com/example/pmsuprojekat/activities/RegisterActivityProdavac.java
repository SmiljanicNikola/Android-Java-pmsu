package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pmsuprojekat.R;
import java.time.LocalDate;
import java.util.Calendar;

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
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        registerClick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
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
                            Boolean insert2 = DB.insertProdavci(ime, prezime, user, pass, LocalDate.parse(getTodaysDate()), email, adresa, naziv);

                            if (insert == true) {
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

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);

    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + getDayFormat(day);
    }

    private String getMonthFormat(int month) {

        if(month==1){
            return "0";
        }
        if(month==2){
            return "01";
        }
        if(month==3){
            return "02";
        }
        if(month==4){
            return "03";
        }
        if(month==5){
            return "04";
        }
        if(month==6){
            return "06";
        }
        if(month==7){
            return "07";
        }
        if(month==8){
            return "08";
        }
        if(month==9){
            return "09";
        }
        if(month==10){
            return "10";
        }
        if(month==11){
            return "11";
        }
        if(month==12){
            return "11";
        }
        return "8";
    }


    private String getDayFormat(int day) {

        if(day==1){
            return "01";
        }
        if(day==2){
            return "02";
        }
        if(day==3){
            return "03";
        }
        if(day==4){
            return "04";
        }
        if(day==5){
            return "05";
        }
        if(day==6){
            return "06";
        }
        if(day==7){
            return "07";
        }
        if(day==8){
            return "08";
        }
        if(day==9){
            return "09";
        }
        if(day==10){
            return "10";
        }
        if(day==11){
            return "11";
        }
        if(day==12){
            return "12";
        }
        if(day==13){
            return "13";
        }
        if(day==14){
            return "14";
        }
        if(day==15){
            return "15";
        }
        if(day==16){
            return "16";
        }
        if(day==17){
            return "17";
        }
        if(day==18){
            return "18";
        }
        if(day==18){
            return "18";
        }
        if(day==19){
            return "19";
        }
        if(day==20){
            return "20";
        }
        if(day==21){
            return "21";
        }
        if(day==22){
            return "22";
        }
        if(day==23){
            return "23";
        }
        if(day==24){
            return "24";
        }
        if(day==25){
            return "25";
        }
        if(day==26){
            return "26";
        }
        if(day==27){
            return "27";
        }
        if(day==28){
            return "28";
        }
        if(day==29){
            return "29";
        }
        if(day==30){
            return "30";
        }
        if(day==31){
            return "31";
        }
        return "3";

    }

}
