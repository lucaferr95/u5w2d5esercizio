package it.epicode.u5w2d5esercizio.Repository;


import it.epicode.u5w2d5esercizio.Entities.Postazione;
import it.epicode.u5w2d5esercizio.Entities.Prenotazione;
import it.epicode.u5w2d5esercizio.Entities.Utente;
import it.epicode.u5w2d5esercizio.Enumeration.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {
    //QUERY PER CERCARE LA POSTAZIONE CON TIPO POSTAZIONE E CITTA

  List<Postazione> findByTipoPostazioneAndEdificio_Citta(TipoPostazione tipoPostazione, String citta);

  List<Postazione> findByDescrizione(String descrizione);

}

