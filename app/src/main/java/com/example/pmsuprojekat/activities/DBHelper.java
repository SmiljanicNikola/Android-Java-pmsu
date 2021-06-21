package com.example.pmsuprojekat.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Artikal;
import model.Korisnik;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";

    /*private static final String TABLE_NAMEArtikli = "artikli";

    public static final String id="id";
    public static final String naziv="naziv";
    public static final String opis="opis";
    public static final String cena= "cena";
    public static final String putanja="putanja";

    private static final String CREATE_TABLEArtikli = "create table" + TABLE_NAMEArtikli + "(" + id +
            "INTEGER PRIMARY KEY AUTOINCREMENT," + naziv + "TEXT," + opis + "TEXT,"
            + cena + "Double," + putanja + "TEXT );";*/


    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id INTEGER PRIMARY KEY AUTOINCREMENT,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, uloga TEXT, blokiran BOOLEAN)");
        MyDB.execSQL("create Table kupci(id INTEGER PRIMARY KEY AUTOINCREMENT ,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, adresa TEXT)");
        MyDB.execSQL("create Table prodavci(id INTEGER PRIMARY KEY AUTOINCREMENT ,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, poslujeOd LocaleDate, email TEXT, adresa TEXT, naziv TEXT )");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (1,'milorad','miloradovic','miloradm','321','administrator',0)");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (2,'milan','milanovic','milanm','321','administrator',0)");
        //MyDB.execSQL(CREATE_TABLEArtikli);
        MyDB.execSQL("create Table artikli(id INTEGER PRIMARY KEY AUTOINCREMENT, naziv TEXT, opis TEXT, cena DOUBLE, putanja TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists kupci");
        MyDB.execSQL("drop Table if exists prodavci");
        //MyDB.execSQL("drop Table if exists " + TABLE_NAMEArtikli);
        MyDB.execSQL("drop Table if exists artikli");


        //MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(MyDB);

    }

    public Boolean insertData(String ime, String prezime, String username, String password, String uloga, boolean b){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ime", ime);
        contentValues.put("prezime", prezime);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("blokiran",false);
        contentValues.put("uloga", uloga);

        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;

    }

    public Boolean insertKupci(String ime, String prezime, String username, String password, String adresa){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ime", ime);
        contentValues.put("prezime", prezime);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("adresa", adresa);

        long result = MyDB.insert("kupci", null, contentValues);
        if(result==-1) return false;
        else
            return true;

    }

    public Boolean insertProdavci(String ime, String prezime, String username, String password, LocalDate poslujeOd, String email, String adresa, String naziv){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ime", ime);
        contentValues.put("prezime", prezime);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("poslujeOd", String.valueOf(poslujeOd));
        contentValues.put("email", email);
        contentValues.put("adresa", adresa);
        contentValues.put("naziv", naziv);

        long result = MyDB.insert("prodavci", null, contentValues);
        if(result==-1) return false;
        else
            return true;

    }


    public void insertArtikal(Artikal artikal){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("naziv", artikal.getNaziv());
        contentValues.put("opis", artikal.getOpis());
        contentValues.put("cena", artikal.getCena());
        contentValues.put("putanja", artikal.getPutanjaSlike());

        MyDB.insert("artikli", null, contentValues);

    }

    public Korisnik findKorisnik(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[] {username});
        if(cursor.getCount() > 0){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String ime = cursor.getString(1);
            String prezime = cursor.getString(2);
            String usernamee = cursor.getString(3);
            String password = cursor.getString(4);
            String uloga = cursor.getString(5);
            Boolean obrisan = Boolean.parseBoolean(cursor.getString(6));
            cursor.close();
            Korisnik korisnik = new Korisnik(id,ime,prezime,usernamee,password,uloga,obrisan);

            return korisnik;
        }
        else{
            return null;
        }

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

        public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;


        }


        //CRUD OPERACIJE ZA ARTIKLE -----------------------------------------------------------------

        public List<Artikal> getArtikli(){
            String sql = "select * from artikli";
            SQLiteDatabase MyDB = this.getReadableDatabase();
            List<Artikal> artikli = new ArrayList<>();
            Cursor cursor = MyDB.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                do{
                    int id = Integer.parseInt(cursor.getString(0));
                    String naziv = cursor.getString(1);
                    String opis = cursor.getString(2);
                    Double cena = Double.parseDouble(cursor.getString(3));
                    String putanja = cursor.getString(4);
                    artikli.add(new Artikal(id,naziv,opis,cena,putanja));
                }while(cursor.moveToNext());
            }
            cursor.close();
            return artikli;

        }

        public void updateArtikal(Artikal artikal){
            ContentValues contentValues = new ContentValues();
            contentValues.put("naziv", artikal.getNaziv());
            contentValues.put("opis", artikal.getOpis());
            contentValues.put("cena", artikal.getCena());
            contentValues.put("putanja", artikal.getPutanjaSlike());
            SQLiteDatabase MyDB = this.getWritableDatabase();
            MyDB.update("artikli",contentValues, "id = ?", new String[] {String.valueOf(artikal.getId())});

        }

        public void deleteArtikal(int id){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            MyDB.delete("artikli", "id = ?", new String[] {String.valueOf(id)});
        }

}
