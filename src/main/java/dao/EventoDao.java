package dao;

import Entities.Evento;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventoDao {

    private final EntityManager em;


    public EventoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(evento);

        transaction.commit();

        System.out.println("L'evento: " + evento + "è stato salvato nel DB");
    }

    public Evento findById(long id) {

        Evento fromDB = em.find(Evento.class, id);
        if(fromDB == null) throw new NotFoundException(id);
        return fromDB;
    }

    public void findByIdAndDelete(long id){

        Evento fromDB = findById(id);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(fromDB);

        transaction.commit();

        System.out.println("L'evento: " + fromDB + "è stato rimosso con successo");


    }
}
