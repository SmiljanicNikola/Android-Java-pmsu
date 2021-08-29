package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;

import java.util.ArrayList;
import java.util.List;

import model.Akcija;
import model.Artikal;
import model.Stavka;

public class PotvrdaPorudzbineActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private Spinner spinner;
    private TextView textViewNaziv,textViewKolicina,textViewCenaPojedinacno,textViewCenaUkupno,textViewCenaSaAkcijom;
    private Button btnPotvrdiPorudzbinu;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potvrda_porudzbine);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        int kupacId = intent.getIntExtra("idKupca",0);
        //int stavkaId = intent.getIntExtra("stavkaId",0);
        //String stavkaId = String.valueOf(intent.getIntExtra("stavkaId",0));
        //String artikalId = String.valueOf(intent.getIntExtra("artikalId",0));

        int stavkaId = intent.getIntExtra("stavkaId",0);
        int kolicina = intent.getIntExtra("kolicina",0);
        int artikal_id = intent.getIntExtra("artikalId",0);

        Artikal artikal = dbHelper.findArtikal(artikal_id);
        Double cenaArtikla = artikal.getCena();
        String naziv = artikal.getNaziv();

        /*Stavka stavka = dbHelper.findStavka(stavkaOznaka);
        int kolicina2 = stavka.getKolicina();*/
        //MY_CLASS class = (MY_CLASS) intent.getExtras().getSerializable("KEY");
        spinner = findViewById(R.id.aSpinnerToolBar);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Izaberi");
        categories.add("Artikli");
        categories.add("Dodaj artikal");
        categories.add("Svi korisnici");
        categories.add(String.valueOf(stavkaId));
        categories.add(String.valueOf(kolicina));
        categories.add(String.valueOf(artikal_id));



        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        textViewNaziv = findViewById(R.id.textView1);
        textViewNaziv.setText(naziv);

        textViewKolicina = findViewById(R.id.textView2);
        textViewKolicina.setText(String.valueOf(kolicina));

        textViewCenaPojedinacno = findViewById(R.id.textView3);
        textViewCenaPojedinacno.setText(String.valueOf(cenaArtikla));

        /*List<Akcija> akcije = artikal.getAkcije();
        for(Akcija akcija : akcije){
            textViewCenaSaAkcijom = findViewById(R.id.textView5);
            textViewCenaSaAkcijom.setText(String.valueOf(cenaArtikla*akcija.getProcenat()));
        }*/
        List<Akcija> akcije = dbHelper.getAkcije();
        for(Akcija akcija : akcije) {
            if (akcija.getArtikal_id() == artikal.getId()) {
                textViewCenaSaAkcijom = findViewById(R.id.textView5);
                double stotiDeo = cenaArtikla/100;
                textViewCenaSaAkcijom.setText(String.valueOf((cenaArtikla-(stotiDeo*akcija.getProcenat()))*kolicina));
            }
            else{
                textViewCenaSaAkcijom = findViewById(R.id.textView5);
                textViewCenaSaAkcijom.setText("Trenutno nema aktivne akcije");
            }
        }

        textViewCenaUkupno = findViewById(R.id.textView4);
        textViewCenaUkupno.setText(String.valueOf(cenaArtikla * kolicina));

        btnPotvrdiPorudzbinu = findViewById(R.id.btnPotvrdiPorudzbinu);
        btnPotvrdiPorudzbinu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivityKupac.class);


                v.getContext().startActivity(myIntent);

            }
            });

        /*dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Izaberi")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    if (parent.getItemAtPosition(position).equals("Artikli")) {
                        Intent intent = new Intent(PotvrdaPorudzbineActivity.this, ArtikalActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Dodaj artikal")) {
                        Intent intent = new Intent(PotvrdaPorudzbineActivity.this, NoviArtikalActivity.class);
                        startActivity(intent);
                    }
                    if (parent.getItemAtPosition(position).equals("Svi korisnici")) {
                        Intent intent = new Intent(PotvrdaPorudzbineActivity.this, SviKorisniciActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

        @SuppressLint("ResourceType")
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.layout.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();

            if(id == R.id.share){
                Toast.makeText(getApplicationContext(), "You clicked share",
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        }

    }

