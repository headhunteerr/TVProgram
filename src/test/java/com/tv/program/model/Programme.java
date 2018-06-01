package com.tv.program.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public abstract class Programme {
    private Chaine chaine;
    private String titre, sous_titre;
    private String type;
    private Date dateDeDebut, dateDeFin;
    private Duree duree;
    private List<Personne> credits = Collections.EMPTY_LIST;
    private String pays;
    String aspect; //ex 16:9
    String qualite;
    String note;

    Programme(Chaine chaine, String titre, String sous_titre,
              String type, Date dateDeDebut, Date dateDeFin,
              Duree duree, String pays,
              String aspect, String qualite, String note) {
        this.chaine = chaine;
        this.titre = titre;
        this.sous_titre = sous_titre;
        this.type = type;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.duree = duree;
        this.pays = pays;
        this.aspect = aspect;
        this.qualite = qualite;
        this.note = note;
    }

    void setCredits(List<Personne>... credits) {
        this.credits = Stream.of(credits)
                .reduce(new ArrayList<>(),(l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
        //pour rendre la modification d'éléments impossible via le getter
        this.credits = Collections.unmodifiableList(this.credits);
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
}
