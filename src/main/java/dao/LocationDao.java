package dao;

import Entities.Location;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class LocationDao {

    private final EntityManager em;

    public LocationDao(EntityManager em) {
        this.em = em;
    }

    public void save(Location location){
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(location);

        transaction.commit();

        System.out.println("La location: " + location + " è stato salvato nel DB");
    }

    public Location findById(UUID id) {

        Location fromDB = em.find(Location.class, id);
        if(fromDB == null) throw new NotFoundException(id);
        return fromDB;
    }

    public void findByIdAndDelete(UUID id){

        Location fromDB = findById(id);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(fromDB);

        transaction.commit();

        System.out.println("La location: " + fromDB + " è stato rimosso con successo");


    }
}

