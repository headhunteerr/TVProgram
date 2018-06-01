package com.tv.program.model;

import java.util.List;

public class Film extends Programme {

    public Film(List<Personne> personnes) {
        super(personnes);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<Personne> getGuests() {
        return getCredits();
    }
}
