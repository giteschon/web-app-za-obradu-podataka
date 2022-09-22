package hr.ivanakasalo.zadatak.core;

import hr.ivanakasalo.zadatak.repositories.OsobaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractCrudService<T> implements  ICrudService<T>{

   protected JpaRepository<T, Long> repository;

    public AbstractCrudService(JpaRepository<T,Long> repository) {
        this.repository=repository;
    }

    @Override
    public T spremi(T obj) {
        return repository.save(obj);
    }

    @Override
    public T uredi(T obj, Long id) {
         if(repository.existsById(id)){
             return repository.save(obj);
         }
         throw new RuntimeException("Zapis ne postoji.");
    }

    @Override
    public void obrisi(Long id) {
repository.deleteById(id);
    }

    @Override
    public List<T> spremiSve(List<T> list) {
        return  repository.saveAll(list);
    }
}
