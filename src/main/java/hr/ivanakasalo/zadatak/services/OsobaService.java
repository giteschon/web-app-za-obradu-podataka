package hr.ivanakasalo.zadatak.services;

import hr.ivanakasalo.zadatak.core.AbstractCrudService;
import hr.ivanakasalo.zadatak.models.Osoba;
import hr.ivanakasalo.zadatak.podaci.mapiranje.CsvMapiranje;
import hr.ivanakasalo.zadatak.repositories.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OsobaService extends AbstractCrudService<Osoba> {

    public OsobaService(@Autowired OsobaRepository repository) {
        super(repository);
    }

    @Override
    public List<Osoba> dohvatiSve() {
        return repository.findAll(Sort.by("prezime", "ime"));
    }

    /**
     * Čitanje csv datoteke, mapiranje polja i spremanje u bazu.
     *
     * @param file
     */
    public void spremiOsobe(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            List<String> list = reader.lines().collect(Collectors.toCollection(ArrayList::new));
            if (!list.isEmpty()) {
                CsvMapiranje<Osoba> csvMapiranje = new CsvMapiranje<>(Osoba.class);
                List<Osoba> osobe = csvMapiranje.pripremiPodatke(list);
                spremiSve(osobe);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
