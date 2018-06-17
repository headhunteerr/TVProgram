package com.tv.program.tri;

import com.tv.program.model.programmes.Programme;

import java.util.Comparator;
import java.util.List;

public class ProgrammeTrieur {
    private final static Comparator<Programme> PAR_DATE_DE_DEBUT =
            Comparator.comparing(Programme::getDateDeDebut);

    private final static Comparator<Programme> PAR_DATE_DE_FIN =
            Comparator.comparing(Programme::getDateDeFin);

    private final static Comparator<Programme> PAR_TITRE =
            Comparator.comparing(Programme::getTitre);

    private final static Comparator<Programme> PAR_ANNEE =
            Comparator.comparing(Programme::getAnnee);

    private final static Comparator<Programme> PAR_PAYS =
            Comparator.comparing(Programme::getPays);

    private final static Comparator<Programme> PAR_DUREE =
            Comparator.comparing(Programme::getDuree);

    private final static Comparator<Programme> PAR_TYPE =
            Comparator.comparing(Programme::getType);

    private final static Comparator<Programme> PAR_ASPECT =
            Comparator.comparing(Programme::getAspect);

    private final static Comparator<Programme> PAR_QUALITE =
            Comparator.comparing(Programme::getQualite);

    public static void trierParDateDeDebut(List<Programme> programmes) {
        programmes.sort(PAR_DATE_DE_DEBUT);
    }

    public static void trierParDateDeFin(List<Programme> programmes) {
        programmes.sort(PAR_DATE_DE_FIN);
    }

    public static void trierParTitre(List<Programme> programmes) {
        programmes.sort(PAR_TITRE);
    }

    public static void trierParAnnee(List<Programme> programmes) {
        programmes.sort(PAR_ANNEE);
    }

    public static void trierParPays(List<Programme> programmes) {
        programmes.sort(PAR_PAYS);
    }

    public static void trierParType(List<Programme> programmes) {
        programmes.sort(PAR_TYPE);
    }

    public static void trierParDuree(List<Programme> programmes) {
        programmes.sort(PAR_DUREE);
    }

    public static void trierParAspect(List<Programme> programmes) {
        programmes.sort(PAR_ASPECT);
    }

    public static void trierParQualitee(List<Programme> programmes) {
        programmes.sort(PAR_QUALITE);
    }
}
