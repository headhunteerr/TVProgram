package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Film extends Programme {

    public Film(List<Personne> personnes) {
        super(personnes);
    }

    @Override
    String creditsToString() {
        return "guests=" + getGuests();
    }

    public List<Personne> getGuests() {
        return getCredits();
    }
}
