package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.ArticleImage;
import amvisible.hookahmarket.data.repository.ArticleImageRepository;
import amvisible.hookahmarket.service.service.ArticleImageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Service
public class ArticleImageServiceImpl implements ArticleImageService {

    private final ArticleImageRepository articleImageRepository;
    private final AmazonService amazonService;

    @Autowired
    public ArticleImageServiceImpl(
            ArticleImageRepository articleImageRepository,
            AmazonService amazonService) {

        this.articleImageRepository = articleImageRepository;
        this.amazonService = amazonService;
    }

    @Override
    @Transactional
    public void uploadImages(Map<MultipartFile, Boolean> images, Article article) {
        images.forEach((currentImage, isCoverImage) -> {
            if (currentImage != null) {
                String fileExtension = FilenameUtils
                        .getExtension(
                                currentImage.getOriginalFilename()
                        );
                String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
                ArticleImage articleImage = new ArticleImage(newFileName, article);
                if (isCoverImage) {
                    articleImage.setCoverImage(true);
                }
                this.amazonService.uploadFile(currentImage, newFileName);
                this.articleImageRepository.saveAndFlush(articleImage);
            }
        });
    }
}
