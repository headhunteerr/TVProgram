package com.tv.program.model.programmes;

import com.tv.program.model.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Serie extends Programme {

    private List<Personne> actors, writers, directors, guests;

    Serie(List<Personne> personnes) {
        super(Collections.emptyList());
        actors = new ArrayList<>();
        writers = new ArrayList<>();
        directors = new ArrayList<>();
        guests = new ArrayList<>();
        for (Personne personne : personnes) {
            switch (personne.getRole()) {
                case "actor":
                    actors.add(personne);
                    break;
                case "writer":
                    writers.add(personne);
                    break;
                case "director":
                    directors.add(personne);
                    break;
                case "guest":
                    guests.add(personne);
                    break;
            }
        }
        actors = Collections.unmodifiableList(actors);
        writers = Collections.unmodifiableList(writers);
        directors = Collections.unmodifiableList(directors);
        guests = Collections.unmodifiableList(guests);
    }

    public List<Personne> getActors() {
        return actors;
    }

    public List<Personne> getWriters() {
        return writers;
    }

    public List<Personne> getDirectors() {
        return directors;
    }

    public List<Personne> getGuests() {
        return guests;
    }

    @Override
    String creditsToString() {
        return "actors=" + getActors() + "\n\t"
                + "writers=" + getWriters() + "\n\t"
                + "directors=" + getDirectors() + "\n\t"
                + "guests=" + getGuests();
    }
}
