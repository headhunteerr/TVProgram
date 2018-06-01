package com.tv.program.model;

public class Personne {
    public final static String GUEST = "guest", WRITER = "writer", PRESENTER = "presenter";
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
