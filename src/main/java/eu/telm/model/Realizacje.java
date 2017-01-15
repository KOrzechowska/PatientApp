package eu.telm.model;

import eu.telm.view.SimpleLoginView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by kasia on 13.11.16.
 */
@Entity
@Table(name = "realizacje")
public class Realizacje implements IAuditLog{

    @Id
    @Column(name = "realizacje_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    private Date data;
    @Column
    private String wynik;
    @Column
    private String uwagi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable=true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="operacja_id", nullable=true)
    private Operacja operacja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getWynik() {
        return wynik;
    }

    public void setWynik(String wynik) {
        this.wynik = wynik;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Operacja getOperacja() {
        return operacja;
    }

    public void setOperacja(Operacja operacja) {
        this.operacja = operacja;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Transient
    @Override
    public Long getIdToLog() {
        return getId();
    }

    @Transient
    @Override
    public String getLogDeatil(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id : ").append(getId())
                .append(" Id Pacjenta : ").append(getPatient().getId());

        return sb.toString();
    }
    @Transient
    @Override
    public String getCreatedBy() {
        return SimpleLoginView.currentUser.getUsername();
    }
}
