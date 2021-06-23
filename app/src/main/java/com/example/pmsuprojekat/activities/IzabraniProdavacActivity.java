package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass2;

import java.util.List;

import model.Artikal;

public class IzabraniProdavacActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.izabrani_prodavac);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);


        Intent intent = getIntent();

        //String idKupca = String.valueOf(intent.getIntExtra("idKupca",0));

        String username = intent.getStringExtra("user");
        int id = intent.getIntExtra("id",0);

        String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));
        //Dobavljanje artikala pojedinacnog prodavca
        List<Artikal> artikli = dbHelper.getArtikliProdavca(prodavacId);
        if(artikli.size() > 0){
            ArtikalAdapterClass2 artikalAdapterClass = new ArtikalAdapterClass2(artikli,IzabraniProdavacActivity.this);
            recyclerView.setAdapter(artikalAdapterClass);

        }else{
            Toast.makeText(IzabraniProdavacActivity.this, "Nema artikala u bazi podataka!", Toast.LENGTH_SHORT).show();

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
}
