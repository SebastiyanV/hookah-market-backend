package amvisible.hookahmarket.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles_images")
public class ArticleImage extends BaseModel {

    @Column(name = "image_name", nullable = false, unique = true)
    private String imageName;

    @ManyToOne(targetEntity = Article.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference
    private Article article;

    @Column(name = "cover_image", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean coverImage;

    public ArticleImage(String imageName, Article article) {
        this.imageName = imageName;
        this.article = article;
    }
}
