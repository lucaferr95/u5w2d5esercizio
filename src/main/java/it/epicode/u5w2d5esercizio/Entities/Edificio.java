package it.epicode.u5w2d5esercizio.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Edificio {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String indirizzo;

    @Column(nullable = false)
    private String citta;

    @OneToMany(mappedBy = "edificio")
    @ToString.Exclude
    private List<Postazione> postazioni;


}
