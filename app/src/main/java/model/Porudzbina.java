package model;

import java.util.Date;

public class Porudzbina {

    private Integer id;
    private Date satnica;
    private boolean dostavljeno;
    private int ocena;
    private String komentar;
    private boolean anonimanKomentar;
    private boolean arhiviranKomentar;
    private Kupac kupac;

    public Porudzbina(){

    }

    public Porudzbina(Integer id, Date satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimanKomentar, boolean arhiviranKomentar, Kupac kupac) {
        this.id = id;
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimanKomentar = anonimanKomentar;
        this.arhiviranKomentar = arhiviranKomentar;
        this.kupac = kupac;
    }

    public Porudzbina(Date satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimanKomentar, boolean arhiviranKomentar, Kupac kupac) {
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimanKomentar = anonimanKomentar;
        this.arhiviranKomentar = arhiviranKomentar;
        this.kupac = kupac;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSatnica() {
        return satnica;
    }

    public void setSatnica(Date satnica) {
        this.satnica = satnica;
    }

    public boolean isDostavljeno() {
        return dostavljeno;
    }

    public void setDostavljeno(boolean dostavljeno) {
        this.dostavljeno = dostavljeno;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public boolean isAnonimanKomentar() {
        return anonimanKomentar;
    }

    public void setAnonimanKomentar(boolean anonimanKomentar) {
        this.anonimanKomentar = anonimanKomentar;
    }

    public boolean isArhiviranKomentar() {
        return arhiviranKomentar;
    }

    public void setArhiviranKomentar(boolean arhiviranKomentar) {
        this.arhiviranKomentar = arhiviranKomentar;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }
}
