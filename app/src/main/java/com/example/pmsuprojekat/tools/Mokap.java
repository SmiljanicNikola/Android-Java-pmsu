package com.example.pmsuprojekat.tools;

import java.util.ArrayList;
import java.util.List;
import com.example.pmsuprojekat.R;

import model.Artikal;
import model.Cinema;
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
            Artikal a1 = new Artikal("Monitor","Monitor za racunar",14900,"@R.Drawable/ic_action_username");
            Artikal a2 = new Artikal("Telefon","Mobilni telefon Samsung",24999,"@R.Drawable/ic_action_username");
            Artikal a3 = new Artikal("Zvucnik","Zvucnik ORGO",3500,"@R.Drawable/ic_action_username");


            artikli.add(a1);
            artikli.add(a2);
            artikli.add(a3);

            return artikli;
        }
    }

