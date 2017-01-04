package eu.telm.models;

/**
 * Created by kasia on 10.12.16.
 */
public class SlownikOperacji {
    private String nazwa;
    private String opis;
    private Typ typ;
    private enum Typ{BADANIE, ZABIEG};

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

    public Typ getTyp(){
        return typ;
    }

    public void setTyp(String typ) {
        if(typ == "BADANIE")
        this.typ = Typ.BADANIE;
        else
            this.typ = Typ.ZABIEG;
    }
}
