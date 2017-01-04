package eu.telm.models;

import java.util.Date;

/**
 * Created by kasia on 10.12.16.
 */
public class RealizacjaBadania {

    private Date date;
    private SlownikOperacji operacja;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SlownikOperacji getOperacja() {
        return operacja;
    }

    public void setOperacja(SlownikOperacji operacja) {
        this.operacja = operacja;
    }
}
