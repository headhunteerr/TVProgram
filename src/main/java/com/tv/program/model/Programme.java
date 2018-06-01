package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class Programme {
    private Chaine chaine;
    private String titre, sous_titre;
    private String description;
    private String type;
    private Date dateDeDebut, dateDeFin;
    private Duree duree;
    private int annee;
    private final List<Personne> credits;
    private String pays;
    private String aspect; //ex 16:9
    private String qualite; //ex HDTV
    private String note; //ex TOUT PUBLIC

    Programme(List<Personne> personnes) {
        this.credits = Collections.unmodifiableList(personnes);
    }

    /**
     * Obligation de redéfinir la méthode toString()
     * pour les classes filles
     */
    public abstract String toString();

    public Chaine getChaine() {
        return chaine;
    }

    public String getTitre() {
        return titre;
    }

    public String getSous_titre() {
        return sous_titre;
    }

    public String getType() {
        return type;
    }

    public Date getDateDeDebut() {
        return dateDeDebut;
    }

    public Date getDateDeFin() {
        return dateDeFin;
    }

    public Duree getDuree() {
        return duree;
    }

    public List<Personne> getCredits() {
        return credits;
    }

    public String getPays() {
        return pays;
    }

    public String getAspect() {
        return aspect;
    }

    public String getQualite() {
        return qualite;
    }

    public String getNote() {
        return note;
    }

    public int getAnnee() {
        return annee;
    }

    public String getDescription() {
        return description;
    }

    public static Programme of(Chaine chaine, String titre, String sous_titre,
                               String description, int annee, String type,
                               List<Personne> personnes, Date dateDeDebut, Date dateDeFin,
                               Duree duree, String pays, String aspect, String qualite,
                               String note) {
        Programme programme;
        if (type.contains("documentaire")) {
            programme = new Documentaire(personnes);
        } else if (type.contains("classique")) {
            programme = new Classique(personnes);
        } else if (type.contains("emission")) {
            programme = new Emission(personnes);
        } else if (type.contains("film")) {
            programme = new Film(personnes);
        } else if (type.contains("journal")) {
            programme = new Journal(personnes);
        } else if (type.contains("magazine")) {
            programme = new Magazine(personnes);
        } else {
            throw new RuntimeException("Type de programme inconnu:" + type);
        }

        programme.chaine = chaine;
        programme.titre = titre;
        programme.sous_titre = sous_titre;
        programme.description = description;
        programme.annee = annee;
        programme.dateDeDebut = dateDeDebut;
        programme.dateDeFin = dateDeFin;
        programme.duree = duree;
        programme.pays = pays;
        programme.aspect = aspect;
        programme.qualite = qualite;
        programme.type = type;
        programme.note = note;

        return programme;
    }
}
