package dao;

import Entities.Partecipazione;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.UUID;

public class PartecipazioneDao {

    private final EntityManager em;

    public PartecipazioneDao(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione partecipazione){
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(partecipazione);

        transaction.commit();

        System.out.println("La partecipazione: " + partecipazione + " è stato salvato nel DB");
    }

    public Partecipazione findById(UUID id) {

        Partecipazione fromDB = em.find(Partecipazione.class, id);
        if(fromDB == null) throw new NotFoundException(id);
        return fromDB;
    }

    public void findByIdAndDelete(UUID id){

        Partecipazione fromDB = findById(id);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(fromDB);

        transaction.commit();

        System.out.println("La partecipazione: " + fromDB + " è stato rimosso con successo");
    }
}