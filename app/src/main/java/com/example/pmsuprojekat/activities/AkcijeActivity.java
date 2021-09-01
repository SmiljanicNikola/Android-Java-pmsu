package com.example.pmsuprojekat.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.adapters.AkcijaAdapterClass;

import java.time.LocalDate;
import java.util.Calendar;
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
        dodajAkciju=findViewById(R.id.btn_dodajAkciju);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        //String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));

        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        int prodavacId = prefs.getInt("idProdavca", 0);
        String usernameProdavca = prefs.getString("usernameProdavca", "No name defined");
        String prodavacIdd = String.valueOf(prodavacId);

        //Dobavljanje akcija pojedinacnog prodavca
        List<Akcija> akcije = dbHelper.getAkcijeProdavca(prodavacIdd);
        for(Akcija akcija : akcije){
            if(LocalDate.parse(getTodaysDate()).isAfter(akcija.getDoKad())){
                akcije.remove(akcija);
            }
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.share){
            Intent intent = new Intent(AkcijeActivity.this, MainActivity.class);
            //Intent intent1 = getIntent();
            SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
            String usernameProdavca = prefs.getString("userName", "No name defined");
            SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
            editor.putString("usernameProdavca", usernameProdavca);
            editor.apply();
            /*String username = intent1.getStringExtra("user");
            intent.putExtra("user", username);*/
            startActivity(intent);
            finish();
        }
        return true;
    }


    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + getDayFormat(day);
    }

    private String getMonthFormat(int month) {

        if(month==1){
            return "0";
        }
        if(month==2){
            return "01";
        }
        if(month==3){
            return "02";
        }
        if(month==4){
            return "03";
        }
        if(month==5){
            return "04";
        }
        if(month==6){
            return "06";
        }
        if(month==7){
            return "07";
        }
        if(month==8){
            return "08";
        }
        if(month==9){
            return "09";
        }
        if(month==10){
            return "10";
        }
        if(month==11){
            return "11";
        }
        if(month==12){
            return "11";
        }
        return "8";

    }


    private String getDayFormat(int day) {

        if(day==1){
            return "01";
        }
        if(day==2){
            return "02";
        }
        if(day==3){
            return "03";
        }
        if(day==4){
            return "04";
        }
        if(day==5){
            return "05";
        }
        if(day==6){
            return "06";
        }
        if(day==7){
            return "07";
        }
        if(day==8){
            return "08";
        }
        if(day==9){
            return "09";
        }
        if(day==10){
            return "10";
        }
        if(day==11){
            return "11";
        }
        if(day==12){
            return "12";
        }
        if(day==13){
            return "13";
        }
        if(day==14){
            return "14";
        }
        if(day==15){
            return "15";
        }
        if(day==16){
            return "16";
        }
        if(day==17){
            return "17";
        }
        if(day==18){
            return "18";
        }
        if(day==18){
            return "18";
        }
        if(day==19){
            return "19";
        }
        if(day==20){
            return "20";
        }
        if(day==21){
            return "21";
        }
        if(day==22){
            return "22";
        }
        if(day==23){
            return "23";
        }
        if(day==24){
            return "24";
        }
        if(day==25){
            return "25";
        }
        if(day==26){
            return "26";
        }
        if(day==27){
            return "27";
        }
        if(day==28){
            return "28";
        }
        if(day==29){
            return "29";
        }
        if(day==30){
            return "30";
        }
        if(day==31){
            return "31";
        }
        return "3";

    }

}
