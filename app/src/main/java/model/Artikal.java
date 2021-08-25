package model;

import java.util.List;

public class Artikal {

    private Integer id;
    private String naziv;
    private String opis;
    private double cena;
    private String putanjaSlike;
    private int prodavac_id;
    private byte[] image;
    private List<Akcija> akcije;


    public Artikal(){

    }
    public Artikal(Integer id,String naziv, String opis, double cena, String putanjaSlike) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
    }

    public Artikal(String naziv, String opis, double cena, String putanjaSlike) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
    }

    public Artikal(Integer id, String naziv, String opis, double cena, String putanjaSlike, int prodavac_id, byte[] image) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac_id = prodavac_id;
        this.image = image;
    }

    public Artikal(String naziv, String opis, double cena) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Artikal(String naziv, String opis, double cena, String putanjaSlike, int prodavac_id) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac_id = prodavac_id;
    }

    public Artikal(Integer id, String naziv, String opis, double cena) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Artikal(String naziv, String opis, double cena, String putanjaSlike, int prodavac_id, byte[] image) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac_id = prodavac_id;
        this.image = image;
    }

    public Artikal(Integer id, String naziv, String opis, double cena, String putanjaSlike, int prodavac_id) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac_id = prodavac_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getPutanjaSlike() {
        return putanjaSlike;
    }

    public void setPutanjaSlike(String putanjaSlike) {
        this.putanjaSlike = putanjaSlike;
    }

    public int getProdavac_id() {
        return prodavac_id;
    }

    public void setProdavac_id(int prodavac_id) {
        this.prodavac_id = prodavac_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Akcija> getAkcije() {
        return akcije;
    }

    public void setAkcije(List<Akcija> akcije) {
        this.akcije = akcije;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                ", putanjaSlike='" + putanjaSlike + '\'' +
                '}';
    }
}
