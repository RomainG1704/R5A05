package com.example.TP1.Entite;

import jakarta.persistence.*;

@Entity
public class ArticleUserReaction {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    private boolean liked;
    @Id
    @GeneratedValue
    private Long id;

    public ArticleUserReaction() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void changeReact() {
        this.setLiked(!liked);
    }
}
