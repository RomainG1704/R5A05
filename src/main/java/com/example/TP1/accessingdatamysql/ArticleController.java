package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.Article;
import com.example.TP1.Entite.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/article/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = (List<Article>) articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build(); // Code HTTP 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(articles); // Code HTTP 200 OK avec la liste des utilisateurs
    }

    @GetMapping(path="/article/get")
    public ResponseEntity<Article> getArticle(@RequestParam(name = "id") int id) {

        if (articleRepository.findById(id).isPresent()) {
            Article article = articleRepository.findById(id).get();
            return ResponseEntity.ok(article); // Code HTTP 200 OK avec la liste des utilisateurs
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path="/article/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> addNewArticle (@RequestBody Map<String, Object> requestBody) {

        if(userRepository.findById((Integer) requestBody.get("auteur")).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userRepository.findById((Integer) requestBody.get("auteur")).get();
        Article article = new Article();
        article.setAuteur(user);
        article.setContenu(String.valueOf(requestBody.get("contenu")));
        Date date = new Date();
        article.setDatePublication(date);
        articleRepository.save(article);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/article/update")
    public ResponseEntity<?> updateArticle(
            @RequestParam(name = "id") int id,
            @RequestBody Map<String, Object> requestBody) {

        if (articleRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article article = articleRepository.findById(id).get();

        if (userRepository.findById((Integer) requestBody.get("user")).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findById((Integer) requestBody.get("user")).get();
        article.setAuteur(user);
        Date date = new Date();
        article.setDatePublication(date);
        article.setContenu(requestBody.get("contenu").toString());
        articleRepository.save(article);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping(path="/article/delete") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<?> deleteArticle (@RequestBody Map<String, Long> requestBody ) {

        long id = requestBody.get("id");
        if (articleRepository.findById((int)id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Article article = articleRepository.findById((int) id).get();
        articleRepository.delete(article);
        return ResponseEntity.ok().build();
    }
}
