/*package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsuprojekat.R;

import model.Artikal;

public class ArtikalAdapter extends BaseAdapter {
    private Activity activity;

    public ArtikalAdapter(Activity activity) { this.activity = activity;}


    @Override
    public int getCount() {
        return Mokap.getArtikle().size();
    }

    @Override
    public Object getItem(int position) {
        return Mokap.getArtikle().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Artikal artikal = Mokap.getArtikle().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.prikaz_artikli_list, null);

        TextView naziv = (TextView)vi.findViewById(R.id.naziv);
        TextView opis = (TextView)vi.findViewById(R.id.opis);
       // TextView cena = (TextView)vi.findViewById(R.id.cena);

        naziv.setText(artikal.getNaziv());
        opis.setText(artikal.getOpis());
        //cena.setText(Double.valueOf( artikal.getCena()));

        return vi;
    }
}
*/