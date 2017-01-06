package eu.telm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by kasia on 13.11.16.
 */
@Entity
@Table(name = "slownik_operacji")
public class Operacja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operacja_id", unique = true, nullable = false)
    private Long id;
    @Column
    @NotNull
    private String nazwa;
    @Column
    private String opis;
    public enum typ{BADANIE, ZABIEG};
    @Column
    @Enumerated(EnumType.STRING)
    private typ typ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operacja")
    private List<Realizacje> realizacjeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Realizacje> getRealizacjeList() {
        return realizacjeList;
    }

    public void setRealizacjeList(List<Realizacje> realizacjeList) {
        this.realizacjeList = realizacjeList;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Operacja.typ getTyp() {
        return typ;
    }

    public void setTyp(Operacja.typ typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "OPERACJA\t"+nazwa+"\t"+opis+"\t"+typ;
    }
}
