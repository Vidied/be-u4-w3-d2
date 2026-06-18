package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class GaraDiAtletica  extends  Evento{

    @ManyToMany
    @JoinTable(
            name = "gara_atleti",
            joinColumns = @JoinColumn(name = "gara_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )

    private Set<Persona> atleti;

    @ManyToOne
    private Persona vincitore;

    public GaraDiAtletica(){}

    public GaraDiAtletica(String titolo, LocalDate data, String desc, EventType eventType, Integer max, Location location, Set<Persona> atleti, Persona vincitore) {
        super(titolo, data, desc, eventType, max, location);
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public Set<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }
}
