package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Classique extends Programme {

    Classique(List<Personne> personnes) {
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
