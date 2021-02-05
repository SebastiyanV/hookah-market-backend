package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ArticleImageService {

    void uploadImages(Map<MultipartFile, Boolean> images, Article article);
}
