package it.epicode.u5w2d5esercizio.Runner;


import it.epicode.u5w2d5esercizio.Entities.Edificio;
import it.epicode.u5w2d5esercizio.Entities.Postazione;
import it.epicode.u5w2d5esercizio.Entities.Prenotazione;
import it.epicode.u5w2d5esercizio.Entities.Utente;
import it.epicode.u5w2d5esercizio.Enumeration.TipoPostazione;
import it.epicode.u5w2d5esercizio.Exceptions.UsernameEsistenteException;
import it.epicode.u5w2d5esercizio.Repository.EdificioRepository;
import it.epicode.u5w2d5esercizio.Repository.PostazioneRepository;
import it.epicode.u5w2d5esercizio.Repository.PrenotazioneRepository;
import it.epicode.u5w2d5esercizio.Repository.UtenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component



public class RunnerQuery implements CommandLineRunner {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    PostazioneRepository postazioneRepository;

    @Autowired
    EdificioRepository edificioRepository;

    @Autowired
    private ApplicationContext context;


    @Override
    public void run(String... args) throws Exception {
        // Recupero gli edifici dal contesto Spring
        List<String> edificiBeans = Arrays.asList("WorkSpace Piacenza", "Workspace Napoli", "Workspace Amsterdam");

        for (String beanName : edificiBeans) {
            try {
                Edificio edificio = context.getBean(beanName, Edificio.class);

                // Verifica se l'edificio esiste già nel database
                List<Edificio> edificioEsistente = edificioRepository.findByNome(edificio.getNome());
                if (edificioEsistente.isEmpty()) {
                    edificioRepository.save(edificio);

                    // Salva le postazioni solo se non già esistenti
                    for (Postazione postazione : edificio.getPostazioni()) {
                        List<Postazione> postazioneEsistente = postazioneRepository.findByDescrizione(postazione.getDescrizione());
                        if (postazioneEsistente.isEmpty()) {
                            postazione.setEdificio(edificio);
                            postazioneRepository.save(postazione);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Errore nel recupero del bean: " + beanName + " - " + e.getMessage());
            }
        }

        Scanner scanner = new Scanner(System.in);

        //creo un menu di registrazione per creare utenti con nome, email e password

        int scelta = -1;
        while (scelta != 0) {
            try {
                System.out.println("""
                        ╔════════════════════╗
                        ║   💻 GESTIONE   💻
                              PRENOTAZIONI            ║
                        ║       By Luca 🐢💪  ║
                        ╚════════════════════╝
                        """);
                System.out.println("**MENU LOGIN**");
                System.out.println("Welcome user, are you ready for cadere malato?!");
                System.out.println("1 - Esegui il Login");
                System.out.println("2 - Registrazione");
                System.out.println("0 - Esci");


                scelta = Integer.parseInt(scanner.nextLine());
                switch (scelta) {
                    case 1 -> {
                        try {
                            System.out.println("Inserisci l'username: ");
                            String username = scanner.nextLine();
                            System.out.println("Inserisci la password");
                            String password = scanner.nextLine();

                            Optional<Utente> utenteOpt = utenteRepository.findByUsernameAndPassword(username, password);
                            if (utenteOpt.isPresent()) {
                                Utente utenteLoggato = utenteOpt.get();
                                System.out.println("Login effettuato con successo!");
                                        // dopo login si passa al menuprenotazioni

                                        menuPrenotazione(utenteLoggato);

                            } else {
                                System.out.println("Credenziali errate");
                            }


                        } catch (Exception e) {
                            System.out.println(e.getMessage());

                        }

                    }

                    case 2 -> {

                        try {

                            System.out.println("Nome Completo: ");
                            String nome = scanner.nextLine();
                            System.out.println("Inserisci l'username: ");
                            String username = scanner.nextLine();
                            System.out.println("Inserisci un'email valida");
                            String email = scanner.nextLine();
                            System.out.println("Inserisci una password");
                            String password = scanner.nextLine();
                            System.out.println("Registrazione completata!");
                            if (utenteRepository.existsByUsername(username)) {
                                throw new UsernameEsistenteException("Username già in uso.");
                            }
                            Utente nuovoUtente = new Utente();
                            nuovoUtente.setNomeCompleto(nome);
                            nuovoUtente.setUsername(username);
                            nuovoUtente.setEmail(email);
                            nuovoUtente.setPassword(password);
                            utenteRepository.save(nuovoUtente);



                        } catch (UsernameEsistenteException e) {
                            System.out.println("❌ Errore: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("❌ Errore durante la creazione dell'utente: " + e.getMessage());
                        }
                    }

                    case 0 -> System.out.println("Alla prossima!");
                    default -> System.out.println("scelta non valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Inserisci un numero valido.");
            }
        }
    }

    public void menuPrenotazione(Utente utente) {
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {

            System.out.println("\n--- MENU PRENOTAZIONE ---");
            System.out.println("1) Prenota una postazione per nome edificio e città");
            System.out.println("2) Visualizza le tue prenotazioni");
            System.out.println("3) Ricerca postazioni per tipo e città");
            System.out.println("0) Logout");
            System.out.print("Scelta: ");

            String scelta1 = scanner.nextLine();

            switch (scelta1) {
                case "1" -> {
                    try {
                        // Mostro subito le postazioni disponibili tr a cui scegliere
                        System.out.println("\n **POSTAZIONI DISPONIBILI** ");
                        List<Postazione> tuttePostazioni = postazioneRepository.findAll();
                        if (!tuttePostazioni.isEmpty()) {
                            for (int i = 0; i < tuttePostazioni.size(); i++) {
                                System.out.println((i + 1) + ") " + tuttePostazioni.get(i));
                            }
                        } else {
                            System.out.println("⚠️ Nessuna postazione disponibile.");
                            break;
                        }
                        System.out.print("Inserisci il numero della postazione da prenotare: ");
                        int sceltaPostazione = Integer.parseInt(scanner.nextLine());

                        if (sceltaPostazione < 1 || sceltaPostazione > tuttePostazioni.size()) {
                            System.out.println("❌ Scelta non valida.");
                            break;
                        }

                        Postazione sceltaPos = tuttePostazioni.get(sceltaPostazione - 1);

                        System.out.print("Inserisci data di prenotazione (es: 2025-06-10): ");
                        LocalDate data = LocalDate.parse(scanner.nextLine());

                        // Controlla se la postazione è già prenotata per la data scelta
                        List<Prenotazione> prenotazioniEsistenti =
                                prenotazioneRepository.findByPostazioneAndDataPrenotazione(sceltaPos, data);

                        if (!prenotazioniEsistenti.isEmpty()) {
                            System.out.println("❌ Questa postazione è già prenotata per la data selezionata.");
                            break;
                        }

                        // Creazione e salvataggio della prenotazione
                        Prenotazione prenotazione = new Prenotazione();
                        prenotazione.setUtente(utente);
                        prenotazione.setDataPrenotazione(data);
                        prenotazione.setPostazione(sceltaPos);
                        prenotazioneRepository.save(prenotazione);

                        System.out.println("✅ Prenotazione completata con successo!");
                        System.out.println(prenotazione);

                    } catch (Exception e) {
                        System.out.println("❌ Errore durante la prenotazione: " + e.getMessage());
                    }
                }


                case "2" -> {
                    List <Prenotazione> prenotazioni= prenotazioneRepository.findByUtente(utente);
                    if (prenotazioni.isEmpty()) {
                        System.out.println("❌ Nessuna prenotazione trovata.");
                    } else {
                        prenotazioni.forEach(System.out::println);
                    }
                }
                case "3" -> {
                    System.out.println("Inserisci il tipo della postazione");
                    String tipoPostazione= scanner.nextLine().toUpperCase();
                    System.out.println("Inserisci città della postazione");
                    String citta= scanner.nextLine();
                    List<Postazione> postazioniFiltrate = postazioneRepository.findByTipoPostazioneAndEdificio_Citta(TipoPostazione.valueOf(tipoPostazione), citta);
                    if (postazioniFiltrate.isEmpty()) {
                        System.out.println("❌ Nessuna postazione trovata con i criteri selezionati.");
                    } else {
                        System.out.println("\n**POSTAZIONI DISPONIBILI PER " + tipoPostazione + " IN " + citta.toUpperCase() + "** ");
                        for (int i = 0; i < postazioniFiltrate.size(); i++) {
                            System.out.println((i + 1) + ") " + postazioniFiltrate.get(i));
                        }
                    }
                }
                case "0" -> {
                    System.out.println("Logout eseguito, statt bbuon.");
                    continua = false;
                }

                default -> System.out.println("❌ Scelta non valida.");
            }
        }
    }
}