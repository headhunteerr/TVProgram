package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Musique extends Programme {

    Musique(List<Personne> personnes) {
        super(personnes);
    }


    public List<Personne> getActors() {
        return getCredits();
    }

    @Override
    String creditsToString() {
        return "actors=" + getActors();
    }
}
