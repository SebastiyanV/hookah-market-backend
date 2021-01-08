package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;

import java.util.List;

public interface ArticleService {

    void createArticle(ArticleCreateServiceModel articleCreateServiceModel, String email);

    List<Article> getLastArticlesWithLimit();

    List<Article> getArticlesByEmail(String name);
}
