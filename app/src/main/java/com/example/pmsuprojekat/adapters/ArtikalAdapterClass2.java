package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Artikal;
import model.Kupac;
import model.NavItem;
import model.Porudzbina;
import model.Stavka;

import static android.content.Context.MODE_PRIVATE;

public class ArtikalAdapterClass2 extends RecyclerView.Adapter<ArtikalAdapterClass2.ViewHolder> {

    List<Artikal> artikli;
    Context context;
    DBHelper dbHelper;

    private SharedPreferenceConfig sharedPreferenceConfig;


    public ArtikalAdapterClass2(List<Artikal> artikli, Context context) {
        this.artikli = artikli;
        this.context = context;
        dbHelper = new DBHelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.artikal_item_list2, parent,false);
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
        //holder.editText_prodavacId.setText(artikal.getProdavac_id());


        holder.btn_poruci.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                int artikal_id = Integer.valueOf(holder.textViewID.getText().toString());
                String naziv = holder.editText_naziv.getText().toString();
                String opis = holder.editText_opis.getText().toString();
                Double cena = Double.valueOf(holder.editText_cena.getText().toString());
                //int prodavac_id = Integer.valueOf(holder.editText_prodavacId.getText().toString());
                int kolicina = Integer.valueOf(holder.editText_kolicina.getText().toString());
                //int id = hashCode();

                Stavka stavka = new Stavka(kolicina,artikal_id);
                int stavkaId = stavka.getId();
                dbHelper.insertStavke(stavka);
                SharedPreferences sharedPref = context.getSharedPreferences("My pref",Context.MODE_PRIVATE);
                String usernameKupca = sharedPref.getString("userName", "No name defined");

                //String usernameKupca = intent.getStringExtra("user"); ZAKOMENTARISAO REAL
                Kupac kupac = dbHelper.findKupca(usernameKupca);
                int idKupca = kupac.getId();

                //String usernameKupca = intent.getStringExtra("user"); ZAKOMENTARISAO REAL


                Porudzbina porudzbina = new Porudzbina(LocalDate.parse("2021-04-04"),false,4,"teksttekst",false,false, idKupca, stavkaId);
                dbHelper.insertPorudzbinu(porudzbina);

                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

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
        EditText editText_kolicina;
        //EditText editText_putanja;
        //EditText editText_prodavacId;
        Button btn_poruci;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_naziv = itemView.findViewById(R.id.editText_naziv);
            editText_opis = itemView.findViewById(R.id.editText_opis);
            editText_cena = itemView.findViewById(R.id.editText_cena);
            editText_kolicina = itemView.findViewById(R.id.editText_kolicina);
            //editText_putanja = itemView.findViewById(R.id.editText_putanja);
            //editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_poruci = itemView.findViewById(R.id.btn_poruci);


        }
    }

}
