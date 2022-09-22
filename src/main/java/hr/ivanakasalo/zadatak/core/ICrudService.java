package hr.ivanakasalo.zadatak.core;

import java.util.List;

public interface ICrudService<T> {
    T spremi(T obj);
    T uredi(T obj, Long id);
    void obrisi(Long id);
    List<T> spremiSve(List<T> list);

}
