package com.example.pmsuprojekat.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DetailActivity;
import com.example.pmsuprojekat.activities.KorisnikDetailActivity;
import com.example.pmsuprojekat.adapters.CinemaAdapter;
import com.example.pmsuprojekat.adapters.KorisniciAdapter;
import com.example.pmsuprojekat.tools.Mokap;

import model.Cinema;
import model.Korisnik;

public class MyFragmentKor extends ListFragment {

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout_korisnik, vg, false);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Korisnik korisnik = Mokap.getKorisnici().get(position);

        /*
         * Ako nasoj aktivnosti zelimo da posaljemo nekakve podatke
         * za to ne treba da koristimo konstruktor. Treba da iskoristimo
         * identicnu strategiju koju smo koristili kda smo radili sa
         * fragmentima! Koristicemo Bundle za slanje podataka. Tacnije
         * intent ce formirati Bundle za nas, ali mi treba da pozovemo
         * odgovarajucu putExtra metodu.
         * */
        Intent intent = new Intent(getActivity(), KorisnikDetailActivity.class);
        intent.putExtra("prezime", korisnik.getPrezime());
        intent.putExtra("username", korisnik.getUsername());
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        //Dodaje se adapter
        KorisniciAdapter korisnikAdapter = new KorisniciAdapter(getActivity());
        setListAdapter(korisnikAdapter);
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

}
