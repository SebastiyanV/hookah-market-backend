package amvisible.hookahmarket.data.repository;

import amvisible.hookahmarket.data.model.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleImageRepository extends JpaRepository<ArticleImage, String> {
}
