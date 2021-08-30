package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass2;

import java.util.ArrayList;
import java.util.List;

import model.Akcija;
import model.Artikal;

public class IzabraniProdavacActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView textView_idKupca;

    private Spinner spinner;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHelper DB;
    /*SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString("userName", userName);
    editor.commit();*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.izabrani_prodavac);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        int id = intent.getIntExtra("id",0);
        int idKupca = intent.getIntExtra("idKupca",0);

        /*SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        String usernameKupca = prefs.getString("usernameKupca", "No name defined");
        int idKupca = prefs.getInt("idKupca", 0);
        int idProdavca = prefs.getInt("idProdavca", 0);
        String username = prefs.getString("username", "No name defined");*/

        textView_idKupca = findViewById(R.id.text_idKupca);


        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        String usernameKupca = prefs.getString("usernameKupca", "No name defined");
        //int idKupca = prefs.getInt("idKupca", 0);
        /*int idProdavca = prefs.getInt("id", 0);
        String prodavacId = String.valueOf(idProdavca);*/
        String prodavacId = String.valueOf(intent.getIntExtra("id",0));

        //Dobavljanje artikala pojedinacnog prodavca
        List<Artikal> artikli = dbHelper.getArtikliProdavca(prodavacId);
        List<Akcija> akcije= dbHelper.getAkcijeProdavca(prodavacId);

        if(artikli.size() > 0){
            ArtikalAdapterClass2 artikalAdapterClass = new ArtikalAdapterClass2(artikli, akcije,IzabraniProdavacActivity.this);
            recyclerView.setAdapter(artikalAdapterClass);

        }else{
            Toast.makeText(IzabraniProdavacActivity.this, "Nema artikala u bazi podataka!", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(IzabraniProdavacActivity.this, MainActivityKupac.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


}
