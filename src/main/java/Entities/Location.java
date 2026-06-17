package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private UUID id;

    private String nome;

    private String citta;

    public Location(){};

    public Location(String nome, String citta){
        this.nome = nome;
        this.citta = citta;
    }

    public String getCitta() {
        return citta;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
