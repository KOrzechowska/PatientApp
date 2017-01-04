package eu.telm.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasia on 10.12.16.
 */
public class Pacjent {

    private String imie;
    private String nazwisko;
    private List<RealizacjaBadania> realizacjaBadaniaList;

    public Pacjent(){
        realizacjaBadaniaList = new ArrayList<>();
    }

    public void addBadanie(RealizacjaBadania badania){
        realizacjaBadaniaList.add(badania);
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

    public List<RealizacjaBadania> getRealizacjaBadaniaList() {
        return realizacjaBadaniaList;
    }

    public void setRealizacjaBadaniaList(List<RealizacjaBadania> realizacjaBadaniaList) {
        this.realizacjaBadaniaList = realizacjaBadaniaList;
    }
}
