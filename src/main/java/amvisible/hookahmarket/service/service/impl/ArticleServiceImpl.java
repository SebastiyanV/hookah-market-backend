package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.Brand;
import amvisible.hookahmarket.data.model.Category;
import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.data.repository.ArticleRepository;
import amvisible.hookahmarket.service.service.ArticleService;
import amvisible.hookahmarket.service.service.BrandService;
import amvisible.hookahmarket.service.service.UserService;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            ModelMapper modelMapper,
            UserService userService) {

        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void createArticle(ArticleCreateServiceModel articleCreateServiceModel, String email) {
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
        }
    }

    @Override
    public List<Article> getLastArticlesWithLimit() {
        return this.articleRepository.findAll();
    }
}
