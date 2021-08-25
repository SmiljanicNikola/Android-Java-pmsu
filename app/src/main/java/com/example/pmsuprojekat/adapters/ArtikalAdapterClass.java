package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.NoviArtikalActivity;
import com.example.pmsuprojekat.activities.RecenzijaActivity;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;

import java.sql.Blob;
import java.util.List;

import model.Artikal;
import model.Prodavac;

public class ArtikalAdapterClass extends RecyclerView.Adapter<ArtikalAdapterClass.ViewHolder>{
    private SharedPreferenceConfig sharedPreferenceConfig;
    List<Artikal> artikli;
    Context context;
    DBHelper dbHelper;
    ImageView imgIcon;

    public ArtikalAdapterClass(List<Artikal> artikli, Context context) {
        this.artikli = artikli;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.artikal_item_list, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Artikal artikal = artikli.get(position);

        holder.textViewID.setText(Integer.toString(artikal.getId()));
        holder.editText_naziv.setText(artikal.getNaziv());
        holder.editText_opis.setText(artikal.getOpis());
        holder.editText_cena.setText(Double.toString(artikal.getCena()));
        holder.editText_putanja.setText(artikal.getPutanjaSlike());
        //holder.editText_prodavacId.setText(artikal.getProdavac_id());

        byte[] recordImage = artikal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.imgIcon.setImageBitmap(bitmap);

        //Izmena
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String naziv = holder.editText_naziv.getText().toString();
                String opis = holder.editText_opis.getText().toString();
                Double cena = Double.valueOf(holder.editText_cena.getText().toString());
                String putanja = holder.editText_putanja.getText().toString();
                //int prodavac_id = Integer.valueOf(holder.editText_prodavacId.getText().toString());

                dbHelper.updateArtikal(new Artikal(artikal.getId(),naziv,opis,cena,putanja));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        //Brisanje
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteArtikal(artikal.getId());
                artikli.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.btn_dodajArtikal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodavacId = artikal.getProdavac_id();
                Intent myIntent = new Intent(v.getContext(), NoviArtikalActivity.class);
                myIntent.putExtra("id", prodavacId);
                v.getContext().startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return artikli.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_naziv;
        EditText editText_opis;
        EditText editText_cena;
        EditText editText_putanja;
        EditText editText_prodavacId;
        ImageView imgIcon;
        Button btn_edit;
        Button btn_delete;
        Button btn_dodajArtikal;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_naziv = itemView.findViewById(R.id.editText_naziv);
            editText_opis = itemView.findViewById(R.id.editText_opis);
            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_putanja = itemView.findViewById(R.id.editText_putanja);
            editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_dodajArtikal = itemView.findViewById(R.id.btn_dodajArtikal);
            imgIcon = itemView.findViewById(R.id.imgIcon);


        }
    }

}
