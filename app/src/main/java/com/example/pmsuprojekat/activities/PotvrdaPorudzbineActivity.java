package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pmsuprojekat.R;

import java.util.ArrayList;
import java.util.List;

import model.Stavka;

public class PotvrdaPorudzbineActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private SharedPreferenceConfig sharedPreferenceConfig;
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potvrda_porudzbine);

        Intent intent = getIntent();
        int kupacId = intent.getIntExtra("idKupca",0);
        //int stavkaId = intent.getIntExtra("stavkaId",0);
        //String stavkaId = String.valueOf(intent.getIntExtra("stavkaId",0));
        int stavkaId = intent.getIntExtra("stavkaId",0);
        int kolicina = intent.getIntExtra("kolicina",0);
        int artikal_id = intent.getIntExtra("artikalId",0);

        //String prodavacId = String.valueOf(intent.getIntExtra("id",0));

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


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_check);

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

