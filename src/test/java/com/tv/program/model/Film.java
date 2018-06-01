package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Film extends Programme {

    private final List<Personne> guests;

    Film(Chaine chaine, String titre, String sous_titre, String type, Date dateDeDebut, Date dateDeFin, Duree duree, List<Personne> guests, String pays, String aspect, String qualite, String note) {
        super(chaine, titre, sous_titre, type, dateDeDebut, dateDeFin, duree, pays, aspect, qualite, note);
        this.guests = Collections.unmodifiableList(guests);
        setCredits(guests);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<Personne> getGuests() {
        return guests;
    }
}
