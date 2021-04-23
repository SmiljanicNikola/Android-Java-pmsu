package com.example.pmsuprojekat.tools;

import java.util.ArrayList;
import java.util.List;
import com.example.pmsuprojekat.R;

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


}
