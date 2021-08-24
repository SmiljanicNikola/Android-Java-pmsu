package model;

import java.time.LocalDate;
import java.util.Date;

public class Porudzbina {

    private Integer id;
    private LocalDate satnica;
    private boolean dostavljeno;
    private int ocena;
    private String komentar;
    private boolean anonimanKomentar;
    private boolean arhiviranKomentar;
    private int kupac_id;
    private int stavka_id;

    public Porudzbina(){

    }

    public Porudzbina(Integer id, boolean arhiviranKomentar) {
        this.id = id;
        this.arhiviranKomentar = arhiviranKomentar;
    }

    public Porudzbina(Integer id, LocalDate satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimanKomentar, boolean arhiviranKomentar, int kupac_id, int stavka_id) {
        this.id = id;
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimanKomentar = anonimanKomentar;
        this.arhiviranKomentar = arhiviranKomentar;
        this.kupac_id = kupac_id;
        this.stavka_id = stavka_id;
    }

    public Porudzbina(LocalDate satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimanKomentar, boolean arhiviranKomentar, int kupac_id, int stavka_id) {
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimanKomentar = anonimanKomentar;
        this.arhiviranKomentar = arhiviranKomentar;
        this.kupac_id = kupac_id;
        this.stavka_id = stavka_id;
    }

    public Porudzbina(Integer id, int ocena, String komentar) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public Porudzbina(Integer id, int ocena, String komentar, boolean anonimanKomentar) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimanKomentar = anonimanKomentar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSatnica() {
        return satnica;
    }

    public void setSatnica(LocalDate satnica) {
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

    public int getKupac_id() {
        return kupac_id;
    }

    public void setKupac_id(int kupac_id) {
        this.kupac_id = kupac_id;
    }

    public int getStavka_id() {
        return stavka_id;
    }

    public void setStavka_id(int stavka_id) {
        this.stavka_id = stavka_id;
    }
}
