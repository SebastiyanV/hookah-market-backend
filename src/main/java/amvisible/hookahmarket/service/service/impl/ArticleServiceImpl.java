package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.enumerate.ArticleStatusEnum;
import amvisible.hookahmarket.data.enumerate.ArticleTypeEnum;
import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.Brand;
import amvisible.hookahmarket.data.model.Category;
import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.data.repository.ArticleRepository;
import amvisible.hookahmarket.service.service.ArticleImageService;
import amvisible.hookahmarket.service.service.ArticleService;
import amvisible.hookahmarket.service.service.UserService;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ArticleImageService articleImageService;

    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            ModelMapper modelMapper,
            UserService userService,
            ArticleImageService articleImageService) {

        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.articleImageService = articleImageService;
    }

    @Override
    @Transactional
    public void createArticle(ArticleCreateServiceModel articleCreateServiceModel, String email, Map<MultipartFile, Boolean> images) {
        User user = this.userService.getUserByEmail(email).orElse(null);
        if (null != user) {
            articleCreateServiceModel.setAuthor(user);
            articleCreateServiceModel.setArticleCondition(ConditionEnum.NEW);

            Brand brand = this.modelMapper.map(articleCreateServiceModel.getBrand(), Brand.class);
            Category category = this.modelMapper.map(articleCreateServiceModel.getCategory(), Category.class);

            Article article = this.modelMapper.map(articleCreateServiceModel, Article.class);

            article.setBrand(brand);
            article.setCategory(category);

            this.articleRepository.saveAndFlush(article);

            this.articleImageService.uploadImages(images, article);
        }
    }

    @Override
    public List<Article> getLastArticlesWithLimit() {
        return this.articleRepository.findAll();
    }

    @Override
    public List<Article> getArticlesByEmail(String name) {
        User user = this.userService.getUserByEmail(name).orElse(null);
        if (null != user) {
            return this.articleRepository.findAllByAuthor(user);
        }
        return null;
    }

    @Override
    public Article getArticleById(String articleId) {
        return this.articleRepository.findById(articleId).orElse(null);
    }

    @Override
    public List<Article> getArticlesByType(ArticleTypeEnum type) {
        return this.articleRepository.findAllByType(type);
    }

    @Override
    public List<Article> getArticlesByStatus(ArticleStatusEnum articleStatus) {
        return this.articleRepository.findAllByStatus(articleStatus);
    }

    @Override
    public Integer countArticlesByStatus(ArticleStatusEnum articleStatus) {
        return this.articleRepository.countArticleByStatus(articleStatus);
    }
}
