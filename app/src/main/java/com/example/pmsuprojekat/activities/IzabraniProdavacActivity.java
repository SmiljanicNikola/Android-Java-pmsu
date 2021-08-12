package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass2;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.izabrani_prodavac);




        spinner = findViewById(R.id.aSpinnerToolBar);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Izaberi");
        categories.add("Artikli");
        categories.add("Dodaj artikal");
        categories.add("Svi korisnici");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Izaberi")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    if(parent.getItemAtPosition(position).equals("Artikli"))
                    {
                        Intent intent = new Intent(IzabraniProdavacActivity.this, ArtikalActivity.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Dodaj artikal"))
                    {
                        Intent intent = new Intent(IzabraniProdavacActivity.this, NoviArtikalActivity.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Svi korisnici"))
                    {
                        Intent intent = new Intent(IzabraniProdavacActivity.this, SviKorisniciActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);


        Intent intent = getIntent();
        //String idKupca = String.valueOf(intent.getIntExtra("idKupca",0));

        String username = intent.getStringExtra("user");
        int id = intent.getIntExtra("id",0);
        int idKupca = intent.getIntExtra("idKupca",0);
        textView_idKupca = findViewById(R.id.text_idKupca);

        String prodavacId = String.valueOf(intent.getIntExtra("id",0));
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
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_check);

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
            Toast.makeText(getApplicationContext(), "You clicked share",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


}
