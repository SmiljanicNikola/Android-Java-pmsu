package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;

import model.Artikal;
import model.Prodavac;

public class NoviArtikalActivity extends AppCompatActivity {

    EditText txtNaziv, txtOpis, txtCena, txtPutanja;
    TextView textViewDodaj, textViewPregledaj;
    DBHelper DB;

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
        /*Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);*/
        Intent intent = getIntent();
       // String username = intent.getStringExtra("user");
        //String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));
        Integer prodavacid = intent.getIntExtra("id",0);

        textViewDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naziv = txtNaziv.getText().toString();
                String opis = txtOpis.getText().toString();
                Double cena = Double.valueOf(txtCena.getText().toString());
                String putanja = txtPutanja.getText().toString();
                Integer prodavac_id = prodavacid;


                if (naziv.equals("") || opis.equals("") || cena.equals("") || putanja.equals("")) {
                    Toast.makeText(NoviArtikalActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper DB = new DBHelper(NoviArtikalActivity.this);
                    Artikal artikal = new Artikal(naziv,opis,cena,putanja,prodavac_id);
                    DB.insertArtikal(artikal);
                    Toast.makeText(NoviArtikalActivity.this, "Uspesno ste dodali artikal", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        textViewPregledaj.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(NoviArtikalActivity.this, ArtikalActivity.class);
            startActivity(intent);
                /*Intent intent = new Intent(NoviArtikalActivity.this, ArtikalActivity.class);
                Intent intent1 = getIntent();
                String username = intent1.getStringExtra("user");
                //Korisnik korisnik = DB.findKorisnik(username);
                Prodavac prodavac = DB.findProdavac(username);
                int idProdavca = prodavac.getId();
                intent.putExtra("idProdavca", idProdavca);
                intent.putExtra("user", username);
                startActivity(intent);*/
            }
        });


    }
}