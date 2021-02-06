package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.ArticleView;
import amvisible.hookahmarket.data.repository.ArticleViewRepository;
import amvisible.hookahmarket.service.service.ArticleService;
import amvisible.hookahmarket.service.service.ArticleViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class ArticleViewServiceImpl implements ArticleViewService {

    private final ArticleViewRepository articleViewRepository;
    private final ArticleService articleService;

    @Autowired
    public ArticleViewServiceImpl(
            ArticleViewRepository articleViewRepository,
            ArticleService articleService) {

        this.articleViewRepository = articleViewRepository;
        this.articleService = articleService;
    }

    @Override
    public void registerView(String articleId, HttpServletRequest request) {
        Article article = this.articleService.getArticleById(articleId);
        Date currentDate = new Date();
        ArticleView articleView = new ArticleView(article, request.getRemoteAddr(), currentDate);

        ArticleView lastArticleViewDate =
                this.articleViewRepository.findTopByArticleAndIpAddress(article, request.getRemoteAddr())
                        .orElse(null);

        if (null != lastArticleViewDate) {
            long currentDateDiffInMillis = Math.abs(currentDate.getTime() - lastArticleViewDate.getDate().getTime());
            long allowedDateDiffInMillis = 3600000; // 60 minutes in milliseconds
            if (allowedDateDiffInMillis > currentDateDiffInMillis) {
                return;
            }
        }

        this.articleViewRepository.saveAndFlush(articleView);
    }
}
