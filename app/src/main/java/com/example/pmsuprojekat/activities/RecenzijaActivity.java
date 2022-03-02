package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.pmsuprojekat.R;
import model.Porudzbina;

public class RecenzijaActivity extends AppCompatActivity {

    EditText editTextKomentar, editTextOcena;
    DBHelper dbHelper;
    Button submitClick;
    CheckBox checkBoxAnonimno;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recenzija);

        editTextKomentar = (EditText) findViewById(R.id.editTextKomentar);
        editTextOcena = (EditText) findViewById(R.id.editTextOcena);
        checkBoxAnonimno = (CheckBox) findViewById(R.id.checkBoxAnonimno);

        submitClick = (Button) findViewById(R.id.btnSubmit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        dbHelper = new DBHelper(this);

        submitClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                // startActivity(intent);
                String komentar = editTextKomentar.getText().toString();
                Integer ocena = Integer.valueOf(editTextOcena.getText().toString());
                boolean anonimanKomentar;
                if(checkBoxAnonimno.isChecked()){ anonimanKomentar = true; }
                else { anonimanKomentar = false; }

                Intent intent = getIntent();
                int idPorudzbine = intent.getIntExtra("idPorudzbine",0);
                int id_kupca = intent.getIntExtra("idKupca",0);
                if(ocena.equals("")||komentar.equals("")) {
                    Toast.makeText(RecenzijaActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                }else {
                    dbHelper.updatePorudzbinu(new Porudzbina(idPorudzbine, ocena, komentar, anonimanKomentar));
                }
                Intent intent2 = new Intent(RecenzijaActivity.this, PorudzbinaActivity.class);
                intent2.putExtra("idKupca", id_kupca);

                startActivity(intent2);

            }
        });

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
        if(id == R.id.logout){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return true;
    }

}
