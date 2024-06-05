package com.dioufserignemor.gmail.gestionelection.entites;

public enum Civilite {
    MONSIEUR ("Mr"),
    MADAME ("Mm"),
    AUTRE ("Aut");

    private final String name;

    Civilite(String name) {
        this.name = name;
    }
}
