package com.tv.program.model;

import java.util.List;

public class Magazine extends Programme {

    public Magazine(List<Personne> personnes) {
        super(personnes);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<Personne> getPresenters() {
        return getCredits();
    }
}
