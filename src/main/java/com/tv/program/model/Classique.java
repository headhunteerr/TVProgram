package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Classique extends Programme {

    Classique(List<Personne> personnes) {
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
