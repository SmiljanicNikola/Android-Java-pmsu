package com.example.pmsuprojekat.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.MainActivityKupac;
import com.example.pmsuprojekat.R;
import com.example.pmsuprojekat.activities.DBHelper;
import com.example.pmsuprojekat.activities.LoginActivity;
import com.example.pmsuprojekat.activities.NoviArtikalActivity;
import com.example.pmsuprojekat.activities.PotvrdaPorudzbineActivity;
import com.example.pmsuprojekat.activities.SharedPreferenceConfig;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Akcija;
import model.Artikal;
import model.Kupac;
import model.NavItem;
import model.Porudzbina;
import model.Prodavac;
import model.Stavka;

import static android.content.Context.MODE_PRIVATE;

public class ArtikalAdapterClass2 extends RecyclerView.Adapter<ArtikalAdapterClass2.ViewHolder> {

    List<Artikal> artikli;
    List<Akcija> akcije;

    Context context;
    DBHelper dbHelper;
    ImageView imgIcon;


    private SharedPreferenceConfig sharedPreferenceConfig;


    public ArtikalAdapterClass2(List<Artikal> artikli,List<Akcija> akcije, Context context) {
        this.artikli = artikli;
        this.akcije = akcije;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Artikal artikal = artikli.get(position);
        dbHelper = new DBHelper(context);
        //final Akcija akcija = dbHelper.findAkcija(artikal.getAkcija_id());
        //final List<Akcija> akcije = dbHelper.getAkcijeProdavca(String.valueOf(artikal.getProdavac_id()));

            holder.textViewID.setText(Integer.toString(artikal.getId()));
            byte[] recordImage = artikal.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
            holder.imgIcon.setImageBitmap(bitmap);

            holder.textViewNaziv.setText("Naziv: " + artikal.getNaziv());
            holder.textViewOpis.setText("Opis: " + artikal.getOpis());
            holder.textViewCena.setText(Double.toString(artikal.getCena()));

            /*int akcijaId = artikal.getAkcija_id();
            //Akcija akcija = dbHelper.findAkcija(akcijaId);
            if(akcija != null) {
                double stotiDeo = artikal.getCena() / 100;
                holder.textViewAkcijskaCena.setText(String.valueOf(artikal.getCena() - (stotiDeo * akcija.getProcenat())));
            }else{
                holder.textViewAkcijskaCena.setText("Nema");

            }*/

            if (position < akcije.size()) {
                Akcija akcija = akcije.get(position);
                if (akcija.getArtikal_id() == artikal.getId() && akcija.getProdavac_id() == artikal.getProdavac_id()) {
                    double stotiDeo = artikal.getCena() / 100;
                    holder.textViewAkcijskaCena.setText(String.valueOf(artikal.getCena() - (stotiDeo * akcija.getProcenat())));
                } else {
                    holder.textViewAkcijskaCena.setText("Nema akcije");
                }
            }else{
                holder.textViewAkcijskaCena.setText("Nema akcije");
            }

        /*for(Akcija akcija : akcije) {
            if (akcija.getArtikal_id() == artikal.getId()) {
                double stotiDeo = artikal.getCena()/100;
                holder.textViewAkcijskaCena.setText(String.valueOf(artikal.getCena()-(stotiDeo*akcija.getProcenat())));
            }
            else{
                holder.textViewAkcijskaCena.setText("Nema akcije");
            }
        }*/
        //holder.editText_putanja.setText(artikal.getPutanjaSlike());
        //holder.editText_prodavacId.setText(artikal.getProdavac_id());

        holder.btn_poruci.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                int artikal_id = Integer.valueOf(holder.textViewID.getText().toString());
                String naziv = holder.textViewNaziv.getText().toString();
                String opis = holder.textViewOpis.getText().toString();
                Double cena = Double.valueOf(holder.textViewCena.getText().toString());
                //int prodavac_id = Integer.valueOf(holder.editText_prodavacId.getText().toString());
                if(holder.editText_kolicina.getText().toString().equals("")){
                    Toast.makeText(v.getContext(), "Morate uneti kolicinu koju zelite da narucite!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int kolicina = Integer.valueOf(holder.editText_kolicina.getText().toString());


                    //int id = hashCode();
                    int idStavke = hashCode();
                    Stavka stavka = new Stavka(idStavke, kolicina, artikal_id);
                    int stavkaId = stavka.getId();
                    dbHelper.insertStavke(stavka);


                    SharedPreferences sharedPref = context.getSharedPreferences("My pref", Context.MODE_PRIVATE);
                    String usernameKupca = sharedPref.getString("userName", "No name defined");
                    //String usernameKupca = intent.getStringExtra("user"); ZAKOMENTARISAO REAL
                    Kupac kupac = dbHelper.findKupca(usernameKupca);
                    int idKupca = kupac.getId();


                    Porudzbina porudzbina = new Porudzbina(LocalDate.parse("2021-03-09"), false, 3, "Nije Unesen", false, false, idKupca, stavkaId);
                    dbHelper.insertPorudzbinu(porudzbina);

                    notifyDataSetChanged();
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());

                    Intent myIntent = new Intent(v.getContext(), PotvrdaPorudzbineActivity.class);
                    myIntent.putExtra("idKupca", idKupca);
                    myIntent.putExtra("kolicina", kolicina);
                    myIntent.putExtra("artikalId", artikal_id);
                    myIntent.putExtra("stavkaId", stavkaId);

                    v.getContext().startActivity(myIntent);

                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return artikli.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView textViewNaziv;
        TextView textViewCena;
        TextView textViewOpis;
        TextView textViewAkcijskaCena;

        EditText editText_kolicina;
        Button btn_poruci;
        ImageView imgIcon;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            textViewNaziv = itemView.findViewById(R.id.textViewNaziv);
            textViewOpis = itemView.findViewById(R.id.textViewOpis);
            textViewCena = itemView.findViewById(R.id.textViewCena);
            textViewAkcijskaCena = itemView.findViewById(R.id.textViewAkcijskaCena);

            editText_kolicina = itemView.findViewById(R.id.editText_kolicina);
            //editText_putanja = itemView.findViewById(R.id.editText_putanja);
            //editText_prodavacId = itemView.findViewById(R.id.editText_prodavacId);
            btn_poruci = itemView.findViewById(R.id.btn_poruci);
            imgIcon = itemView.findViewById(R.id.imgIcon);

        }
    }

}
