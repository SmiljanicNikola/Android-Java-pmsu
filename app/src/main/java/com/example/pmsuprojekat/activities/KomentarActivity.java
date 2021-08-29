package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
/*import com.example.pmsuprojekat.fragments.MyFragmentArtikal;
import com.example.pmsuprojekat.fragments.MyFragmentKomentar;*/
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.KomentarAdapterClass;
import com.example.pmsuprojekat.tools.FragmentTransitionArtikli;
import com.example.pmsuprojekat.tools.FragmentTransitionKomentari;

import java.util.ArrayList;
import java.util.List;

import model.Artikal;
import model.Komentar;
import model.NavItem;
import model.Porudzbina;
import model.Stavka;

public class KomentarActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.komentari_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("user");

        String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));

        List<Artikal> artikli = dbHelper.getArtikliProdavca(prodavacId);
        List<Porudzbina> porudzbine = dbHelper.getPorudzbineNearhivirani();
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
            KomentarAdapterClass komentarAdapterClass = new KomentarAdapterClass(porudzbineProdavca,KomentarActivity.this);
            recyclerView.setAdapter(komentarAdapterClass);
        }else{
            Toast.makeText(KomentarActivity.this, "Nema komentara za ovog prodavca u bazi podataka!", Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(KomentarActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

}

