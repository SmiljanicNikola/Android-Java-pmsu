package model;

import java.time.LocalDate;

public class Prodavac {
    private Integer id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private LocalDate poslujeOd;
    private String email;
    private String adresa;
    private String naziv;

    public Prodavac(){

    }

    public Prodavac(Integer id, String ime, String prezime, String username, String password, LocalDate poslujeOd, String email, String adresa, String naziv) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.poslujeOd = poslujeOd;
        this.email = email;
        this.adresa = adresa;
        this.naziv = naziv;
    }

    public Prodavac(String ime, String prezime, String username, String password, LocalDate poslujeOd, String email, String adresa, String naziv) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.poslujeOd = poslujeOd;
        this.email = email;
        this.adresa = adresa;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getPoslujeOd() {
        return poslujeOd;
    }

    public void setPoslujeOd(LocalDate poslujeOd) {
        this.poslujeOd = poslujeOd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
