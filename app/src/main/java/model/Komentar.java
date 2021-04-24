package model;

public class Komentar
{

    private String autor;
    private String tekst;

    public Komentar(){

    }

    public Komentar(String autor, String tekst) {
        this.autor = autor;
        this.tekst = tekst;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "autor='" + autor + '\'' +
                ", tekst='" + tekst + '\'' +
                '}';
    }
}
