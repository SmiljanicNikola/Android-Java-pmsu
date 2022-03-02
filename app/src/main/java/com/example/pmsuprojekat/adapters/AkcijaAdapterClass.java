package com.example.pmsuprojekat.adapters;

import android.content.Context;
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
import java.time.LocalDate;
import java.util.Calendar;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dbHelper = new DBHelper(context);
        final Akcija akcija = akcije.get(position);
        holder.textViewID.setText(Integer.toString(akcija.getId()));
        //holder.editText_naziv.setText(Integer.toString(akcija.getProcenat()));
        holder.textViewProcenat.setText("Popust: "+Integer.toString(akcija.getProcenat())+"%");
        //holder.editText_opis.setText(akcija.getOdKad().toString());

        holder.textViewOdKad.setText("Akcija traje od: "+akcija.getOdKad().toString());
        //holder.editText_cena.setText(akcija.getDoKad().toString());
        if(LocalDate.parse(getTodaysDate()).isAfter(akcija.getDoKad())){
            dbHelper.deleteAkcija(akcija.getId());
            Artikal artikal = dbHelper.findArtikal(akcija.getArtikal_id());
            dbHelper.skiniAkciju(artikal);
            akcije.remove(position);
            notifyDataSetChanged();
        }

        holder.textViewDoKad.setText("Akcija traje do: "+akcija.getDoKad().toString());

        Artikal artikal = dbHelper.findArtikal(akcija.getArtikal_id());
        holder.textViewArtikalId.setText("Artikal koji je na popustu: "+artikal.getNaziv());


        //holder.editText_prodavacId.setText(artikal.getProdavac_id());


        //Izmena
        /*holder.btn_edit.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

    /*private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }*/

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

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + getDayFormat(day);
    }

    private String getMonthFormat(int month) {

        if(month==1){
            return "0";
        }
        if(month==2){
            return "01";
        }
        if(month==3){
            return "02";
        }
        if(month==4){
            return "03";
        }
        if(month==5){
            return "04";
        }
        if(month==6){
            return "06";
        }
        if(month==7){
            return "07";
        }
        if(month==8){
            return "08";
        }
        if(month==9){
            return "09";
        }
        if(month==10){
            return "10";
        }
        if(month==11){
            return "11";
        }
        if(month==12){
            return "11";
        }
        return "8";

    }


    private String getDayFormat(int day) {

        if(day==1){
            return "01";
        }
        if(day==2){
            return "02";
        }
        if(day==3){
            return "03";
        }
        if(day==4){
            return "04";
        }
        if(day==5){
            return "05";
        }
        if(day==6){
            return "06";
        }
        if(day==7){
            return "07";
        }
        if(day==8){
            return "08";
        }
        if(day==9){
            return "09";
        }
        if(day==10){
            return "10";
        }
        if(day==11){
            return "11";
        }
        if(day==12){
            return "12";
        }
        if(day==13){
            return "13";
        }
        if(day==14){
            return "14";
        }
        if(day==15){
            return "15";
        }
        if(day==16){
            return "16";
        }
        if(day==17){
            return "17";
        }
        if(day==18){
            return "18";
        }
        if(day==18){
            return "18";
        }
        if(day==19){
            return "19";
        }
        if(day==20){
            return "20";
        }
        if(day==21){
            return "21";
        }
        if(day==22){
            return "22";
        }
        if(day==23){
            return "23";
        }
        if(day==24){
            return "24";
        }
        if(day==25){
            return "25";
        }
        if(day==26){
            return "26";
        }
        if(day==27){
            return "27";
        }
        if(day==28){
            return "28";
        }
        if(day==29){
            return "29";
        }
        if(day==30){
            return "30";
        }
        if(day==31){
            return "31";
        }
        return "3";

    }

}
