package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;

import java.util.List;

import model.Artikal;
import model.Porudzbina;
import model.Stavka;

public class PorudzbinaAdapterClass extends RecyclerView.Adapter<PorudzbinaAdapterClass.ViewHolder>{

    List<Porudzbina> porudzbine;
    Context context;
    DBHelper dbHelper;

    public PorudzbinaAdapterClass(List<Porudzbina> porudzbine, Context context) {
        this.porudzbine = porudzbine;
        this.context = context;
        dbHelper = new DBHelper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.porudzbina_item_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Porudzbina porudzbina = porudzbine.get(position);

        holder.textViewID.setText(Integer.toString(porudzbina.getId()));
        holder.textViewSatnica.setText("Satnica(Datum): "+porudzbina.getSatnica().toString());
        holder.textViewKomentar.setText("Komentar: "+porudzbina.getKomentar());
        int stavka_id = porudzbina.getStavka_id();
        Stavka stavka = dbHelper.findStavka(stavka_id);
        int artikal_id = stavka.getArtikal_id();
        Artikal artikal = dbHelper.findArtikal(artikal_id);
        holder.textViewArtikal.setText("Naziv artikla: "+artikal.getNaziv());
        holder.textViewKolicina.setText("Kolicina narucenih artikala: "+stavka.getKolicina());
        holder.textViewCenaPojedinacno.setText("Cena artikla pojedinacno: "+artikal.getCena());
        holder.textViewCenaUkupno.setText("Cena narudzbe ukupno:"+artikal.getCena()*stavka.getKolicina());
        //holder.editText_putanja.setText(artikal.getPutanjaSlike());
        //holder.editText_prodavacId.setText(artikal.getProdavac_id());

        //Izmena
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String naziv = holder.textViewSatnica.getText().toString();
                String opis = holder.textViewKomentar.getText().toString();
                Double cena = Double.valueOf(holder.editText_cena.getText().toString());
                String putanja = holder.editText_putanja.getText().toString();
                //int prodavac_id = Integer.valueOf(holder.editText_prodavacId.getText().toString());

                //dbHelper.updateArtikal(new Artikal(artikal.getId(),naziv,opis,cena,putanja));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        //Brisanje
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dbHelper.deleteArtikal(artikal.getId());
                //artikli.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return porudzbine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView textViewKomentar;
        TextView textViewSatnica;
        TextView textViewArtikal;
        TextView textViewKolicina;
        TextView textViewCenaPojedinacno;
        TextView textViewCenaUkupno;


        EditText editText_cena;
        EditText editText_putanja;
        EditText editText_prodavacId;
        ImageView imgIcon;
        Button btn_edit;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            textViewKomentar = itemView.findViewById(R.id.textViewKomentar);
            textViewSatnica = itemView.findViewById(R.id.textViewSatnica);
            textViewArtikal = itemView.findViewById(R.id.textViewArtikal);
            textViewKolicina = itemView.findViewById(R.id.textViewKolicina);
            textViewCenaPojedinacno = itemView.findViewById(R.id.textViewCenaPojedinacno);
            textViewCenaUkupno = itemView.findViewById(R.id.textViewCenaUkupno);


            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_putanja = itemView.findViewById(R.id.editText_putanja);
            editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            imgIcon = itemView.findViewById(R.id.imgIcon);


        }
    }


}
