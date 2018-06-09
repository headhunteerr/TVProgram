package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Emission extends Programme {
    private List<Personne> guests, writers;

    public Emission(List<Personne> personnes) {
        super(personnes);
        guests = new ArrayList<>();
        writers = new ArrayList<>();
        for (Personne personne : personnes) {
            switch (personne.getRole()) {
                case "guests":
                    guests.add(personne);
                    break;
                case "writer":
                    writers.add(personne);
                    break;
                default:
                    System.err.println("Role inconnu pour Emission: " + personne.getRole());
                    break;
            }
        }
        guests = Collections.unmodifiableList(guests);
        writers = Collections.unmodifiableList(writers);
    }

    @Override
    String creditsToString() {
        return "guests=" + getGuests() + "\n\t"
                + "writers=" + getWriters();
    }

    public List<Personne> getGuests() {
        return guests;
    }

    public List<Personne> getWriters() {
        return writers;
    }
}
