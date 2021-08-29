package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.AkcijaAdapterClass;
import java.util.List;
import model.Akcija;
import model.Prodavac;

public class AkcijeActivity extends AppCompatActivity {

    private SensorManager sensor;
    private float acelVal;
    private float acelLast;
    private float shake;

    Button dodajAkciju;
    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akcije_list);
        dodajAkciju=findViewById(R.id.btnDodajAkciju);

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
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        dodajAkciju.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent1 = getIntent();
                String username = intent1.getStringExtra("user");
                //String idProdavca = String.valueOf(intent1.getIntExtra("idProdavca",0));
                int idProdavca = intent1.getIntExtra("idProdavca",0);
                Intent intent = new Intent(AkcijeActivity.this, NovaAkcijaActivity.class);
                //Korisnik korisnik = DB.findKorisnik(username);

                /*Prodavac prodavac = dbHelper.findProdavac(username);
                int idProdavca = prodavac.getId();*/

                intent.putExtra("id", idProdavca);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        });

        sensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor.registerListener(sensorListener, sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    private final SensorEventListener sensorListener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent sensorEvent){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if(shake>12){
                Toast toast = Toast.makeText(getApplicationContext(), "NE MUCKAJ!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent1 = getIntent();
                String username = intent1.getStringExtra("user");
                //String idProdavca = String.valueOf(intent1.getIntExtra("idProdavca",0));
                int idProdavca = intent1.getIntExtra("idProdavca",0);
                Intent intent = new Intent(AkcijeActivity.this, NovaAkcijaActivity.class);
                //Korisnik korisnik = DB.findKorisnik(username);

                /*Prodavac prodavac = dbHelper.findProdavac(username);
                int idProdavca = prodavac.getId();*/

                intent.putExtra("id", idProdavca);
                intent.putExtra("user", username);
                startActivity(intent);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

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


}
