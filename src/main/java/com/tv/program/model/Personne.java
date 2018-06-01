package com.tv.program.model;

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
}
