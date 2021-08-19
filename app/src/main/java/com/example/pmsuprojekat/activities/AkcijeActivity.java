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
import com.example.pmsuprojekat.adapters.AkcijaAdapterClass;
import java.util.List;
import model.Akcija;

public class AkcijeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikli_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));

        //Dobavljanje akcija pojedinacnog prodavca
        List<Akcija> akcije = dbHelper.getAkcijeProdavca(prodavacId);

        if(akcije.size() > 0){
            AkcijaAdapterClass akcijaAdapterClass = new AkcijaAdapterClass(akcije,AkcijeActivity.this);
            recyclerView.setAdapter(akcijaAdapterClass);
        }else{
            Toast.makeText(AkcijeActivity.this, "Nema akcija u bazi podataka!", Toast.LENGTH_SHORT).show();

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
