# Web aplikacija za obradu datoteka (Java)

Web aplikacija koja koristi reg

## Zahtjevi

- Java 17 SDK

## Upute

U application.properties podesiti aktivni profil `prod`.<br>
Aplikacija je dostupna na [localhost:9090](localhost:9090)

### IntelliJ IDEA

1. Otvoriti `ZadatakApplication`.
2. Kliknuti na run.

### Konzola

1. Pozicionirati se u konzoli u root direktorij.
2. Pokrenuti naredbu `./mvnw spring-boot:run`.

## Tehnologije

- Spring MVC

## Funkcionalnosti:

- Z1: Generiranje datoteke u .csv formatu sa stupcima: Ime, Prezime, DatumRodjenja
  - Omogućiti preuzimanje datoteke, odabir redoslijeda stupaca i broj redaka
- Z2: Omogućiti unos podataka u bazu

