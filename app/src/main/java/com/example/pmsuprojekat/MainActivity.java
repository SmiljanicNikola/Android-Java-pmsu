package com.example.pmsuprojekat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pmsuprojekat.activities.AkcijeActivity;
import com.example.pmsuprojekat.activities.AnonimniKomentariActivity;
import com.example.pmsuprojekat.activities.ArtikalActivity;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.KomentarActivity;
import com.example.pmsuprojekat.activities.LoginActivity;
import com.example.pmsuprojekat.activities.NovaAkcijaActivity;
import com.example.pmsuprojekat.activities.NoviArtikalActivity;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;
import com.example.pmsuprojekat.activities.SviKorisniciActivity;
import com.example.pmsuprojekat.adapters.DrawerListAdapter;
/*import com.example.pmsuprojekat.fragments.MyFragment;*/

import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.NavItem;
import model.Prodavac;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceConfig sharedPreferenceConfig;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private Spinner spinner;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        DB = new DBHelper(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");

        prepareMenu(mNavItems);

        mTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);

        mDrawerPane = findViewById(R.id.drawerPane);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        // Specificiramo da kada se drawer zatvori prikazujemo jednu ikonu
        // kada se drawer otvori drugu. Za to je potrebo da ispranvo povezemo
        // Toolbar i ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("iReviewer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        if (savedInstanceState == null) {
            selectItemFromDrawer(0);
        }


        spinner = findViewById(R.id.aSpinnerToolBar);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Izaberi");
        categories.add("Akcije");
        categories.add("Dodaj akciju");
        categories.add("Komentari");
        categories.add("Anonimni komentari");
        categories.add("Artikli");
        //categories.add("Dodaj artikal");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Izaberi")) {
                    //Nista
                } else {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    if(parent.getItemAtPosition(position).equals("Artikli"))
                    {

                        Intent intent = new Intent(MainActivity.this, ArtikalActivity.class);
                        Intent intent1 = getIntent();

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        String username = intent1.getStringExtra("user");
                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        //Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("idProdavca", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Komentari"))
                    {

                        Intent intent = new Intent(MainActivity.this, KomentarActivity.class);
                        Intent intent1 = getIntent();

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        String username = intent1.getStringExtra("user");
                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("idProdavca", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Anonimni komentari"))
                    {

                        Intent intent = new Intent(MainActivity.this, AnonimniKomentariActivity.class);
                        Intent intent1 = getIntent();

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        String username = intent1.getStringExtra("user");
                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("idProdavca", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Dodaj artikal"))
                    {
                        Intent intent1 = getIntent();
                        String username = intent1.getStringExtra("user");

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        Intent intent = new Intent(MainActivity.this, NoviArtikalActivity.class);
                        //Korisnik korisnik = DB.findKorisnik(username);
                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("id", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }

                    if(parent.getItemAtPosition(position).equals("Akcije"))
                    {
                        Intent intent = new Intent(MainActivity.this, AkcijeActivity.class);
                        Intent intent1 = getIntent();
                        String username = intent1.getStringExtra("user");

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("idProdavca", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    /*if(parent.getItemAtPosition(position).equals("Dodaj akciju"))
                    {
                        Intent intent1 = getIntent();
                        String username = intent1.getStringExtra("user");

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameProdavca = prefs.getString("userName", "No name defined");

                        Intent intent = new Intent(MainActivity.this, NovaAkcijaActivity.class);
                        Prodavac prodavac = DB.findProdavac(usernameProdavca);
                        int idProdavca = prodavac.getId();

                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameProdavca", usernameProdavca);
                        editor.putInt("idProdavca", idProdavca);
                        editor.apply();

                        intent.putExtra("id", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);

                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


    private void prepareMenu(ArrayList<NavItem> mNavItems) {
        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
        String userName = prefs.getString("userName", "No name defined");//"No name defined" is the default value.
        Korisnik korisnik = DB.findKorisnik(userName);
        if(userName != null) {
            mNavItems.add(new NavItem(userName, korisnik.getUloga(), R.drawable.ic_action_username));
        }
        else{
            mNavItems.add(new NavItem("You are logged out", "Logged out", R.drawable.ic_action_username));

        }
        mNavItems.add(new NavItem(getString(R.string.Location),getString(R.string.FindUs), R.drawable.ic_action_username));
        mNavItems.add(new NavItem(getString(R.string.about), getString(R.string.about_long), R.drawable.ic_action_username));
        mNavItems.add(new NavItem(getString(R.string.logOut), getString(R.string.logOut), R.drawable.ic_action_username));
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }


    private void selectItemFromDrawer(int position) {
        if (position == 0) {
            //FragmentTransition.to(MyFragment.newInstance(), this, false);
        } else if (position == 1) {
            sharedPreferenceConfig.login_status(false);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (position == 2) {
            //..
        } else if (position == 3) {
            sharedPreferenceConfig.login_status(false);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (position == 4) {
            //..
        } else if (position == 5) {
            //..
        } else {
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        if (position != 5) // za sve osim za sync
        {
            setTitle(mNavItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }


    @Override
    public void setTitle(CharSequence title) {
        /*mTitle = title;
        getSupportActionBar().setTitle(mTitle);*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }





}