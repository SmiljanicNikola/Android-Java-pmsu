package com.example.pmsuprojekat.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pmsuprojekat.R;

import org.w3c.dom.Text;

public class KorisnikDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_korisnik);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView ime = findViewById(R.id.ime);
       /* TextView medium1 = findViewById(R.id.textView);
        TextView medium2 = findViewById(R.id.textView1);
        TextView medium3 = findViewById(R.id.textView3);*/
        TextView prezime = findViewById(R.id.prezime);
        TextView username = findViewById(R.id.userName);



        /*medium1.setText(getIntent().getStringExtra("Medium1"));
        medium2.setText(getIntent().getStringExtra("Medium2"));
        medium3.setText(getIntent().getStringExtra("Medium3"));*/
        ime.setText(getIntent().getStringExtra("ime"));
        prezime.setText(getIntent().getStringExtra("prezime"));
        username.setText(getIntent().getStringExtra("username"));
    }

}
