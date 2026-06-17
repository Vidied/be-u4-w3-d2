package dao;

import Entities.Persona;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.UUID;

public class PersonaDao {

    private final EntityManager em;

    public PersonaDao(EntityManager em) {
        this.em = em;
    }

    public void save(Persona persona){
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(persona);

        transaction.commit();

        System.out.println("La persona: " + persona + " è stato salvato nel DB");
    }

    public Persona findById(UUID id) {

        Persona fromDB = em.find(Persona.class, id);
        if(fromDB == null) throw new NotFoundException(id);
        return fromDB;
    }

    public void findByIdAndDelete(UUID id){

        Persona fromDB = findById(id);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(fromDB);

        transaction.commit();

        System.out.println("La persona: " + fromDB + " è stato rimosso con successo");
    }
}