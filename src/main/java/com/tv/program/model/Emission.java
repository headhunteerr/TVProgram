package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Emission extends Programme {
    private final List<Personne> guests, writers;

    Emission(Chaine chaine, String titre, String sous_titre, String type, Date dateDeDebut, Date dateDeFin, Duree duree, List<Personne> guests, List<Personne> writers, String pays, String aspect, String qualite, String note) {
        super(chaine, titre, sous_titre, type, dateDeDebut, dateDeFin, duree, pays, aspect, qualite, note);
        this.guests = Collections.unmodifiableList(guests);
        this.writers = Collections.unmodifiableList(writers);
    }

    public String toString() {
        //TODO
        return null;
    }

    public List<Personne> getGuests() {
        return guests;
    }

    public List<Personne> getWriters() {
        return writers;
    }
}
