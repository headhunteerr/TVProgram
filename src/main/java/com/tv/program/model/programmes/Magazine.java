package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Magazine extends Programme {

    public Magazine(List<Personne> personnes) {
        super(personnes);
    }

    @Override
    String creditsToString() {
        return "presenters=" + getPresenters();
    }

    public List<Personne> getPresenters() {
        return getCredits();
    }
}
