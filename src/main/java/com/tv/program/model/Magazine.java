package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Magazine extends Programme {

    private final List<Personne> presenters;

    Magazine(Chaine chaine, String titre, String sous_titre, String type, Date dateDeDebut, Date dateDeFin, Duree duree, List<Personne> presenters, String pays, String aspect, String qualite, String note) {
        super(chaine, titre, sous_titre, type, dateDeDebut, dateDeFin, duree, pays, aspect, qualite, note);
        this.presenters = Collections.unmodifiableList(presenters);
        setCredits(presenters);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<Personne> getPresenters() {
        return presenters;
    }
}
