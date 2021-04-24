package model;

import java.util.Date;

public class Akcija {

    private int procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;

    public Akcija(){

    }

    public Akcija(int procenat, Date odKad, Date doKad, String tekst) {
        this.procenat = procenat;
        this.odKad = odKad;
        this.doKad = doKad;
        this.tekst = tekst;
    }

    public int getProcenat() {
        return procenat;
    }

    public void setProcenat(int procenat) {
        this.procenat = procenat;
    }

    public Date getOdKad() {
        return odKad;
    }

    public void setOdKad(Date odKad) {
        this.odKad = odKad;
    }

    public Date getDoKad() {
        return doKad;
    }

    public void setDoKad(Date doKad) {
        this.doKad = doKad;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String toString() {
        return "Akcija{" +
                "procenat=" + procenat +
                ", odKad=" + odKad +
                ", doKad=" + doKad +
                ", tekst='" + tekst + '\'' +
                '}';
    }
}
