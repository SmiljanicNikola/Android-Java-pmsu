package com.example.pmsuprojekat.adapters;

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
import model.Kupac;
import model.Porudzbina;

public class AnonimanKomentarAdapterClass extends RecyclerView.Adapter<AnonimanKomentarAdapterClass.ViewHolder> {

    List<Porudzbina> porudzbineProdavca;
    Context context;
    DBHelper dbHelper;

    public AnonimanKomentarAdapterClass(List<Porudzbina> porudzbineProdavca, Context context){
        this.porudzbineProdavca = porudzbineProdavca;
        this.context = context;
        dbHelper = new DBHelper(context);

    }


    public AnonimanKomentarAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.anoniman_komentar_item_list, parent,false);
        AnonimanKomentarAdapterClass.ViewHolder viewHolder = new AnonimanKomentarAdapterClass.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Porudzbina porudzbina = porudzbineProdavca.get(position);

        holder.textViewID.setText(Integer.toString(porudzbina.getId()));
        String kupacId = String.valueOf(porudzbina.getKupac_id());

        Kupac kupac = dbHelper.findKupca2(kupacId);
        holder.textViewAutorKomentara.setText("Anoniman komentar");
        holder.textViewKomentar.setText("Komentar: "+porudzbina.getKomentar());

        holder.btn_arhiviraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.arhivirajKomentar(new Porudzbina(porudzbina.getId(), true));
                porudzbineProdavca.remove(position);

                notifyDataSetChanged();

            }
        });

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
