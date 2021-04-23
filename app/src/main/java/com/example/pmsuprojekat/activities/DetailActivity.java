package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView Ime = findViewById(R.id.ime);
        TextView prezime = findViewById(R.id.prezime);
        TextView Username = findViewById(R.id.username);

        Ime.setText(getIntent().getStringExtra("ime"));
        prezime.setText(getIntent().getStringExtra("prezime"));
        Username.setText(getIntent().getStringExtra("username"));


        spinner = findViewById(R.id.aSpinnerToolBarKorisnik);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Izaberi");
        categories.add("Artikli");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Izaberi")) {
                    //Nista
                } else {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();


                    if (parent.getItemAtPosition(position).equals("Artikli")) {
                        Intent intent = new Intent(DetailActivity.this, ArtikalActivity.class);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }
}
