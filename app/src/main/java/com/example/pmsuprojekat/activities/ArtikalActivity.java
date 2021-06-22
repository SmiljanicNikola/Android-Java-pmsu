package com.example.pmsuprojekat.activities;

import android.content.Intent;
import android.content.res.Configuration;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.ArtikalAdapterClass;
/*import com.example.pmsuprojekat.fragments.MyFragment;
import com.example.pmsuprojekat.fragments.MyFragmentArtikal;*/
import com.example.pmsuprojekat.tools.FragmentTransition;
import com.example.pmsuprojekat.tools.FragmentTransitionArtikli;

import java.util.ArrayList;
import java.util.List;

import model.Artikal;
import model.NavItem;

public class ArtikalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikli_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<Artikal> artikli = dbHelper.getArtikli();

        if(artikli.size() > 0){
            ArtikalAdapterClass artikalAdapterClass = new ArtikalAdapterClass(artikli,ArtikalActivity.this);
            recyclerView.setAdapter(artikalAdapterClass);
        }else{
            Toast.makeText(ArtikalActivity.this, "Nema artikala u bazi podataka!", Toast.LENGTH_SHORT).show();

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

