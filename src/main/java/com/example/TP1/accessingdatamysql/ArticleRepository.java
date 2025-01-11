package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
