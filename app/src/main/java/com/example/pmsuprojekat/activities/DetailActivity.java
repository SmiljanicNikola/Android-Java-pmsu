package com.example.pmsuprojekat.activities;

import android.os.Bundle;
import android.widget.TextView;
import com.example.pmsuprojekat.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView Ime = findViewById(R.id.tvIme);
        TextView prezime = findViewById(R.id.tvPrezime);
        TextView Username = findViewById(R.id.tvUsername);

        Ime.setText(getIntent().getStringExtra("Ime"));
        prezime.setText(getIntent().getStringExtra("Prezime"));
        Username.setText(getIntent().getStringExtra("Username"));
    }

}
