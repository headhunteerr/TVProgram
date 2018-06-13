package com.tv.program.model.programmes;

import com.tv.program.model.Chaine;
import com.tv.program.model.Duree;
import com.tv.program.model.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class Programme {
    private static long ID_COUNT = 0;
    
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
    private final long id;

    Programme(List<Personne> personnes) {
        this.credits = Collections.unmodifiableList(personnes);
        this.id = ID_COUNT++;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Programme)) {
            return false;
        }
        return id == ((Programme) obj).id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "\n\tid=" + id +
                "\n\tchaine=" + chaine +
                "\n\ttitre='" + titre + '\'' +
                "\n\tsous_titre='" + sous_titre + '\'' +
                "\n\tdescription='" + description + '\'' +
                "\n\ttype='" + type + '\'' +
                "\n\tdateDeDebut=" + dateDeDebut +
                "\n\tdateDeFin=" + dateDeFin +
                "\n\tduree=" + duree +
                "\n\tannee=" + annee +
                "\n\t" + creditsToString() +
                "\n\tpays='" + pays + '\'' +
                "\n\taspect='" + aspect + '\'' +
                "\n\tqualite='" + qualite + '\'' +
                "\n\tnote='" + note + '\'' +
                "\n}";
    }
    
    abstract String creditsToString();

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
            programme = new Journal();
        } else if (type.contains("magazine")) {
            programme = new Magazine(personnes);
        } else if (type.contains("contemporain")) {
            programme = new Contemporain();
        } else if (type.contains("série d'animation")) {
            programme = new SerieAnimation();
        } else {
            //TODO IL Y A PLUS DE 73 TYPES DE PROGRAMMES DIFFERENTS=> ARRETER CLASSE ABSTRAITES???
            System.err.println("Type inconnu: " + type);
            return new Programme(new ArrayList<>()) {
                @Override
                String creditsToString() {
                    return null;
                }
            };
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
