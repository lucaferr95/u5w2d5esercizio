package it.epicode.u5w2d5esercizio.Configuration;



import it.epicode.u5w2d5esercizio.Entities.Edificio;
import it.epicode.u5w2d5esercizio.Entities.Postazione;
import it.epicode.u5w2d5esercizio.Enumeration.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    //creo edifici coi Bean
    //Prima postazione/Piacenza
    @Bean("WorkSpace Piacenza")
    public Edificio getEdificioPC(){
        Edificio edificioPC= new Edificio();
        edificioPC.setCitta("Piacenza");
        edificioPC.setIndirizzo("Piazza Cavalli");
        edificioPC.setNome("Workspace Piacenza");
        //SETTO POSTAZIONE
        Postazione psPC= new Postazione();
        psPC.setTipoPostazione(TipoPostazione.OPENSPACE);
        psPC.setDescrizione("Spazio di lavoro a Piacenza");
        psPC.setMaxOccupanti(20);
        psPC.setEdificio(edificioPC);

        edificioPC.setPostazioni(List.of(psPC));
        return edificioPC;
    }

    //postazione 2 (Napoli)

    @Bean("Workspace Napoli")
    public Edificio getEdificioNA(){
        Edificio edificioNA= new Edificio();
        edificioNA.setCitta("Napoli");
        edificioNA.setIndirizzo("Piazza Garibaldi");
        edificioNA.setNome("Workspace Napoli");

        Postazione psNA= new Postazione();
        psNA.setTipoPostazione(TipoPostazione.PRIVATO);
        psNA.setEdificio(edificioNA);
        psNA.setMaxOccupanti(10);
        psNA.setDescrizione("Spazio di lavoro a Napoli");

        edificioNA.setPostazioni(List.of(psNA));
        return edificioNA;
    }

    //Edificio a 3
    @Bean("Workspace Amsterdam")
    public Edificio edificioAM(){
        Edificio edificioAM= new Edificio();
        edificioAM.setNome("Workspace Amsterdam");
        edificioAM.setIndirizzo("Via robba buona");
        edificioAM.setCitta("Amsterdam");

        Postazione psAM1= new Postazione();

        psAM1.setDescrizione("Una postazione da sballo");
        psAM1.setMaxOccupanti(100);
        psAM1.setEdificio(edificioAM);
        psAM1.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);

        Postazione psAM2= new Postazione();
        psAM2.setMaxOccupanti(30);
        psAM2.setDescrizione("Secondo Workspace a Amsterdam");
        psAM2.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
        psAM2.setEdificio(edificioAM);
        edificioAM.setPostazioni(List.of(psAM1, psAM2));
        return edificioAM;

    }
}
