package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.PorudzbinaAdapterClass;

import java.util.List;

import model.Artikal;
import model.Porudzbina;

public class PorudzbinaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porudzbine_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("user");

        String kupacId = String.valueOf(intent.getIntExtra("idKupca",0));
        //Dobavljanje pourdzbina pojedinacnog kupca koji ih je obavio
        List<Porudzbina> porudzbine = dbHelper.getPorudzbineKupca(kupacId);


        if(porudzbine.size() > 0){
            PorudzbinaAdapterClass porudzbinaAdapterClass = new PorudzbinaAdapterClass(porudzbine,PorudzbinaActivity.this);
            recyclerView.setAdapter(porudzbinaAdapterClass);
        }else{
            Toast.makeText(PorudzbinaActivity.this, "Nema porudzbina od ovog kupca u bazi podataka!", Toast.LENGTH_SHORT).show();

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            /*actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);*/
            actionBar.setHomeButtonEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

}
