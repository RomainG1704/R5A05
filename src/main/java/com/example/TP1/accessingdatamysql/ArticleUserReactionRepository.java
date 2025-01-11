package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.ArticleUserReaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleUserReactionRepository extends CrudRepository<ArticleUserReaction, Integer> {
    Optional<ArticleUserReaction> findByUserAndArticle(int userId, int articleId);
}
