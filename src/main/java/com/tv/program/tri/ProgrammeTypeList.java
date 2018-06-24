package com.tv.program.tri;

import com.tv.program.model.programmes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe containeur d'objet utilisée pour traiter les types d'émissions par periode.
 */
public class ProgrammeTypeList {
    List<Autre> autres;
    List<Classique> classiques;
    List<Contemporain> contemporains;
    List<Documentaire> documentaires;
    List<Emission> emissions;
    List<Film> films;
    List<Journal> journals;
    List<Magazine> magazines;
    List<Musique> musiques;
    List<Serie> series;
    List<Sport> sports;

    public ProgrammeTypeList() {
        autres = new ArrayList<>();
        classiques = new ArrayList<>();
        contemporains = new ArrayList<>();
        documentaires = new ArrayList<>();
        emissions = new ArrayList<>();
        films = new ArrayList<>();
        journals = new ArrayList<>();
        magazines = new ArrayList<>();
        musiques = new ArrayList<>();
        series = new ArrayList<>();
        sports = new ArrayList<>();
    }

    public List<Autre> getAutres() {
        return autres;
    }

    public List<Classique> getClassiques() {
        return classiques;
    }

    public List<Contemporain> getContemporains() {
        return contemporains;
    }

    public List<Documentaire> getDocumentaires() {
        return documentaires;
    }

    public List<Emission> getEmissions() {
        return emissions;
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public List<Musique> getMusiques() {
        return musiques;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public List<Sport> getSports() {
        return sports;
    }
}
