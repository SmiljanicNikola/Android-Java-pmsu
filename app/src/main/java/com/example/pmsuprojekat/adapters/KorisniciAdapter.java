package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.tools.Mokap;

import model.Cinema;
import model.Korisnik;

public class KorisniciAdapter extends BaseAdapter {
    private Activity activity;

    public KorisniciAdapter(Activity activity){ this.activity=activity;}
    @Override
    public int getCount() {
        return Mokap.getKorisnici().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getCinemas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Korisnik korisnik = Mokap.getKorisnici().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.cinema_list, null);

        TextView ime = (TextView)vi.findViewById(R.id.ime);
        TextView prezime = (TextView)vi.findViewById(R.id.prezime);
        TextView username = (TextView)vi.findViewById(R.id.username);

        ime.setText(korisnik.getIme());
        prezime.setText(korisnik.getPrezime());
        username.setText(korisnik.getUsername());

        return vi;

    }
}
