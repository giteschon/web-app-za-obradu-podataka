package hr.ivanakasalo.zadatak.podaci.generiranje;

import hr.ivanakasalo.zadatak.models.Osoba;
import hr.ivanakasalo.zadatak.podaci.mapiranje.CsvMapiranje;
import hr.ivanakasalo.zadatak.podaci.mapiranje.Podatak;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Pomoćna klasa za generiranje datoteke s podacima Ime, Prezime i DatumRodjenja.
 * Imena i prezimena se nasumično generiraju iz unaprijed definiranih listi.
 * DatumRodjenja se generira nasumično u periodu od 1960 do trenutne godine.
 */
public class GeneriranjeOsoba {

    private static final String[] imena = {"Katarina",
            "Iskra", "Maša", "Ivan", "Nikola", "Matej", "Isak", "Matija", "Marta",
            "Jelena", "Lea", "Nina", "Milan", "Maksim", "Vanja", "Darija", "Aleksej", "Anastasija", "Magdalena",
            "Aleksandar", "Tea", "Milica", "Marijana", "Klara", "Jan", "Andrija", "Borna", "Dora", "Arian", "Damjan"};

    private static final String[] prezimena = {"Jukić",
            "Topić", "Bašić", "Jurić", "Delić", "Bilić", "Šimić", "Muslim", "Grubišić", "Filipović", "Murtić",
            "Zelenbaba", "Ujdur", "Grgurić", "Bagarić", "Jurčević", "Vergić", "Ibrahimbegović", "Kvočić", "Ljubas"};

    private final Random random;
    private final int trenutnaGodina;

    public GeneriranjeOsoba() {
        this.random = new Random();
        trenutnaGodina = LocalDate.now().getYear();
    }

    public ByteArrayOutputStream generiraj(int brojRedaka) {
        StringBuilder sb = new StringBuilder();
        List<String> nazivi = dodajNazive(sb);

        int index = 0;
        while (index < brojRedaka) {
            dodajOsobu(sb, nazivi);
            index++;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> dodajNazive(StringBuilder sb) {
        List<String> nazivi = new ArrayList<>();
        Class<?> clazz = Osoba.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(f -> {
            if (f.isAnnotationPresent(Podatak.class)) {
                nazivi.add(f.getDeclaredAnnotation(Podatak.class).naziv());
            }
        });

        Collections.shuffle(nazivi);

        for (String naziv : nazivi) {
            sb.append(naziv).append(CsvMapiranje.DELIMITER);
        }
        sb.append("\n");

        return nazivi;
    }

    private void dodajOsobu(StringBuilder sb, List<String> nazivi) {
        for (String n : nazivi) {
            switch (n) {
                case "Ime":
                    sb.append(generirajIme(imena));
                    break;
                case "Prezime":
                    sb.append(generirajIme(prezimena));
                    break;
                case "DatumRodjenja":
                    sb.append(generirajDatum());
            }
            sb.append(CsvMapiranje.DELIMITER);
        }
        sb.append("\n");
    }

    /**
     * Generiranje datuma u ISO formatu (yyyy-MM-dd)
     *
     * @return
     */
    private String generirajDatum() {
        int godina = random.nextInt(1960, trenutnaGodina);
        int mjesec = random.nextInt(1, 12);
        Month m = Month.of(mjesec);
        int dan = random.nextInt(1, m.maxLength());

        return String.format("%d-%02d-%02d", godina, mjesec, dan);
    }

    private String generirajIme(String[] imena) {
        return imena[random.nextInt(imena.length)];
    }
}
