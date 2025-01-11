package com.example.TP1.Entite;

public enum Role {
    Moderator("moderator"), Publisher("publisher");

    private final String role;

    // Constructeur
    Role(String role) {
        this.role = role;
    }
}
