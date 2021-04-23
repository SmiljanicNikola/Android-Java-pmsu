package com.example.pmsuprojekat.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.ArtikalActivity;
import com.example.pmsuprojekat.activities.DetailActivity;
import com.example.pmsuprojekat.adapters.ArtikalAdapter;
import com.example.pmsuprojekat.tools.Mokap;

import model.Artikal;

public class MyFragmentArtikal extends ListFragment {


    public static MyFragmentArtikal newInstance() {
        return new MyFragmentArtikal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout, vg, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Artikal artikal = Mokap.getArtikle().get(position);

        /*
         * Ako nasoj aktivnosti zelimo da posaljemo nekakve podatke
         * za to ne treba da koristimo konstruktor. Treba da iskoristimo
         * identicnu strategiju koju smo koristili kda smo radili sa
         * fragmentima! Koristicemo Bundle za slanje podataka. Tacnije
         * intent ce formirati Bundle za nas, ali mi treba da pozovemo
         * odgovarajucu putExtra metodu.
         * */
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("naziv", artikal.getNaziv());
        intent.putExtra("opis",artikal.getOpis());
        //intent.putExtra("cena",artikal.getCena());
        startActivity(intent);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        /*CinemaAdapter adapter = new CinemaAdapter(getActivity());
        setListAdapter(adapter);*/

        ArtikalAdapter adapter = new ArtikalAdapter(getActivity());
        setListAdapter(adapter);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // ovo korostimo ako je nasa arhitekrura takva da imamo jednu aktivnost
        // i vise fragmentaa gde svaki od njih ima svoj menu unutar toolbar-a
        menu.clear();
        inflater.inflate(R.layout.activity_itemdetail, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            Toast.makeText(getActivity(), "Refresh App", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(getActivity(), "Create Text", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
