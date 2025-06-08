package it.epicode.u5w2d5esercizio.Repository;


import it.epicode.u5w2d5esercizio.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository <Utente, Integer> {
    boolean existsByUsername(String username);

    //metodo per trovare l' utente tramite username dopo che si è registrato

    Optional<Utente> findByUsernameAndPassword(String username, String password);

}
