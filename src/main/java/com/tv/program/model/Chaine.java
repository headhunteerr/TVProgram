package com.tv.program.model;

import java.util.Objects;

public class Chaine {
    private final String nom, id;

    public Chaine(String nom, String id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chaine chaine = (Chaine) o;
        return Objects.equals(id, chaine.id);
    }
}
