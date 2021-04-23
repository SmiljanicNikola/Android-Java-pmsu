package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.tools.Mokap;

import java.util.List;

public class KorisniciActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Button btnPoj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korisnici_list);


        btnPoj = findViewById(R.id.btnPojedinacniKorisnik1);

        btnPoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KorisniciActivity.this, KorisnikDetailActivity.class);
                startActivity(intent);
            }
        });


        btnPoj = findViewById(R.id.btnPojedinacniKorisnik2);

        btnPoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KorisniciActivity.this, KorisnikDetailActivity.class);
                startActivity(intent);
            }
        });

        btnPoj = findViewById(R.id.btnPojedinacniKorisnik3);

        btnPoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KorisniciActivity.this, KorisnikDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
