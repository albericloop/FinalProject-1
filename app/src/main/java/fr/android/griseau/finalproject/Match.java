package fr.android.griseau.finalproject;

/**
 * Created by alberic on 29/03/2018.
 */
//PAS ENCORE OPERATIONNEL

public class Match {
    private long id;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatch() {
        return comment;
    }

    public void setMatch(String comment) {
        this.comment = comment;
    }

    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return comment;
    }
}