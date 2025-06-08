package it.epicode.u5w2d5esercizio.Entities;


import it.epicode.u5w2d5esercizio.Enumeration.TipoPostazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "edificio")

public class Postazione {
    @Id
    @GeneratedValue
    private int codiceUnivoco;
    private String descrizione;
    private TipoPostazione tipoPostazione;
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    @ToString.Exclude
    private List<Prenotazione> prenotazioni;



}
