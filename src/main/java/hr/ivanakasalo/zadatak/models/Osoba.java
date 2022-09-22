package hr.ivanakasalo.zadatak.models;

import hr.ivanakasalo.zadatak.podaci.mapiranje.Podatak;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entitet Osoba
 */
@Entity
public class Osoba implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Podatak(naziv = "Ime")
    private String ime;
    @Podatak(naziv = "Prezime")
    private String prezime;
    @Podatak(naziv = "DatumRodjenja")
    private LocalDate datumRodjenja;

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

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
