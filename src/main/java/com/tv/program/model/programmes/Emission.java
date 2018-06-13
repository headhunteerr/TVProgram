package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Emission extends Programme {
    private List<Personne> guests, writers, presenters;

    Emission(List<Personne> personnes) {
        super(personnes);
        guests = new ArrayList<>();
        writers = new ArrayList<>();
        presenters = new ArrayList<>();
        for (Personne personne : personnes) {
            switch (personne.getRole()) {
                case "guest":
                    guests.add(personne);
                    break;
                case "writer":
                    writers.add(personne);
                    break;
                case "presenter":
                    presenters.add(personne);
                    break;
                default:
                    System.err.println("Role inconnu pour Emission: " + personne.getRole());
                    break;
            }
        }
        guests = Collections.unmodifiableList(guests);
        writers = Collections.unmodifiableList(writers);
        presenters = Collections.unmodifiableList(presenters);
    }

    @Override
    String creditsToString() {
        return "guests=" + getGuests() + "\n\t"
                + "writers=" + getWriters() + "\n\t"
                + "presenters=" + getPresenters();
    }

    public List<Personne> getGuests() {
        return guests;
    }

    public List<Personne> getWriters() {
        return writers;
    }

    public List<Personne> getPresenters() {
        return presenters;
    }
}
