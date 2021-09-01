package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.AnonimanKomentarAdapterClass;
import com.example.pmsuprojekat.adapters.KomentarAdapterClass;

import java.util.ArrayList;
import java.util.List;

import model.Artikal;
import model.Porudzbina;
import model.Stavka;

public class AnonimniKomentariActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anonimni_komentari_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);

        //SHAREDRPEFERENCES NACIN ------------------------
        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        int idProdavca = prefs.getInt("idProdavca", 0);
        String idProdavcaa = String.valueOf(idProdavca);//SHARED NACIN BREEEE
        String usernameProdavca = prefs.getString("usernameProdavca", "No name defined");

        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));

        List<Artikal> artikli = dbHelper.getArtikliProdavca(prodavacId);
        List<Porudzbina> porudzbine = dbHelper.getPorudzbineNearhiviraniAnonimni();
        List<Stavka> stavke = dbHelper.getStavke();
        List<Porudzbina> porudzbineProdavca = new ArrayList<>();
        for(Artikal artikal : artikli){
            for(Porudzbina porudzbina : porudzbine){
                for(Stavka stavka : stavke){
                    if(artikal.getId() == stavka.getArtikal_id() && stavka.getId() == porudzbina.getStavka_id()){
                        porudzbineProdavca.add(porudzbina);
                    }
                }

            }
        }


        //Dobavljanje komentara pojedinacnog prodavca

        if(porudzbineProdavca.size() > 0){
            AnonimanKomentarAdapterClass anonimniKomentarAdapterClass = new AnonimanKomentarAdapterClass(porudzbineProdavca,AnonimniKomentariActivity.this);
            recyclerView.setAdapter(anonimniKomentarAdapterClass);
        }else{
            Toast.makeText(AnonimniKomentariActivity.this, "Nema anonimnih komentara za ovog prodavca u bazi podataka!", Toast.LENGTH_SHORT).show();

        }

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
            Intent intent = new Intent(AnonimniKomentariActivity.this, MainActivity.class);
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
