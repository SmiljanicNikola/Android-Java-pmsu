package com.example.pmsuprojekat.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import model.Akcija;
import model.Artikal;
import model.Prodavac;

public class NovaAkcijaActivity extends AppCompatActivity {

    EditText txtProcenat, txtOdKad, txtDoKad, txtTekst, txtArtikalId;
    TextView textViewDodaj, textViewPregledaj;
    DBHelper DB;
    Spinner spinner;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private Button dateButton;
    private TextView dateTextView, dateTextView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_akciju);



        txtProcenat = findViewById(R.id.txtProcenat);
        //txtOdKad = findViewById(R.id.);
        ////txtDoKad = findViewById(R.id.txtDoKad);
        txtTekst = findViewById(R.id.txtTekst);

        textViewDodaj = findViewById(R.id.textViewDodaj);

        initDatePicker();
        initDatePicker1();
        //dateButton = findViewById(R.id.datePickerButton);
        dateTextView = findViewById(R.id.datePickerButton);
        dateTextView1 = findViewById(R.id.datePickerButton1);

        //dateButton.setText(getTodaysDate());
        dateTextView1.setText(getTodaysDate());
        dateTextView.setText(getTodaysDate());
        DB = new DBHelper(this);
        Intent intent = getIntent();
        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        int prodavacId = prefs.getInt("idProdavca", 0);
        Integer prodavacid = intent.getIntExtra("id",0);

        spinner = findViewById(R.id.spinner);
        List<Artikal> artikliList = DB.getArtikliProdavca(String.valueOf(prodavacid));
        ArrayAdapter<Artikal> adapter = new ArrayAdapter<Artikal>(this, android.R.layout.simple_spinner_item, artikliList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Artikal artikal = (Artikal) parent.getSelectedItem();
                displayArtikalData(artikal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textViewDodaj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Integer procenat = Integer.valueOf(txtProcenat.getText().toString());
                String tekst = txtTekst.getText().toString();
                //String odKad = dateButton.getText().toString();
                /*SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-DD-YYYY");
                Date dutyDay = (Date) dateButton.getText();
                try {
                    dutyDay = (Date) simpleDateFormat.parse(odKad);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                LocalDate odKad = LocalDate.parse(dateTextView.getText().toString());
                LocalDate doKad = LocalDate.parse(dateTextView1.getText().toString());
                Integer prodavac_id = prodavacid;
                //Integer artikal_id = Integer.valueOf(txtArtikalId.getText().toString());
                Integer artikal_id = Integer.valueOf(spinner.getSelectedItem().toString());

                Artikal artikal = DB.findArtikal(artikal_id);
                // LocalDate odKad = LocalDate.parse(getTodaysDate());

                Akcija akcija = null;
                if (procenat.equals("") || tekst.equals("") || doKad.equals("")) {
                    Toast.makeText(NovaAkcijaActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper DB = new DBHelper(NovaAkcijaActivity.this);
                    akcija = new Akcija(procenat, odKad, doKad, tekst, prodavac_id, artikal_id);
                    DB.insertAkcija(akcija);



                    /*int akcijaId = akcija.getId();
                    DB.updateArtikal(new Artikal(artikal.getId(),akcijaId));*/

                    Toast.makeText(NovaAkcijaActivity.this, "Uspesno ste dodali akciju", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }

                /*Artikal artikal1 = DB.findArtikal(artikal_id);
                List<Akcija> akcije = artikal1.getAkcije();
                akcije.add(akcija);*/

                Intent intent = new Intent(NovaAkcijaActivity.this, AkcijeActivity.class);
                Intent intent1 = getIntent();
                //String username = intent1.getStringExtra("user");
                Integer prodavacid = intent1.getIntExtra("id", 0);
                //Korisnik korisnik = DB.findKorisnik(username);
                //Prodavac prodavac = DB.findProdavac(username);
                //int idProdavca = prodavac.getId();
                intent.putExtra("idProdavca", prodavacid);
                //intent.putExtra("user", username);
                startActivity(intent);
            }
        });



    }

    public void getSelectedArtikal(View v){
        Artikal artikal = (Artikal) spinner.getSelectedItem();
        displayArtikalData(artikal);
    }

    private void displayArtikalData(Artikal artikal){
        String naziv = artikal.getNaziv();
        String opis = artikal.getOpis();
        Double cena = artikal.getCena();

        String artikalData = "Naziv: "+naziv+"\nOpis: "+opis+"\nCena: "+cena;

        Toast.makeText(this, artikalData, Toast.LENGTH_LONG).show();
    }

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month,year);
                dateTextView.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year,month,day);
    }

    private void initDatePicker1() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month,year);
                dateTextView1.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year,month,day);
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

    public void openDatePicker(View view){
        datePickerDialog.show();
    }
    public void openDatePicker1(View view){
        datePickerDialog1.show();
    }

}
