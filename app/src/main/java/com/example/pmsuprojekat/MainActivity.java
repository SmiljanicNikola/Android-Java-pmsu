package com.example.pmsuprojekat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import com.example.pmsuprojekat.activities.ArtikalActivity;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.KorisniciActivity;
import com.example.pmsuprojekat.activities.KorisnikDetailActivity;
import com.example.pmsuprojekat.activities.LoginActivity;
import com.example.pmsuprojekat.activities.NoviArtikalActivity;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;
import com.example.pmsuprojekat.activities.SviKorisniciActivity;
import com.example.pmsuprojekat.adapters.DrawerListAdapter;
/*import com.example.pmsuprojekat.fragments.MyFragment;*/
import com.example.pmsuprojekat.tools.FragmentTransition;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        //Korisnik korisnik = DB.findKorisnik(username);
        //Prodavac prodavac = DB.findProdavac(username);

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
        categories.add("Login");
        categories.add("Korisnici");
        categories.add("Akcije");
        categories.add("Porudzbine");
        categories.add("Artikli");
        categories.add("Dodaj artikal");
        categories.add("Svi korisnici");

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


                    if(parent.getItemAtPosition(position).equals("Korisnici")) {
                        Intent intent = new Intent(MainActivity.this, KorisniciActivity.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Login")) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Artikli"))
                    {

                        Intent intent = new Intent(MainActivity.this, ArtikalActivity.class);
                        Intent intent1 = getIntent();
                        String username = intent1.getStringExtra("user");
                        //Korisnik korisnik = DB.findKorisnik(username);
                        Prodavac prodavac = DB.findProdavac(username);
                        int idProdavca = prodavac.getId();
                        intent.putExtra("idProdavca", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Dodaj artikal"))
                    {
                        Intent intent1 = getIntent();
                        String username = intent1.getStringExtra("user");

                        Intent intent = new Intent(MainActivity.this, NoviArtikalActivity.class);
                        //Korisnik korisnik = DB.findKorisnik(username);
                        Prodavac prodavac = DB.findProdavac(username);
                        int idProdavca = prodavac.getId();
                        intent.putExtra("id", idProdavca);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Svi korisnici"))
                    {
                        Intent intent = new Intent(MainActivity.this, SviKorisniciActivity.class);
                        startActivity(intent);
                    }
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
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        if(username != null) {
            Korisnik korisnik = DB.findKorisnik(username);
            mNavItems.add(new NavItem(username, korisnik.getUloga(), R.drawable.ic_action_username));
        }
        else{
            mNavItems.add(new NavItem("You are logged out", "Logged out", R.drawable.ic_action_username));

        }
        mNavItems.add(new NavItem(getString(R.string.Empty),getString(R.string.Empty), R.drawable.ic_action_username));
        mNavItems.add(new NavItem(getString(R.string.about), getString(R.string.about_long), R.drawable.ic_action_username));
        mNavItems.add(new NavItem(getString(R.string.logOut), getString(R.string.logOut), R.drawable.ic_action_username));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


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
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
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