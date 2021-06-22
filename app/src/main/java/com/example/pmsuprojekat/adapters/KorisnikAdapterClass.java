package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.SviKorisniciActivity;

import java.util.List;

import model.Korisnik;

public class KorisnikAdapterClass extends RecyclerView.Adapter<KorisnikAdapterClass.ViewHolder> {

    List<Korisnik> korisnici;
    Context context;
    DBHelper dbHelper;


    public KorisnikAdapterClass(List<Korisnik> korisnici, Context context) {
        this.korisnici = korisnici;
        this.context = context;
        dbHelper = new DBHelper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.korisnik_item_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Korisnik korisnik = korisnici.get(position);

        holder.textViewID.setText(Integer.toString(korisnik.getId()));
        holder.editText_korisnickoIme.setText(korisnik.getUsername());
        //holder.editText_putanja.setText(artikal.getPutanjaSlike());

        //Izmena
        holder.btn_blokiraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = holder.editText_korisnickoIme.getText().toString();
                //String putanja = holder.editText_putanja.getText().toString();

                dbHelper.updateKorisnik(new Korisnik(korisnik.getId(),true));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });


    }


    @Override
    public int getItemCount() {
        return korisnici.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_korisnickoIme;
        Button btn_blokiraj;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_korisnickoIme = itemView.findViewById(R.id.editText_korisnickoIme);
            //editText_putanja = itemView.findViewById(R.id.editText_putanja);
            btn_blokiraj = itemView.findViewById(R.id.btn_blokiraj);


        }
    }

}
