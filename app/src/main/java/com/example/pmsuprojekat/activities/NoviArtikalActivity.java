package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.R;

public class NoviArtikalActivity extends AppCompatActivity {

    EditText txtNaziv, txtOpis, txtCena, txtPutanja;
    TextView textViewDodaj, textViewPregledaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikl_forma);

        txtNaziv = findViewById(R.id.txtNaziv);
        txtOpis = findViewById(R.id.txtOpis);
        txtCena = findViewById(R.id.txtCena);
        txtPutanja = findViewById(R.id.txtPutanja);

        textViewDodaj = findViewById(R.id.textViewDodaj);
        textViewPregledaj = findViewById(R.id.textViewPregledaj);


        textViewDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naziv = txtNaziv.getText().toString();
                String opis = txtOpis.getText().toString();
                String cena = txtCena.getText().toString();
                String putanja = txtPutanja.getText().toString();

                if (naziv.equals("") || opis.equals("") || cena.equals("") || putanja.equals("")) {
                    Toast.makeText(NoviArtikalActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });


    }
}