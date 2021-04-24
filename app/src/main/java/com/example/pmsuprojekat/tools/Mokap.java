package com.example.pmsuprojekat.tools;

import java.util.ArrayList;
import java.util.List;
import com.example.pmsuprojekat.R;

import model.Akcija;
import model.Artikal;
import model.Cinema;
import model.Komentar;
import model.Korisnik;

public class Mokap {

    public static List<Cinema> getCinemas() {
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        Cinema u1 = new Cinema("Arena", "Cineplexx 3D", -1);
        Cinema u2 = new Cinema("Cinestar", "Najnoviji 5D", R.drawable.ic_action_username);
        Cinema u3 = new Cinema("Jadran", "Tradicionalni u mirnom ambijentu", -1);

        cinemas.add(u1);
        cinemas.add(u2);
        cinemas.add(u3);

        return cinemas;

    }


    public static List<Korisnik> getKorisnici(){
        ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
        Korisnik k1 = new Korisnik("Marko","Markovic","markom","123",false);
        Korisnik k2 = new Korisnik("Ivan","Ivanovic","ivani","321",false);
        Korisnik k3 = new Korisnik("Milana","Milanovic","milanam","12345", false);

        korisnici.add(k1);
        korisnici.add(k2);
        korisnici.add(k3);

        return korisnici;
    }


    public static List<Artikal> getArtikle(){
        ArrayList<Artikal> artikli = new ArrayList<Artikal>();
            Artikal a1 = new Artikal("Monitor","Dell 32 inch professional",32900,"@R.Drawable/ic_action_username");
            Artikal a2 = new Artikal("Telefon","Samsung J3 2018",24999,"@R.Drawable/ic_action_username");
            Artikal a3 = new Artikal("Monitor","LG Full HD 24 Inch",15000,"@R.Drawable/ic_action_username");
            Artikal a4 = new Artikal("Wide-Monitor","Ultra-Wide 36 inch",42000,"@R.Drawable/ic_action_username");



            artikli.add(a1);
            artikli.add(a2);
            artikli.add(a3);
            artikli.add(a4);

            return artikli;
        }


     public static List<Komentar> getKomentare(){
        ArrayList<Komentar> komentari = new ArrayList<Komentar>();
        Komentar k1 = new Komentar("Petar Petrovic", "Sto se tice kupca, korektan i posten!");
        Komentar k2 = new Komentar("Mirko Mirkovic", "Sve odlicno, svaka preporuka!");
        Komentar k3 = new Komentar("Petar Petrovic", "Tacan, usluzan i dosao na vreme!");

        komentari.add(k1);
        komentari.add(k2);
        komentari.add(k3);

         return komentari;

     }




}

