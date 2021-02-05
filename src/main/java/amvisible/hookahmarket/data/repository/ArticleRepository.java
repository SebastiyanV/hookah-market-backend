package amvisible.hookahmarket.data.repository;

import amvisible.hookahmarket.data.enumerate.ArticleStatusEnum;
import amvisible.hookahmarket.data.enumerate.ArticleTypeEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> findAllByAuthor(User user);

    List<Article> findAllByType(ArticleTypeEnum type);

    List<Article> findAllByStatus(ArticleStatusEnum articleStatus);
}
