package com.tv.program.tri;

import com.tv.program.model.Personne;
import com.tv.program.model.programmes.*;

import java.util.*;

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


    /**
     * Retourne une liste de personnes ainsi que leurs nombre d'apparitions pour
     * un titre de programme donné
     *
     * @param programmes la liste de programmes
     * @param titre  titre du programme sur lequel filtrer
     * @return retourne une nouvelle liste d'acteurs (key) associé à leurs nombre d'apparition (value)
     * trié par nombre d'apparition (Du plus grand nombre au plus petit)
     */
    public static List<Map.Entry<Personne, Integer>> acteursParApparition(List<Programme> programmes, String titre) {
        Map<Personne, Integer> acteursMap = new HashMap<>();

        for (Programme programme : programmes) {
            if (programme.getTitre().contains(titre)) {
                for (Personne personne : programme.getCredits()) {
                    int count = acteursMap.getOrDefault(personne, 0);
                    acteursMap.put(personne, count+1);
                }
            }
        }
        List<Map.Entry<Personne, Integer>> acteursCount = new ArrayList<>(acteursMap.size());
        acteursCount.addAll(acteursMap.entrySet());
        acteursCount.sort((e1, e2) -> e2.getValue() - e1.getValue());
        return acteursCount;
    }

    //pour la question  c) 3), la deuxième
    public ProgrammeTypeList emissionByPeriode(List<Programme> programmes, Date dateDeDebut, Date dateDeFin) {
        ProgrammeTypeList list = new ProgrammeTypeList();
        for (Programme programme : programmes) {
            if (programme.getDateDeDebut().after(dateDeFin) || programme.getDateDeFin().before(dateDeDebut)) {
                continue;
            }
            if (programme instanceof Autre) {
                list.autres.add((Autre) programme);
            } else if (programme instanceof Classique) {
                list.classiques.add((Classique) programme);
            } else if (programme instanceof Contemporain) {
                list.contemporains.add((Contemporain) programme);
            } else if (programme instanceof Documentaire) {
                list.documentaires.add((Documentaire) programme);
            } else if (programme instanceof Emission) {
                list.emissions.add((Emission) programme);
            } else if (programme instanceof Film) {
                list.films.add((Film) programme);
            } else if (programme instanceof Journal) {
                list.journals.add((Journal) programme);
            } else if (programme instanceof Magazine) {
                list.magazines.add((Magazine) programme);
            } else if (programme instanceof Musique) {
                list.musiques.add((Musique) programme);
            } else if (programme instanceof Serie) {
                list.series.add((Serie) programme);
            } else if (programme instanceof Sport) {
                list.sports.add((Sport) programme);
            }
        }

        return list;
    }
}
