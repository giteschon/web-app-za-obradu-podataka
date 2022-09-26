package hr.ivanakasalo.zadatak.podaci.mapiranje;

import hr.ivanakasalo.zadatak.podaci.exceptions.MapiranjeException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvMapiranje<T> {
    public static final String DELIMITER = ";";

    private final Class<T> clazz;

    public CsvMapiranje(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @param list
     * @return
     */
    public List<T> pripremiPodatke(List<String> list) {
        String[] nazivi = pripremiNazive(list.get(0).split(DELIMITER));
        List<T> listT = new ArrayList<>();
        String[] data;

        for (int i = 1; i < list.size(); i++) {
            data = list.get(i).split(DELIMITER);
            listT.add(get(data, nazivi));
        }

        return listT;
    }

    /**
     * Postavljanje objekta podacima iz datoeke
     *
     * @param data   - jedan redak u CSV datoteci, odvojen delimiterom i razdvojen u polje
     * @param nazivi - nazivi polja u koje je potrebno sprmiti podatak za objekt
     * @return napunjeni objekt
     */
    private T get(String[] data, String[] nazivi) {
        try {
            T o = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0; i < nazivi.length; i++) {
                Field f = clazz.getDeclaredField(nazivi[i]);
                f.setAccessible(true);
                if (f.getType() == String.class) {
                    f.set(o, data[i]);
                } else if (f.getType() == LocalDate.class) {
                    f.set(o, LocalDate.parse(data[i], DateTimeFormatter.ISO_DATE));
                }
            }

            return o;
        } catch (Exception e) {
            throw new MapiranjeException("Greška prilikom mapiranja polja.", e);
        }
    }

    /**
     * Usklađivanje naziva iz datoteke s nazivima fieldova.
     *
     * @param nazivi - učitani nazivi iz datoteke
     * @return
     */
    private String[] pripremiNazive(String[] nazivi) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Podatak.class)) {
                Podatak annotation = field.getDeclaredAnnotation(Podatak.class);
                for (int i = 0; i < nazivi.length; i++) {
                    if (nazivi[i].equals(annotation.naziv())) {
                        nazivi[i] = field.getName();
                        break;
                    }
                }
            }
        }

        return nazivi;
    }
}
