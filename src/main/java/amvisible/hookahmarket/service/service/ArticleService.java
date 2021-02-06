package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.enumerate.ArticleStatusEnum;
import amvisible.hookahmarket.data.enumerate.ArticleTypeEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    void createArticle(ArticleCreateServiceModel articleCreateServiceModel, String email, Map<MultipartFile, Boolean> images);

    List<Article> getLastArticlesWithLimit();

    List<Article> getArticlesByEmail(String name);

    Article getArticleById(String articleId);

    List<Article> getArticlesByType(ArticleTypeEnum type);

    List<Article> getArticlesByStatus(ArticleStatusEnum articleStatus);

    Integer countArticlesByStatus(ArticleStatusEnum articleStatus);
}
