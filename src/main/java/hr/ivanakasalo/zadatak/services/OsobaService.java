package hr.ivanakasalo.zadatak.services;

import hr.ivanakasalo.zadatak.core.AbstractCrudService;
import hr.ivanakasalo.zadatak.models.Osoba;
import hr.ivanakasalo.zadatak.repositories.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsobaService extends AbstractCrudService<Osoba> {


    public OsobaService(@Autowired OsobaRepository repository) {
        super(repository);
    }

    public void spremi() {
      /*  Osoba osoba=new Osoba();
        osoba.setIme("Ivan");
        osoba.setPrezime("Anić");
        osoba.setDatumRodjenja(LocalDate.now());
        repository.save(osoba);*/
    }
}
