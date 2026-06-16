package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "titolo_evento")
    private String titolo;

    @Column(name = "data_evento")
    private LocalDate data;

    @Column(name = "descrizione")
    private String desc;

    @Column(name = "tipo_evento")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "max_partecipanti")
    private Integer max;

    public Evento(){}

    public Evento (String titolo, LocalDate data, String desc, EventType eventType, Integer max){
        this.titolo = titolo;
        this.data = data;
        this.desc = desc;
        this.eventType = eventType;
        this.max = max;
    }

    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDesc() {
        return desc;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Integer getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", data=" + data +
                ", desc='" + desc + '\'' +
                ", eventType=" + eventType +
                ", max=" + max +
                '}';
    }
}
