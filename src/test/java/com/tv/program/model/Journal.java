package com.tv.program.model;

import java.util.Date;
import java.util.List;

public class Journal extends Emission {

    Journal(Chaine chaine, String titre, String sous_titre, String type, Date dateDeDebut, Date dateDeFin, Duree duree, List<Personne> guests, List<Personne> writers, String pays, String aspect, String qualite, String note) {
        super(chaine, titre, sous_titre, type, dateDeDebut, dateDeFin, duree, guests, writers, pays, aspect, qualite, note);
    }


}
