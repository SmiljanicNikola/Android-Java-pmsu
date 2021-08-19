package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.ContentValues;
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

import model.Akcija;
import model.Artikal;

public class AkcijaAdapterClass extends RecyclerView.Adapter<AkcijaAdapterClass.ViewHolder> {

    List<Akcija> akcije;
    Context context;
    DBHelper dbHelper;
    ImageView imgIcon;

    public AkcijaAdapterClass(List<Akcija> akcije, Context context){
        this.akcije = akcije;
        this.context = context;
        dbHelper = new DBHelper(context);
    }


    public AkcijaAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.akcija_item_list, parent,false);
        AkcijaAdapterClass.ViewHolder viewHolder = new AkcijaAdapterClass.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Akcija akcija = akcije.get(position);
        holder.textViewID.setText(Integer.toString(akcija.getId()));
        //holder.editText_naziv.setText(Integer.toString(akcija.getProcenat()));
        holder.textViewProcenat.setText(Integer.toString(akcija.getProcenat()));
        //holder.editText_opis.setText(akcija.getOdKad().toString());
        holder.textViewOdKad.setText(akcija.getOdKad().toString());
        //holder.editText_cena.setText(akcija.getDoKad().toString());
        holder.textViewDoKad.setText(akcija.getDoKad().toString());

        Artikal artikal = dbHelper.findArtikal(akcija.getArtikal_id());
        holder.textViewArtikalId.setText(artikal.getNaziv());


        //holder.editText_prodavacId.setText(artikal.getProdavac_id());


        //Izmena
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String naziv = holder.editText_naziv.getText().toString();
                String opis = holder.editText_opis.getText().toString();
                Double cena = Double.valueOf(holder.editText_cena.getText().toString());
                String putanja = holder.editText_putanja.getText().toString();
                //int prodavac_id = Integer.valueOf(holder.editText_prodavacId.getText().toString());

                dbHelper.updateArtikal(new Artikal(akcija.getId(),naziv,opis,cena,putanja));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        /*//Brisanje
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteArtikal(akcija.getId());
                akcije.remove(position);
                notifyDataSetChanged();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return akcije.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView textViewDoKad;
        TextView textViewOdKad;
        TextView textViewProcenat;
        TextView textViewArtikalId;

        EditText editText_naziv;
        EditText editText_opis;
        EditText editText_cena;
        EditText editText_putanja;
        EditText editText_prodavacId;

        ImageView imgIcon;
        Button btn_edit;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            textViewDoKad = itemView.findViewById(R.id.textViewDoKad);
            textViewOdKad = itemView.findViewById(R.id.textViewOdKad);
            textViewProcenat = itemView.findViewById(R.id.textViewProcenat);
            textViewArtikalId = itemView.findViewById(R.id.textViewArtikalId);

            editText_naziv = itemView.findViewById(R.id.editText_naziv);
            editText_opis = itemView.findViewById(R.id.editText_opis);
            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_putanja = itemView.findViewById(R.id.editText_putanja);
            editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            imgIcon = itemView.findViewById(R.id.imgIcon);


        }
    }

}
