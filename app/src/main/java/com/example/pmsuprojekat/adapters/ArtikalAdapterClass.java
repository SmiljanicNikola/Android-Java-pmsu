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

import java.util.List;

import model.Artikal;

public class ArtikalAdapterClass extends RecyclerView.Adapter<ArtikalAdapterClass.ViewHolder>{

    List<Artikal> artikli;
    Context context;
    DBHelper dbHelper;

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
        //holder.editText_putanja.setText(artikal.getPutanjaSlike());

        //Izmena
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String naziv = holder.editText_naziv.getText().toString();
                String opis = holder.editText_opis.getText().toString();
                Double cena = Double.valueOf(holder.editText_cena.getText().toString());
                //String putanja = holder.editText_putanja.getText().toString();

                dbHelper.updateArtikal(new Artikal(artikal.getId(),naziv,opis,cena));
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
        //EditText editText_putanja;
        Button btn_edit;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_naziv = itemView.findViewById(R.id.editText_naziv);
            editText_opis = itemView.findViewById(R.id.editText_opis);
            editText_cena = itemView.findViewById(R.id.editText_cena);
            //editText_putanja = itemView.findViewById(R.id.editText_putanja);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);


        }
    }

}
