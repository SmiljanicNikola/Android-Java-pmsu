package model;

public class Korisnik {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    private boolean blokiran;

    public String getIme() {
        return ime;
    }


    public Korisnik(String ime, String prezime, String username, String password, boolean blokiran) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.blokiran = blokiran;
    }

    public Korisnik() {
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

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
