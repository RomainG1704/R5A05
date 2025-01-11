package com.example.TP1.Entite;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date datePublication;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Renommage pour éviter une collision avec l'ID de l'entité
    private User auteur;

    @Column(nullable = false, length = 5000)
    private String contenu;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", datePublication=" + datePublication +
                ", auteur=" + (auteur != null ? auteur.getUsername() : "null") +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
