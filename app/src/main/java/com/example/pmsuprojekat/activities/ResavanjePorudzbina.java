package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.pmsuprojekat.MainActivityAdministrator;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.SvePorudzbineAdapterClass;
import java.util.List;
import model.Porudzbina;

public class ResavanjePorudzbina extends AppCompatActivity {

    RecyclerView recyclerView;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resavanje_porudzbina_list);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);

        //Dobavljanje svih porudzbina
        List<Porudzbina> porudzbine = dbHelper.getPorudzbine();

        if(porudzbine.size() > 0){
            SvePorudzbineAdapterClass svePorudzbineAdapterClass = new SvePorudzbineAdapterClass(porudzbine,ResavanjePorudzbina.this);
            recyclerView.setAdapter(svePorudzbineAdapterClass);
        }else{
            Toast.makeText(ResavanjePorudzbina.this, "Nema prispelih neresenih porudzbina!", Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(ResavanjePorudzbina.this, MainActivityAdministrator.class);
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
