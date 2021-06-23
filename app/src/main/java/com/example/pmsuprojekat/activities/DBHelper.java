package com.example.pmsuprojekat.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Artikal;
import model.Korisnik;
import model.Kupac;
import model.Porudzbina;
import model.Prodavac;
import model.Stavka;


public class  DBHelper extends SQLiteOpenHelper {

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
        MyDB.execSQL("create Table users(id INTEGER PRIMARY KEY AUTOINCREMENT,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, uloga TEXT, blokiran boolean)");
        MyDB.execSQL("create Table kupci(id INTEGER PRIMARY KEY AUTOINCREMENT ,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, adresa TEXT)");
        MyDB.execSQL("create Table prodavci(id INTEGER PRIMARY KEY AUTOINCREMENT ,ime TEXT, prezime TEXT, username TEXT NOT NULL, password TEXT, poslujeOd LocaleDate, email TEXT, adresa TEXT, naziv TEXT )");
        MyDB.execSQL("create Table akcije(id INTEGER PRIMARY KEY AUTOINCREMENT, procenat INTEGER, odKad LocaleDate, doKad LocaleDate, tekst TEXT, prodavac_id INTEGER, FOREIGN KEY(prodavac_id) REFERENCES prodavci(id))");
        MyDB.execSQL("create Table stavke(id INTEGER PRIMARY KEY AUTOINCREMENT, int kolicina, artikal_id INTEGER, FOREIGN KEY(artikal_id) REFERENCES artikli(id))");

        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (1,'milorad','miloradovic','miloradm','321','administrator',0)");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (2,'milan','milanovic','milanm','321','administrator',0)");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (3,'nenad','nenadovic','nenadn','123','prodavac',0)");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (4,'ivan','ivanovic','ivani','123','prodavac',0)");
        MyDB.execSQL("Insert into users(id,ime,prezime,username,password,uloga,blokiran) VALUES (5,'predrag','predragovic','predragp','123','prodavac',0)");


        MyDB.execSQL("Insert into prodavci(id,ime,prezime,username,password,poslujeOd,email,adresa,naziv) VALUES (1,'nenad','nenadovic','nenadn','123','2021-04-04','nenad@nenadovic.com','nenadska 50','Fruity')");
        MyDB.execSQL("Insert into prodavci(id,ime,prezime,username,password,poslujeOd,email,adresa,naziv) VALUES (2,'predrag','predragovic','predragp','123','2021-05-05','predrag@predragovic.com','fruskogorska 32','GlassDOO')");
        MyDB.execSQL("Insert into prodavci(id,ime,prezime,username,password,poslujeOd,email,adresa,naziv) VALUES (3,'ivan','ivanovic','ivani','123','2021-03-04','ivan@ivanovic.com','ivanoviceva 40','Shop&GO')");

        //MyDB.execSQL(CREATE_TABLEArtikli);
        MyDB.execSQL("create Table artikli(id INTEGER PRIMARY KEY AUTOINCREMENT, naziv TEXT, opis TEXT, cena DOUBLE, putanja TEXT,prodavac_id INTEGER, FOREIGN KEY (prodavac_id)\n" +
                "       REFERENCES prodavci (id) )");

        MyDB.execSQL("create Table porudzbine(id INTEGER PRIMARY KEY AUTOINCREMENT, satnica LocaleDate, dostavljeno boolean, ocena INTEGER, komentar TEXT, anonimanKomentar boolean, arhiviranKomentar boolean, " +
                "kupac_id INTEGER, FOREIGN KEY(kupac_id) REFERENCES kupci(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists kupci");
        MyDB.execSQL("drop Table if exists prodavci");
        //MyDB.execSQL("drop Table if exists " + TABLE_NAMEArtikli);
        MyDB.execSQL("drop Table if exists artikli");
        MyDB.execSQL("drop Table if exists akcije");
        MyDB.execSQL("drop Table if exists stavke");


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
        contentValues.put("uloga", uloga);
        contentValues.put("blokiran",false);

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

    public void insertStavke(Stavka stavka){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("kolicina", stavka.getKolicina());
        contentValues.put("artikal_id", stavka.getArtikal_id());

        MyDB.insert("artikli", null, contentValues);
    }

    public void insertPorudzbinu(Porudzbina porudzbina){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("satnica", String.valueOf(porudzbina.getSatnica()));
        contentValues.put("dostavljeno", porudzbina.isDostavljeno());
        contentValues.put("ocena", porudzbina.getOcena());
        contentValues.put("komentar", porudzbina.getKomentar());
        contentValues.put("anonimanKomentar", porudzbina.isAnonimanKomentar());
        contentValues.put("arhiviranKomentar", porudzbina.isArhiviranKomentar());
        contentValues.put("kupac_id", porudzbina.getKupac_id());


        MyDB.insert("porudzbine", null, contentValues);

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
        contentValues.put("prodavac_id", artikal.getProdavac_id());

        MyDB.insert("artikli", null, contentValues);

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Korisnik findKorisnik(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[] {username});
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String ime = cursor.getString(1);
            String prezime = cursor.getString(2);
            String usernamee = cursor.getString(3);
            String password = cursor.getString(4);
            String uloga = cursor.getString(5);
            boolean blokiran = Boolean.parseBoolean(cursor.getString(6));

            Korisnik korisnik = new Korisnik(id,ime,prezime,username,password,uloga,blokiran);

            return korisnik;
        }
        else{
            return null;
        }

    }


    public Kupac findKupca(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from kupci where username=?", new String[] {username});
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String ime = cursor.getString(1);
            String prezime = cursor.getString(2);
            String usernamee = cursor.getString(3);
            String password = cursor.getString(4);
            String adresa = cursor.getString(5);

            Kupac kupac = new Kupac(id,ime,prezime,username,password,adresa);

            return kupac;
        }
        else{
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Prodavac findProdavac(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from prodavci where username=?", new String[] {username});
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String ime = cursor.getString(1);
            String prezime = cursor.getString(2);
            String usernamee = cursor.getString(3);
            String password = cursor.getString(4);
            //LocalDate poslujeOd = LocalDate.parse(cursor.getColumnIndexOrThrow("poslujeOd"));
            LocalDate poslujeOd = LocalDate.parse(cursor.getString(5));
            String email = cursor.getString(6);
            String adresa = cursor.getString(7);
            String naziv = cursor.getString(8);
           // boolean blokiran = Boolean.parseBoolean(cursor.getString(6));

            Prodavac prodavac = new Prodavac(id,ime,prezime,username,password,poslujeOd,email,adresa,naziv);

            return prodavac;
        }
        else{
            return null;
        }

    }


    public List<Artikal> getArtikliProdavca(String prodavacId){
        //String sql = "select * from artikli where prodavac_id=?";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        List<Artikal> artikli = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery("select * from artikli where prodavac_id=?", new String[] {prodavacId});
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String naziv = cursor.getString(1);
                String opis = cursor.getString(2);
                Double cena = Double.parseDouble(cursor.getString(3));
                String putanja = cursor.getString(4);
                Integer prodavac_id = cursor.getInt(5);
                artikli.add(new Artikal(id,naziv,opis,cena,putanja,prodavac_id));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return artikli;

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
            String sql = "select * from artikli ";
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




        public List<Korisnik> getKorisnike(){
            String sql = "select * from users";
            SQLiteDatabase MyDB = this.getReadableDatabase();
            List<Korisnik> korisnici = new ArrayList<>();
            Cursor cursor = MyDB.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                do{
                    int id = Integer.parseInt(cursor.getString(0));
                    String ime = cursor.getString(1);
                    String prezime = cursor.getString(2);
                    String username = cursor.getString(3);
                    String password = cursor.getString(4);
                    String uloga = cursor.getString(5);
                    Boolean blokiran = Boolean.parseBoolean(cursor.getString(6));
                    korisnici.add(new Korisnik(id,ime,prezime,username,password,uloga,blokiran));
                }while(cursor.moveToNext());
            }
            cursor.close();
            return korisnici;
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

        public void updateKorisnik(Korisnik korisnik){
        ContentValues contentValues = new ContentValues();
        contentValues.put("blokiran", korisnik.isBlokiran());
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.update("users",contentValues, "id = ?", new String[] {String.valueOf(korisnik.getId())});

    }


        public void deleteArtikal(int id){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            MyDB.delete("artikli", "id = ?", new String[] {String.valueOf(id)});
        }

        public Cursor viewData(){
            SQLiteDatabase MyDB = this.getReadableDatabase();
            String query = "Select * from prodavci";
            Cursor cursor = MyDB.rawQuery(query, null);
            return cursor;
        }

}
