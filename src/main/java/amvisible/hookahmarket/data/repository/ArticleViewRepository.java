package amvisible.hookahmarket.data.repository;

import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.ArticleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleViewRepository extends JpaRepository<ArticleView, String> {

    Optional<ArticleView> findTopByArticleAndIpAddress(Article article, String ipAddress);
}
