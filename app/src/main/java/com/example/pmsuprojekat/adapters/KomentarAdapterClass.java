package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
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
import model.Kupac;
import model.Porudzbina;

public class KomentarAdapterClass extends RecyclerView.Adapter<KomentarAdapterClass.ViewHolder> {

    List<Porudzbina> porudzbineProdavca;
    Context context;
    DBHelper dbHelper;

    public KomentarAdapterClass(List<Porudzbina> porudzbineProdavca, Context context){
        this.porudzbineProdavca = porudzbineProdavca;
        this.context = context;
        dbHelper = new DBHelper(context);


    }


    public KomentarAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.komentar_item_list, parent,false);
        KomentarAdapterClass.ViewHolder viewHolder = new KomentarAdapterClass.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Porudzbina porudzbina = porudzbineProdavca.get(position);

            holder.textViewID.setText(Integer.toString(porudzbina.getId()));
            //holder.editText_naziv.setText(Integer.toString(akcija.getProcenat()));
            String kupacId = String.valueOf(porudzbina.getKupac_id());

            Kupac kupac = dbHelper.findKupca2(kupacId);
            if(porudzbina.isAnonimanKomentar() == false) {
                holder.textViewAutorKomentara.setText("Autor komentara(Kupac): " + kupac.getUsername() + "|" + kupac.getIme() + " " + kupac.getPrezime());
            }else{
                holder.textViewAutorKomentara.setText("Anoniman komentar");
            }
            holder.textViewKomentar.setText("Komentar: "+porudzbina.getKomentar());


            //holder.editText_prodavacId.setText(artikal.getProdavac_id());

            holder.btn_arhiviraj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbHelper.arhivirajKomentar(new Porudzbina(porudzbina.getId(), true));
                    porudzbineProdavca.remove(position);

                    notifyDataSetChanged();
                /*((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());*/

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
        return porudzbineProdavca.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView textViewDoKad;
        TextView textViewOdKad;
        TextView textViewKomentar;
        TextView textViewAutorKomentara;

        EditText editText_naziv;
        EditText editText_opis;
        EditText editText_cena;
        EditText editText_putanja;
        EditText editText_prodavacId;

        ImageView imgIcon;
        Button btn_arhiviraj;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            textViewDoKad = itemView.findViewById(R.id.textViewDoKad);
            textViewOdKad = itemView.findViewById(R.id.textViewOdKad);
            textViewKomentar = itemView.findViewById(R.id.textViewKomentar);
            textViewAutorKomentara = itemView.findViewById(R.id.textViewAutorKomentara);

            editText_naziv = itemView.findViewById(R.id.editText_naziv);
            editText_opis = itemView.findViewById(R.id.editText_opis);
            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_putanja = itemView.findViewById(R.id.editText_putanja);
            editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_arhiviraj = itemView.findViewById(R.id.btn_arhiviraj);
            imgIcon = itemView.findViewById(R.id.imgIcon);


        }
    }

}
