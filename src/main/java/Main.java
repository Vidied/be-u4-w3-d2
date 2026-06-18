import Entities.*;
import dao.EventoDao;
import dao.LocationDao;
import dao.PartecipazioneDao;
import dao.PersonaDao;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4w3d2");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        LocationDao locationDao = new LocationDao(entityManager);
        PersonaDao personaDao = new PersonaDao(entityManager);
        EventoDao eventoDao = new EventoDao(entityManager);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(entityManager);

        Location loc1 = new Location("Teatro San Carlo", "Napoli");
        Location locStadium = new Location("Allianz Stadium", "Torino");
        Location locArena = new Location("Arena Civica", "Milano");

        locationDao.save(loc1);
        locationDao.save(locStadium);
        locationDao.save(locArena);


        Persona utente = new Persona("Giangiorgio", "Gio", "giangio@email.com", LocalDate.of(1998, 4, 12), Sesso.M);
        Persona atleta1 = new Persona("Mario", "Rossi", "mario.rossi@email.com", LocalDate.of(1995, 5, 12), Sesso.M);
        Persona atleta2 = new Persona("Luigi", "Verdi", "luigi.verdi@email.com", LocalDate.of(1997, 8, 24), Sesso.M);
        Persona atleta3 = new Persona("Anna", "Bianchi", "anna.bianchi@email.com", LocalDate.of(1996, 3, 15), Sesso.F);

        personaDao.save(utente);
        personaDao.save(atleta1);
        personaDao.save(atleta2);
        personaDao.save(atleta3);


        Concerto concertoRock = new Concerto(
                "Concerto Live dei J-Rocks",
                LocalDate.of(2026, 7, 15),
                "Il grande ritorno della band sul palco dell'Arena!",
                EventType.PUBBLICO,
                5000,
                locArena,
                GenereConcerto.ROCK,
                true
        );
        eventoDao.save(concertoRock);

        PartitaDiCalcio partita1 = new PartitaDiCalcio(
                "Juventus vs Milan", LocalDate.of(2026, 10, 5), "Big match di campionato",
                EventType.PUBBLICO, 40000, locStadium, "Juventus", "Milan", "Juventus", 2, 1
        );
        eventoDao.save(partita1);

        PartitaDiCalcio partita2 = new PartitaDiCalcio(
                "Torino vs Napoli", LocalDate.of(2026, 11, 12), "Anticipo serale",
                EventType.PUBBLICO, 25000, locStadium, "Torino", "Napoli", "Napoli", 0, 2
        );
        eventoDao.save(partita2);

        Set<Persona> partecipanti = new HashSet<>();
        partecipanti.add(atleta1);
        partecipanti.add(atleta2);
        partecipanti.add(atleta3);

        GaraDiAtletica gara100m = new GaraDiAtletica(
                "Meeting Internazionale 100m", LocalDate.of(2026, 8, 20), "Gara di velocità",
                EventType.PUBBLICO, 15000, locArena, partecipanti, atleta1
        );
        eventoDao.save(gara100m);

        Evento saggio = new Evento(
                "Saggio di danza",
                LocalDate.of(2025, 7, 12),
                "Saggio di danza della spettacolare Effie",
                EventType.PUBBLICO, 200, loc1);

        Evento compleanno = new Evento(
                "Compleanno Giangiorgio",
                LocalDate.of(2000,10,10),
                "Compleanno del grandissimo Giangiorgio",
                EventType.PRIVATO, 20, loc1);

        Evento concerto = new Evento(
                "Concerto boo",
                LocalDate.of(3000, 10,1),
                "Concerto misterioso nell'anno 3000",
                EventType.PUBBLICO, 100000, loc1);

        eventoDao.save(saggio);
        eventoDao.save(compleanno);
        eventoDao.save(concerto);

        Partecipazione iscrizione = new Partecipazione(utente, saggio, StatoPartecipazione.CONFERMATA);

        partecipazioneDao.save(iscrizione);

        try {
            UUID idCercato = saggio.getId();
            Evento found = eventoDao.findById(idCercato);
            System.out.println(found);

            UUID idCercato2 = UUID.randomUUID();
            Evento foundErrore = eventoDao.findById(idCercato2);
            System.out.println(foundErrore);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            UUID idCercato3 = concerto.getId();
            eventoDao.findByIdAndDelete(idCercato3);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}
