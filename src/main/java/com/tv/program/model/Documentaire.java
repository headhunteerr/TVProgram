package com.tv.program.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Documentaire extends Programme {

    Documentaire(List<Personne> guests) {
        super(guests);
    }

    @Override
    public String toString() {
        return null;
    }

    public List<Personne> getGuests() {
        return getCredits();
    }
}
