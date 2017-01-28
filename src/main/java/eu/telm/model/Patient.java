package eu.telm.model;

import eu.telm.view.SimpleLoginView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 12.11.16.
 */
@Entity
@Table(name = "pacjenci")
public class Patient  implements IAuditLog{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id", unique = true, nullable = false)
    private Long id;

    @Column
    @NotNull
    private String imie;
    @Column
    @NotNull
    private String nazwisko;
    @Column
    private String pesel;
    @Column
    @NotNull
    private Date data_ur;
    @Column
    @NotNull
    private String plec;
    @Column
    private String nr_tel;
    @Column
    private String email;
    @Column
    private String kod_pocztowy;
    @Column
    private String miasto;
    @Column
    private String ulica;
    @Column
    private String nr_domu;
    @Column
    private boolean czy_ubezp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Realizacje> realizacjeList;


    public Long getId() {
        return id;
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
                .append(" Nazwisko : ").append(nazwisko)
                .append(" UserName Name : ").append(SimpleLoginView.currentUser.getUsername());

        return sb.toString();
    }
    @Transient
    @Override
    public String getCreatedBy() {
        return SimpleLoginView.currentUser.getUsername();
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

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getData_ur() {
        return data_ur;
    }

    public void setData_ur(Date data_ur) {
        this.data_ur = data_ur;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(String nr_domu) {
        this.nr_domu = nr_domu;
    }

    public boolean isCzy_ubezp() {
        return czy_ubezp;
    }

    public void setCzy_ubezp(boolean czy_ubezp) {
        this.czy_ubezp = czy_ubezp;
    }


}
