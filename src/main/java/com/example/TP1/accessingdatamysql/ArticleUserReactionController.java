package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.Article;
import com.example.TP1.Entite.ArticleUserReaction;
import com.example.TP1.Entite.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleUserReactionController {

    @Autowired
    private ArticleUserReactionRepository repo;
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private UserRepository userRepo;

    @PostMapping(path = "/like")
    public ResponseEntity<?> likeArticle(@RequestParam int userId, @RequestParam int articleId) {
        if (repo.findByUserAndArticle(userId, articleId).isPresent()) {
            ArticleUserReaction reaction = repo.findByUserAndArticle(userId, articleId).get();
            reaction.changeReact();
            repo.save(reaction);
            return ResponseEntity.ok().build();
        }
        if(userRepo.findById(userId).isPresent() && articleRepo.findById(articleId).isPresent()) {
            User user = userRepo.findById(userId).get();
            Article article = articleRepo.findById(articleId).get();

            ArticleUserReaction articleUserReaction = new ArticleUserReaction();
            articleUserReaction.setUser(user);
            articleUserReaction.setArticle(article);
            articleUserReaction.setLiked(true);

            repo.save(articleUserReaction);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/dislike")
    public ResponseEntity<?> dislikeArticle(@RequestParam int userId, @RequestParam int articleId) {
        if(userRepo.findById(userId).isPresent() && articleRepo.findById(articleId).isPresent()) {
            User user = userRepo.findById(userId).get();
            Article article = articleRepo.findById(articleId).get();

            ArticleUserReaction articleUserReaction = new ArticleUserReaction();
            articleUserReaction.setUser(user);
            articleUserReaction.setArticle(article);
            articleUserReaction.setLiked(false);

            repo.save(articleUserReaction);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/deleteReact")
    public ResponseEntity<?> deleteReaction(@RequestParam int reactId) {
        ArticleUserReaction articleUserReaction = repo.findById(reactId).get();
        repo.delete(articleUserReaction);
        return ResponseEntity.ok().build();
    }

}
