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
import java.util.UUID;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4w3d2");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        LocationDao locationDao = new LocationDao(entityManager);
        PersonaDao personaDao = new PersonaDao(entityManager);
        EventoDao eventoDao = new EventoDao(entityManager);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(entityManager);

        Location locale = new Location("Teatro San Carlo", "Napoli");


        Persona utente = new Persona("Giangiorgio", "Gio", "giangio@email.com", LocalDate.of(1998, 4, 12), Sesso.M);



        Evento saggio = new Evento(
                "Saggio di danza",
                LocalDate.of(2025, 7, 12),
                "Saggio di danza della spettacolare Effie",
                EventType.PUBBLICO, 200, locale);

        Evento compleanno = new Evento(
                "Compleanno Giangiorgio",
                LocalDate.of(2000,10,10),
                "Compleanno del grandissimo Giangiorgio",
                EventType.PRIVATO, 20, locale);

        Evento concerto = new Evento(
                "Concerto boo",
                LocalDate.of(3000, 10,1),
                "Concerto misterioso nell'anno 3000",
                EventType.PUBBLICO, 100000, locale);

        Partecipazione iscrizione = new Partecipazione(utente, saggio, StatoPartecipazione.CONFERMATA);

        locationDao.save(locale);
        personaDao.save(utente);
        eventoDao.save(saggio);
        eventoDao.save(compleanno);
        eventoDao.save(concerto);
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
