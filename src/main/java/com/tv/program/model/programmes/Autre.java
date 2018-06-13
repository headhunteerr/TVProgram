package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.List;

public class Autre extends Programme {
    Autre(List<Personne> personnes) {
        super(personnes);
    }

    @Override
    String creditsToString() {
        return "credits=" + getCredits();
    }
}
