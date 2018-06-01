package com.tv.program.model;

public class Chaine {
    private final String nom, id;

    Chaine(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }

    @Override
    public String toString() {
        return nom;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }
}
