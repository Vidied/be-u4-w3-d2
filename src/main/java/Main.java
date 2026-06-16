import Entities.EventType;
import Entities.Evento;
import dao.EventoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4w3d2");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        EventoDao eventoDao = new EventoDao(entityManager);

        Evento saggio = new Evento("Saggio di danza", LocalDate.of(2025, 7, 12), "Saggio di danza della spettacolare Effie", EventType.PUBBLICO, 200);
        Evento compleanno = new Evento("Compleanno Giangiorgio", LocalDate.of(2000,10,10), "Compleanno del grandissimo Giangiorgio", EventType.PRIVATO, 20);
        Evento concerto = new Evento("Concerto boo", LocalDate.of(3000, 10,1), "Concerto misterioso nell'anno 3000", EventType.PUBBLICO, 100000);

        

    }
}
