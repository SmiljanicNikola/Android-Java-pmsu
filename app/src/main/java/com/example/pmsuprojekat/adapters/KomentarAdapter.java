/*package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsuprojekat.R;

import model.Komentar;

public class KomentarAdapter extends BaseAdapter {
    private Activity activity;


    public KomentarAdapter(Activity activity) { this.activity = activity; }

    @Override
    public int getCount() {
        return Mokap.getKomentare().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getKomentare().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Komentar komentar = Mokap.getKomentare().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.prikaz_komentara_list, null);


        TextView autor = (TextView)vi.findViewById(R.id.autor);
        TextView tekst = (TextView)vi.findViewById(R.id.tekst);
        // TextView cena = (TextView)vi.findViewById(R.id.cena);

        autor.setText(komentar.getAutor());
        tekst.setText(komentar.getTekst());
        //cena.setText(Double.valueOf( artikal.getCena()));


        return vi;
    }
}
*/