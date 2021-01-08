package amvisible.hookahmarket.web.controller;

import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.service.service.ArticleService;
import amvisible.hookahmarket.service.service.BrandService;
import amvisible.hookahmarket.service.service.CategoryService;
import amvisible.hookahmarket.web.model.MessageResponseModel;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static amvisible.hookahmarket.data.constant.Message.ARTICLE_CREATED;
import static amvisible.hookahmarket.data.constant.Message.THERE_IS_A_PROBLEM;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {

    private final ArticleService articleService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Autowired
    public ArticleController(
            ArticleService articleService,
            BrandService brandService,
            CategoryService categoryService) {

        this.articleService = articleService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Map<String, List<?>> getData() {
        Map<String, List<?>> brandAndCategories = new HashMap<>();
        brandAndCategories.put("brands", this.brandService.getAll());
        brandAndCategories.put("categories", this.categoryService.getAll());
        return brandAndCategories;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createArticle(
            @RequestBody ArticleCreateServiceModel articleCreateServiceModel,
            Principal principal
    ) {
        try {
            this.articleService.createArticle(articleCreateServiceModel, principal.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(THERE_IS_A_PROBLEM));
        }

        return ResponseEntity.ok().body(new MessageResponseModel(ARTICLE_CREATED));
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public List<Article> getAllArticles() {
        return this.articleService.getLastArticlesWithLimit();
    }

    @GetMapping(value = "/get/my", consumes = "application/json", produces = "application/json")
    public List<Article> getMyArticles(Principal principal) {
        return this.articleService.getArticlesByEmail(principal.getName());
    }
}
