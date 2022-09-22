package hr.ivanakasalo.zadatak.repositories;

import hr.ivanakasalo.zadatak.models.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {
}
