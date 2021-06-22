package model;

public class Korisnik {

    private Integer id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String uloga;
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

    public Korisnik(Integer id, String ime, String prezime, String username, String password, boolean blokiran) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.blokiran = blokiran;
    }

    public Korisnik(Integer id, boolean blokiran) {
        this.id = id;
        this.blokiran = blokiran;
    }

    public Korisnik(Integer id, String ime, String prezime, String username, String password, String uloga, boolean blokiran) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.uloga = uloga;
        this.blokiran = blokiran;
    }

    public Korisnik() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
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
