package com.example.pmsuprojekat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmsuprojekat.R;

public class NoviArtikalActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikl_forma);


        textView = findViewById(R.id.textView1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoviArtikalActivity.this, ArtikalActivity.class);
                startActivity(intent);
            }
        });


    }
}