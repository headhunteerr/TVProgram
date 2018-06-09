package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Documentaire extends Programme {

    Documentaire(List<Personne> guests) {
        super(guests);
    }

    @Override
    String creditsToString() {
        return "directors=" + getDirectors();
    }

    public List<Personne> getDirectors() {
        return getCredits();
    }
}
