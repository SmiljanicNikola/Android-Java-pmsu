package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.RecenzijaActivity;

import java.util.List;

import model.Artikal;
import model.Korisnik;
import model.Porudzbina;
import model.Stavka;

public class SvePorudzbineAdapterClass extends RecyclerView.Adapter<SvePorudzbineAdapterClass.ViewHolder> {

    List<Porudzbina> porudzbine;
    Context context;
    DBHelper dbHelper;

    public SvePorudzbineAdapterClass(List<Porudzbina> porudzbine, Context context) {
        this.porudzbine = porudzbine;
        this.context = context;
        dbHelper = new DBHelper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sve_porudzbine_item_list, parent,false);
        SvePorudzbineAdapterClass.ViewHolder viewHolder = new SvePorudzbineAdapterClass.ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Porudzbina porudzbina = porudzbine.get(position);
        //List<Akcija> akcije = dbHelper.getAkcije();

        holder.textViewID.setText(Integer.toString(porudzbina.getId()));
        holder.textViewSatnica.setText(porudzbina.getSatnica().toString());
        holder.textViewKomentar.setText(porudzbina.getKomentar());
        int stavka_id = porudzbina.getStavka_id();
        Stavka stavka = dbHelper.findStavka(stavka_id);
        int artikal_id = stavka.getArtikal_id();
        Artikal artikal = dbHelper.findArtikal(artikal_id);
        holder.textViewArtikal.setText(artikal.getNaziv());
        holder.textViewKolicina.setText(String.valueOf(stavka.getKolicina()));
        holder.textViewCenaPojedinacno.setText(String.valueOf(artikal.getCena()));
        holder.textViewCenaUkupno.setText((String.valueOf(artikal.getCena()*stavka.getKolicina())));


        //Izmena
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idPorudzbine = Integer.valueOf(holder.textViewID.getText().toString());
                Integer id_kupca = porudzbina.getKupac_id();

                dbHelper.dostaviPorudzbinu(new Porudzbina(idPorudzbine, true));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());


                //dbHelper.updateArtikal(new Artikal(artikal.getId(),naziv,opis,cena,putanja));
                /*notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());*/

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
        TextView textViewCenaSaAkcijom;


        EditText editText_cena;
        EditText editText_putanja;
        EditText editText_prodavacId;
        ImageView imgIcon;
        Button btn_edit;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            textViewKomentar = itemView.findViewById(R.id.textViewKomentar);
            textViewSatnica = itemView.findViewById(R.id.textViewSatnica);
            textViewArtikal = itemView.findViewById(R.id.textViewArtikal);
            textViewKolicina = itemView.findViewById(R.id.textViewKolicina);
            textViewCenaPojedinacno = itemView.findViewById(R.id.textViewCenaPojedinacno);
            textViewCenaUkupno = itemView.findViewById(R.id.textViewCenaUkupno);
            //textViewCenaSaAkcijom = itemView.findViewById(R.id.textViewCenaSaAkcijom);


            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_putanja = itemView.findViewById(R.id.editText_putanja);
            editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            imgIcon = itemView.findViewById(R.id.imgIcon);


        }
    }
}
