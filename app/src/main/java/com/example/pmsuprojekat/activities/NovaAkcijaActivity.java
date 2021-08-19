package com.example.pmsuprojekat.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import model.Akcija;
import model.Artikal;

public class NovaAkcijaActivity extends AppCompatActivity {

    EditText txtProcenat, txtOdKad, txtDoKad, txtTekst, txtArtikalId;
    TextView textViewDodaj, textViewPregledaj;
    DBHelper DB;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodaj_akciju);

        txtProcenat = findViewById(R.id.txtProcenat);
        //txtOdKad = findViewById(R.id.);
        txtDoKad = findViewById(R.id.txtDoKad);
        txtTekst = findViewById(R.id.txtTekst);
        txtArtikalId = findViewById(R.id.txtArtikalId);

        textViewDodaj = findViewById(R.id.textViewDodaj);

        initDatePicker();
        //dateButton = findViewById(R.id.datePickerButton);
        dateTextView = findViewById(R.id.datePickerButton);

        //dateButton.setText(getTodaysDate());
        dateTextView.setText(getTodaysDate());
        DB = new DBHelper(this);
        Intent intent = getIntent();
        Integer prodavacid = intent.getIntExtra("id",0);

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
                LocalDate doKad = LocalDate.parse(txtDoKad.getText().toString());
                Integer prodavac_id = prodavacid;
                Integer artikal_id = Integer.valueOf(txtArtikalId.getText().toString());
                Artikal artikal = DB.findArtikal(artikal_id);
                // LocalDate odKad = LocalDate.parse(getTodaysDate());

                if (procenat.equals("") || tekst.equals("") || doKad.equals("")) {
                    Toast.makeText(NovaAkcijaActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper DB = new DBHelper(NovaAkcijaActivity.this);
                    Akcija akcija = new Akcija(procenat, odKad,doKad,tekst,prodavac_id, artikal_id);
                    DB.insertAkcija(akcija);
                    Toast.makeText(NovaAkcijaActivity.this, "Uspesno ste dodali akciju", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
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

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + day;
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

    public void openDatePicker(View view){
        datePickerDialog.show();
    }


}
