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

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescr = findViewById(R.id.tvDescr);

        tvName.setText(getIntent().getStringExtra("name"));
        tvDescr.setText(getIntent().getStringExtra("descr"));
    }

}
