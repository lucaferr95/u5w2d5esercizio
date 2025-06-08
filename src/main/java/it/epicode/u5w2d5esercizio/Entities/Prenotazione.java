package it.epicode.u5w2d5esercizio.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data

public class Prenotazione {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name="postazione_id")
    private Postazione postazione;

    public LocalDate getScadenzaPrenotazione() {
        return this.dataPrenotazione != null ? this.dataPrenotazione.plusDays(1) : null;
    }
    @Override
    public String toString() {
        return "Prenotazione confermata per la data " + dataPrenotazione +
                " (scadenza: " + getScadenzaPrenotazione() + ")"+
                " per la postazione: " + (postazione != null ? postazione.getDescrizione() : "non disponibile");
    }
}
