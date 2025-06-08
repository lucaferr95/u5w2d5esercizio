package it.epicode.u5w2d5esercizio.Repository;

import it.epicode.u5w2d5esercizio.Entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    List<Edificio> findByNome(String nome);

}
