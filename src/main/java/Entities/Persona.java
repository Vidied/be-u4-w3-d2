package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Persona {


    @Id
    @GeneratedValue
    @Column(name = "persona_id")
    private UUID id;

    private String nome;
    private String cognome;
    private String email;
    @Column(name = "data_di_nascita")
    private LocalDate dataNascita;
    @Enumerated(EnumType.STRING)
    private Sesso sesso;
    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    private List<Partecipazione> partecipazioneList = new ArrayList<>();

    public Persona(){};

    public Persona(String nome, String cognome, String email, LocalDate dataNascita, Sesso sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.sesso = sesso;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public List<Partecipazione> getPartecipazioneList() {
        return partecipazioneList;
    }

    public void setPartecipazioneList(List<Partecipazione> partecipazioneList) {
        this.partecipazioneList = partecipazioneList;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascita=" + dataNascita +
                ", sesso=" + sesso +
                
                '}';
    }
}
