package com.tv.program.tri;

import com.tv.program.model.programmes.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe containeur d'objet utilisée pour traiter le nombre de types d'émissions par periode.
 */
public class ProgrammeCountDailyList {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //mapping entre date (dd/MM/yyyy) et un map de type de programme -> nb apparition
    private Map<String, Map<String, Integer>> dateApparitionMap = new TreeMap<>();

    public ProgrammeCountDailyList(Date dateDeDebut, Date dateDeFin) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateDeDebut);
        while (calendar.getTime().before(dateDeFin)) {
            dateApparitionMap.put(sdf.format(calendar.getTime()), new TreeMap<>());
            calendar.add(Calendar.DATE, 1);
        }
        dateApparitionMap.put(sdf.format(calendar.getTime()), new TreeMap<>());
    }

    /**
     * Ajoute le type de programme pour la date correspondant à la date de début du programme
     * @param programme le programme à ajouter
     */
    public void add(Programme programme) {
        Map<String, Integer> apparitionMap = dateApparitionMap.get(sdf.format(programme.getDateDeDebut()));
        int count = apparitionMap.getOrDefault(programme.getClass().getSimpleName(), 0);
        apparitionMap.put(programme.getClass().getSimpleName(), count+1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Map<String, Integer>> dateEntry : dateApparitionMap.entrySet()) {
            if (dateEntry.getValue().size() > 0) {
                stringBuilder.append("day " + dateEntry.getKey() + ":\n");

                for (Map.Entry<String, Integer> typeCountEntry : dateEntry.getValue().entrySet()) {
                    stringBuilder.append("\t " + typeCountEntry.getKey() + " -> " + dateEntry.getValue() + "\n");
                }
            }
        }
        return stringBuilder.toString();
    }
}
