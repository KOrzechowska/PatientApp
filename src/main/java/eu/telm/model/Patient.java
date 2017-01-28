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
    @Size(max=20)
    private String imie;
    @Column
    @NotNull
    @Size(max=20)
    private String nazwisko;
    @Column
    @Size(max=11)
    private String pesel;
    @Column
    @NotNull
    private Date dataUr;
    @Column
    @NotNull
    @Size(max=20)
    private String plec;
    @Column
    @Size(max=20)
    private String nrTel;
    @Column
    @Size(max=50)
    private String email;
    @Column
    @Size(max = 6)
    private String kodPocztowy;
    @Column
    @Size(max=30)
    private String miasto;
    @Column
    @Size(max=30)
    private String ulica;
    @Column
    @Size(max=10)
    private String nrDomu;
    @Column
    private boolean czyUbezp;

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

    public Date getDataUr() {
        return dataUr;
    }

    public void setDataUr(Date dataUr) {
        this.dataUr = dataUr;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
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

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public boolean isCzyUbezp() {
        return czyUbezp;
    }

    public void setCzyUbezp(boolean czyUbezp) {
        this.czyUbezp = czyUbezp;
    }


}
