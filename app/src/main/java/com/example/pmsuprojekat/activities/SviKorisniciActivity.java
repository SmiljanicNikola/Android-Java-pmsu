package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivityAdministrator;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.KorisnikAdapterClass;

import java.util.List;

import model.Artikal;
import model.Korisnik;

public class SviKorisniciActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svi_korisnici_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<Korisnik> korisnici = dbHelper.getKorisnike();

        if(korisnici.size() > 0){
            KorisnikAdapterClass korisnikAdapterClass = new KorisnikAdapterClass(korisnici,SviKorisniciActivity.this);
            recyclerView.setAdapter(korisnikAdapterClass);
        }else{
            Toast.makeText(SviKorisniciActivity.this, "Nema korisnika u bazi podataka!", Toast.LENGTH_SHORT).show();

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
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
            Intent intent = new Intent(SviKorisniciActivity.this, MainActivityAdministrator.class);
            startActivity(intent);
            finish();
        }
        if(id == R.id.logout){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return true;
    }

}
