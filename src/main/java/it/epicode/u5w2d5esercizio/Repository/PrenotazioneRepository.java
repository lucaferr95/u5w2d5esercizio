package it.epicode.u5w2d5esercizio.Repository;

import it.epicode.u5w2d5esercizio.Entities.Postazione;
import it.epicode.u5w2d5esercizio.Entities.Prenotazione;
import it.epicode.u5w2d5esercizio.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    List<Prenotazione> findByUtente(Utente utente);

    // Metodo per controllare se esiste già una prenotazione per la stessa postazione e data
    List<Prenotazione> findByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);
}
