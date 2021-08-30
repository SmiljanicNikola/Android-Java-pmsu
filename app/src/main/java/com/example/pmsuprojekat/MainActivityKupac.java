package com.example.pmsuprojekat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
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
import android.widget.SimpleCursorAdapter;
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
import com.example.pmsuprojekat.activities.IzabraniProdavacActivity;
import com.example.pmsuprojekat.activities.LoginActivity;
import com.example.pmsuprojekat.activities.NoviArtikalActivity;
import com.example.pmsuprojekat.activities.PorudzbinaActivity;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;
import com.example.pmsuprojekat.activities.SviKorisniciActivity;
import com.example.pmsuprojekat.adapters.DrawerListAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.Kupac;
import model.NavItem;
import model.Prodavac;

public class MainActivityKupac extends AppCompatActivity {
    private SharedPreferenceConfig sharedPreferenceConfig;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mTitle;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private Spinner spinner;
    DBHelper DB;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kupac);

        SharedPreferences sharedPref = MainActivityKupac.this.getPreferences(Context.MODE_PRIVATE);
        String userName = sharedPref.getString("userName", null);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        DB = new DBHelper(this);
        Intent intent = getIntent();
        //Korisnik korisnik = DB.findKorisnik(username);

        listItem = new ArrayList<>();
        userList = findViewById(R.id.prodavciListView);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = userList.getItemAtPosition(position).toString();
                Toast.makeText(MainActivityKupac.this,"Prodavac: "+username,Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(MainActivityKupac.this, IzabraniProdavacActivity.class);
                Intent intent = getIntent();

                SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                String usernameKupca = prefs.getString("userName", "No name defined");

                Kupac kupac = DB.findKupca(usernameKupca);
                int idKupca = kupac.getId();
                Prodavac prodavac = DB.findProdavac(username);
                int idProdavca = prodavac.getId();
                /*SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                editor.putInt("id", idProdavca);
                editor.putInt("idKupca", idKupca);
                editor.putString("username", username);
                editor.putString("usernameKupca", usernameKupca);
                editor.apply();OOOOOOOOOO*/
                intent1.putExtra("id", idProdavca);
                intent1.putExtra("usernameKupca", usernameKupca);
                intent1.putExtra("idKupca", idKupca);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
        viewData();

        prepareMenu(mNavItems);

        mTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerList = findViewById(R.id.navList);

        mDrawerPane = findViewById(R.id.drawerPane);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new MainActivityKupac.DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

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

        // Izborom na neki element iz liste, pokrecemo akciju
        if (savedInstanceState == null) {
            selectItemFromDrawer(0);
        }

        spinner = findViewById(R.id.aSpinnerToolBar);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Izaberi");
        categories.add("Porudzbine");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Izaberi")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    if(parent.getItemAtPosition(position).equals("Porudzbine"))
                    {

                        Intent intent = new Intent(MainActivityKupac.this, PorudzbinaActivity.class);
                        Intent intent1 = getIntent();

                        SharedPreferences prefs = getSharedPreferences("My pref",MODE_PRIVATE);
                        String usernameKupca = prefs.getString("userName", "No name defined");
                        Kupac kupac = DB.findKupca(usernameKupca);
                        int idKupca = kupac.getId();

                        //SHARED NACIN---------------------------------------------
                        SharedPreferences.Editor editor = getSharedPreferences("My pref", MODE_PRIVATE).edit();
                        editor.putString("usernameKupca", usernameKupca);
                        editor.putInt("idKupca", idKupca);
                        editor.apply();

                        intent.putExtra("idKupca", idKupca);
                        intent.putExtra("usernameKupca", usernameKupca);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



    }

    private void viewData(){
        Cursor cursor = DB.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();

        } else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(3));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    private void prepareMenu(ArrayList<NavItem> mNavItems) {
        /*Intent intent = getIntent();
        String username = intent.getStringExtra("user");*/
        /*SharedPreferences sharedPref = MainActivityKupac.this.getPreferences(Context.MODE_PRIVATE);
        String userName = sharedPref.getString("userName", "");*/
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

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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
            //..
        } else if (position == 2) {
            //..
        } else if (position == 3) {
            sharedPreferenceConfig.login_status(false);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (position == 4) {
            //..
        } else if (position == 5) {
            //...
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
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }



}