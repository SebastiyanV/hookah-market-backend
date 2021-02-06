package amvisible.hookahmarket.web.controller;

import amvisible.hookahmarket.data.enumerate.ArticleStatusEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.service.service.ArticleService;
import amvisible.hookahmarket.service.service.ArticleViewService;
import amvisible.hookahmarket.service.service.BrandService;
import amvisible.hookahmarket.service.service.CategoryService;
import amvisible.hookahmarket.web.model.MessageResponseModel;
import amvisible.hookahmarket.web.model.article.ArticleCreateServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static amvisible.hookahmarket.data.constant.Message.ARTICLE_CREATED;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {

    private final ArticleService articleService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ArticleViewService articleViewService;

    @Autowired
    public ArticleController(
            ArticleService articleService,
            BrandService brandService,
            CategoryService categoryService,
            ArticleViewService articleViewService) {

        this.articleService = articleService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.articleViewService = articleViewService;
    }

    @GetMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Map<String, List<?>> getData() {
        Map<String, List<?>> brandAndCategories = new HashMap<>();
        brandAndCategories.put("brands", this.brandService.getAll());
        brandAndCategories.put("categories", this.categoryService.getAll());
        return brandAndCategories;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createArticle(
            @RequestPart("article") ArticleCreateServiceModel articleCreateServiceModel,
            @RequestParam(value = "image1") MultipartFile image1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "image3", required = false) MultipartFile image3,
            @RequestParam(value = "image4", required = false) MultipartFile image4,
            @RequestParam(value = "image5", required = false) MultipartFile image5,
            @RequestParam(value = "image6", required = false) MultipartFile image6,
            Principal principal
    ) {
        Map<MultipartFile, Boolean> images = new HashMap<>();
        images.put(image1, true);
        images.put(image2, false);
        images.put(image3, false);
        images.put(image4, false);
        images.put(image5, false);
        images.put(image6, false);
        try {
            this.articleService.createArticle(articleCreateServiceModel, principal.getName(), images);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(e.getMessage()));
        }
        return ResponseEntity.ok().body(new MessageResponseModel(ARTICLE_CREATED));
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public List<Article> getAllArticles() {
        return this.articleService.getLastArticlesWithLimit();
    }

    @GetMapping(value = "/my-articles", consumes = "application/json", produces = "application/json")
    public List<Article> getMyArticles(Principal principal) {
        return this.articleService.getArticlesByEmail(principal.getName())
                .stream()
                .sorted((a, b) -> b.getPublishedOn().compareTo(a.getPublishedOn()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{articleId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Article getArticleById(@PathVariable String articleId, HttpServletRequest request) {
        this.articleViewService.registerView(articleId, request);
        return this.articleService.getArticleById(articleId);
    }

    @GetMapping(value = "/status/{articleStatus}")
    public List<Article> getAwaitingApprovalArticlesSortByDateDesc(@PathVariable ArticleStatusEnum articleStatus) {
        return this.articleService.getArticlesByStatus(articleStatus)
                .stream()
                .sorted(Comparator.comparing(Article::getPublishedOn))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/status/{articleStatus}/count")
    public Integer getCountOfTypeArticles(@PathVariable ArticleStatusEnum articleStatus) {
        return this.articleService.countArticlesByStatus(articleStatus);
    }
}
