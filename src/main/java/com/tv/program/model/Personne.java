package com.tv.program.model;

import java.util.Objects;

/**
 * Classe représentant une persone. Une personne a un nom et un role, une profession.
 */
public class Personne {
    private String role;
    private String nom;

    public Personne(String nom, String role) {
        this.nom = nom;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return role + ": " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(role, personne.role) &&
                Objects.equals(nom, personne.nom);
    }
}
